# AQS简介

- 在同步组件的实现中，AQS是核心部分，同步组件的实现者通过使用AQS提供的模板方法实现同步组件语义。
- AQS 则实现了对同步状态的管理，以及对阻塞线程进行排队，等待通知等等一些底层的实现处理。
- AQS的核心也包括了这些方面：同步队列，独占式锁的获取和释放，共享锁的获取和释放以及可中断锁，超时等待锁获取这些特性的实现，而这些实际上则是AQS提供出来的模板方法。![在这里插入图片描述](https://img-blog.csdnimg.cn/20181122135922473.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NpZmFuY2hhbw==,size_16,color_FFFFFF,t_70)

# 同步队列

当共享资源被某个线程占有，其他请求该资源的线程将会阻塞，从而进入同步队列。就数据结构而言，队列的实现方式无外乎两者：一是通过数组的形式，另外一种则是链表的形式。AQS中的同步队列则是通过链式方式进行实现。 接下来，很显然我们至少会抱有这样的疑问：1. 节点的数据结构是什么样的？2. 是单向还是双向？3. 是带头结点的还是不带头节点的？

在AQS有一个静态内部类Node，这是我们同步队列的每个具体节点。在这个类中有如下属性：

```java
volatile int waitStatus; // 节点状态
volatile Node prev; // 当前节点的前驱节点 
volatile Node next; // 当前节点的后继节点 
volatile Thread thread; // 当前节点所包装的线程对象 
Node nextWaiter; // 等待队列中的下一个节点
```

### ***节点的状态如下:***

- **`int INITIAL = 0`; // 初始状态**
- **`int CANCELLED = 1`; // 当前节点从同步队列中取消**
- **`int SIGNAL = -1`; // 后继节点的线程处于等待状态，如果当前节点释放同步状态会通知后继节点，使得后继 节点的线程继续运行。**
- **`int CONDITION = -2`; // 节点在等待队列中，节点线程等待在Condition上，当其他线程对Condition调用了 signal()方法后，该节点将会从等待队列中转移到同步队列中，加入到对同步状态的获取中。**
- **`int PROPAGATE = -3`; // 表示下一次共享式同步状态获取将会无条件地被传播下去。**

现在我们知道了节点的数据结构类型，并且每个节点拥有其前驱和后继节点，很显然这是一个**带头结点双向链表**。

也就是说**AQS实际上通过头尾指针来管理同步队列，同时实现包括获取锁失败的线程进行入队，释放锁时对同步队 列中的线程进行通知等核心方法**。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181122141136941.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NpZmFuY2hhbw==,size_16,color_FFFFFF,t_70)

# 独占锁的获取

调用lock()方法是获取独占锁，获取锁失败后调用AQS提供的`acquire（int arg）`模板方法将当前线程加入同步队列，成功则线程执行。来看`ReentrantLock`源码

```java
final void lock() {    
	if (compareAndSetState(0, 1))        
		setExclusiveOwnerThread(Thread.currentThread());   
	else       
		acquire(1); 
}
```

**lock方法使用CAS来尝试将同步状态改为1，如果成功则将同步状态持有线程置为当前线程。否则将调用AQS提供的 acquire()方法。**

```java
public final void acquire(int arg) {
 	    // 再次尝试获取同步状态，如果成功则方法直接返回    
 	    // 如果失败则先调用addWaiter()方法再调用acquireQueued()方法
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
}
```

**`tryAcquire(arg)`：再次尝试获取同步状态，成功直接方法退出，失败调用addWaiter();
`addWaiter(Node.EXCLUSIVE), arg)`：将当前线程以指定模式(独占式、共享式)封装为Node节点后置入同步队列**

```java
private Node addWaiter(Node mode) {
    	// 将线程以指定模式封装为Node节点
        Node node = new Node(Thread.currentThread(), mode);
        // 获取当前队列的尾节点
        Node pred = tail;
        // 若尾节点不为空
        if (pred != null) {
            node.prev = pred;
            // 使用CAS将当前节点尾插到同步队列中
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                // CAS尾插成功，返回当前Node节点
                return node;
            }
        }
        // 尾节点为空 || CAS尾插失败
        enq(node);
        return node;
    }
```

分析上面的注释。程序的逻辑主要分为两个部分：1. 当前同步队列的尾节点为null，调用方法`enq()`插入；2. 当前队列的尾节点不为null，则采用尾插入（`compareAndSetTail()`方法）的方式入队。另外还会有另外一个问题： 如果 `if (compareAndSetTail(pred, node))`为false怎么办？会继续执行到`enq()`方法，同时很明显`compareAndSetTail()` 是一个CAS操作，通常来说如果CAS操作失败会继续自旋（死循环）进行重试。

因此，经过我们这样的分析，`enq()`方法可能承担两个任务：

1. 处理当前同步队列尾节点为null时进行入队操作；
2. 如果CAS尾插入节点失败后负责自旋进行尝试

```java
private Node enq(final Node node) {
    	// 直到将当前节点插入同步队列成功为止
        for (;;) {
            Node t = tail;
            // 初始化同步队列
            if (t == null) { // Must initialize
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else {
            	// 不断CAS将当前节点尾插入同步队列中
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
       }
}
```

在上面的分析中我们可以看出在第1步中会先创建头结点，说明**同步队列是带头结点的链式存储结构**。带头结点与不带头结点相比，会在入队和出队的操作中获得更大的便捷性，因此同步队列选择了带头结点的链式存储结构。那么**带头节点的队列初始化时机是什么？自然而然是在`tail==null`时，即当前线程是第一次插入同步队列**。 `compareAndSetTail(t, node)`方法会利用CAS操作设置尾节点，如果CAS操作失败会在for (;;)死循环中不断尝试，直至成功return返回为止。

因此，对enq()方法可以做这样的总结：

1. 在当前线程是第一个加入同步队列时，调用`compareAndSetHead(new Node())`方法，完成链式队列头结点的初始化；
2. 自旋不断尝试CAS尾插入节点直至成功为止。

**现在我们已经很清楚获取独占式锁失败的线程包装成Node然后插入同步队列的过程了**。那么紧接着会有下一个问题：在同步队列中的节点（线程）会做什么事情来保证自己能够有机会获得独占式锁？

```java
final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            // 自旋
            for (;;) {
            	// 获取当前节点的前驱节点
                final Node p = node.predecessor();
                // 获取同步状态成功条件
                // 前驱节点为头结点并且获取同步状态成功
                if (p == head && tryAcquire(arg)) {
                	// 将当前节点设置为头结点
                    setHead(node);
                    // 删除原来的头结点
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
        	// 获取失败将当前节点取消
            if (failed)
                cancelAcquire(node);
        }
}
```

整体来看这是一个这又是一个自旋的过程（`for(;;)`），代码首先获取当前节点的先驱节点，**如果先驱节点是头结点的并且成功获得同步状态的时候（`if (p == head && tryAcquire(arg))`），表示当前节点所指向的线程能够获取锁**，方法执行结束。反之，获取锁失败进入等待状态，先不断自旋将前驱节点状态置为`SIGINAL`，
而后调用`LockSupport.park()`方法将当前线程阻塞。整体示意图为下图：
![img](https://img-blog.csdnimg.cn/20181122144602212.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NpZmFuY2hhbw==,size_16,color_FFFFFF,t_70)

**获取锁成功并且节点出队的逻辑**

```java
// 当前节点前驱为头结点并且再次获取同步状态成功
if (p == head && tryAcquire(arg)) {
	//队列头结点引用指向当前节点 
	setHead(node); 
	//释放前驱节点 
	p.next = null; // help GC
	failed = false;
	return interrupted;
}

private void setHead(Node node) {
	head = node;
	node.thread = null;
	node.prev = null;
}
```

将当前节点通过`setHead()`方法设置为队列的头结点，然后将之前的头结点的next域设置为null并且pre域也为null，即与队列断开，无任何引用方便GC时能够将内存进行回收。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181122152148916.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NpZmFuY2hhbw==,size_16,color_FFFFFF,t_70)

**那么当节点在同步队列中获取锁失败的时候会调用`shouldParkAfterFailedAcquire()`方法**。此方法主要逻辑
是使用CAS将前驱节点状态由INITIAL置为SIGNAL，表示需要将当前节点阻塞。如果CAS失败，说明 `shouldParkAfterFailedAcquire()`方法返回false，然后会在`acquireQueued()`方法中的for (;;)死循环中不断自旋直到前驱节点状态置为SIGANL为止，返回true时才会执行方法 `parkAndCheckInterrupt()`方法。

```java
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        // 获取前驱节点的节点状态
        int ws = pred.waitStatus;
        if (ws == Node.SIGNAL)
            /*
             * This node has already set status asking a release
             * to signal it, so it can safely park.
             */
            return true;
            // 前驱节点已被取消
        if (ws > 0) {
            /*
             * Predecessor was cancelled. Skip over predecessors and
             * indicate retry.
             */
            // 不断重试直到找到一个前驱节点状态不为取消状态
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            /*
             * waitStatus must be 0 or PROPAGATE.  Indicate that we
             * need a signal, but don't park yet.  Caller will need to
             * retry to make sure it cannot acquire before parking.
             */
            // 前驱节点状态不是取消状态时，将前驱节点状态置为-1,
            // 表示后继节点应该处于等待状态
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }
```

`parkAndCheckInterrupt()`方法的源码为

```java
    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }
```

**该方法的关键是会调用`LookSupport.park()`方法，该方法是用来阻塞当前线程的。**

整体上看，`acquireQueued()`在自旋过程中主要完成了两件事情：

1. 如果当前节点的前驱节点是头节点，并且能够获得同步状态的话，当前线程能够获得锁该方法执行结束退出。
2. 获取锁失败的话，先将节点状态设置成SIGNAL，然后调用LookSupport.park方法使得当前线程阻塞。

# 独占式锁的获取过程也就是acquire()方法的执行流程

![img](https://img-blog.csdnimg.cn/20181122154713461.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NpZmFuY2hhbw==,size_16,color_FFFFFF,t_70)

# 独占锁的释放(release()方法)

独占锁的释放调用unlock方法，而该方法实际调用了AQS的release方法

```java
public void unlock() {    
	sync.release(1); 
}

public final boolean release(int arg) {    
	if (tryRelease(arg)) {        
		Node h = head;        
		if (h != null && h.waitStatus != 0)            
			unparkSuccessor(h);        
			return true;    
		}    
		return false; 
	}
}	
```

这段代码逻辑就比较容易理解了，如果同步状态释放成功（tryRelease返回true）则会执行if块中的代码，当head指向的头结点不为null，并且该节点的状态值不为0的话才会执行`unparkSuccessor()`方法。

```java
private void unparkSuccessor(Node node) {
        /*
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling.  It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        /*
         * Thread to unpark is held in successor, which is normally
         * just the next node.  But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
         // 头结点的后继节点 
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
        	 // 后继节点不为null时唤醒 
            LockSupport.unpark(s.thread);
    }
```

首先获取头节点的后继节点，当后继节点不为空的时候会调用`LookSupport.unpark()`方法，该方法会唤醒该节点的后继节点所包装的线程。因此，**每一次锁释放后就会唤醒队列中该节点的后继节点所引用的线程，从而进一步可以佐证获得锁的过程是一个FIFO（先进先出）的过程**。

# 独占式锁获取与释放总结

- **线程获取锁失败，将线程调用`addWaiter()`封装成Node进行入队操作。addWaiter()中`enq()`方法完成对同步队列的头节点初始化以及CAS尾插失败后的重试处理。**
- **入队之后排队获取锁的核心方法`acquireQueued()`，节点排队获取锁是一个自旋过程。当且仅当当前节点的前驱节点为头节点并且获取同步状态时，节点出队并且该节点引用的线程获取到锁。否则不满足条件时会不断自旋将前驱节点的状态置为SIGNAL后调用`LockSupport.part()`将当前线程阻塞。**
- **释放锁时会唤醒后继结点（后继结点不为null）。**

# 独占锁特性

#### 可中断式获取锁

可响应中断式锁可调用方法`lock.lockInterruptibly()`；而该方法其底层会调用AQS的`acquireInterruptibly()`方法。

```java
   /**
     * Acquires in exclusive mode, aborting if interrupted.
     * Implemented by first checking interrupt status, then invoking
     * at least once {@link #tryAcquire}, returning on
     * success.  Otherwise the thread is queued, possibly repeatedly
     * blocking and unblocking, invoking {@link #tryAcquire}
     * until success or the thread is interrupted.  This method can be
     * used to implement method {@link Lock#lockInterruptibly}.
     *
     * @param arg the acquire argument.  This value is conveyed to
     *        {@link #tryAcquire} but is otherwise uninterpreted and
     *        can represent anything you like.
     * @throws InterruptedException if the current thread is interrupted
     */
public final void acquireInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        if (!tryAcquire(arg))
        	    // 线程获取锁失败
            doAcquireInterruptibly(arg);
    }
```

在获取同步状态失败后就会调用`doAcquireInterruptibly()`方法

```java
    /**
     * Acquires in exclusive interruptible mode.
     * @param arg the acquire argument
     */
    private void doAcquireInterruptibly(int arg)
        throws InterruptedException {
        final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    throw new InterruptedException();
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
```

**获取锁响应中断原理与`acquire()`几乎一样，唯一区别在于当`parkAndCheckInterrupt()`返回true时表示线程阻塞时被中断，抛出中断异常后线程退出。**

# 超时等待式获取锁（tryAcquireNanos()方法）

通过调用`lock.tryLock(timeout,TimeUnit)`方式达到超时等待获取锁的效果，该方法会在三种情况下才会返回：

1. 在超时时间内，当前线程成功获取了锁；
2. 当前线程在超时时间内被中断；
3. 超时时间结束，仍未获得锁返回false。

该方法会调用AQS的方法`tryAcquireNanos()`，源码为：

```java
    /**
     * Attempts to acquire in exclusive mode, aborting if interrupted,
     * and failing if the given timeout elapses.  Implemented by first
     * checking interrupt status, then invoking at least once {@link
     * #tryAcquire}, returning on success.  Otherwise, the thread is
     * queued, possibly repeatedly blocking and unblocking, invoking
     * {@link #tryAcquire} until success or the thread is interrupted
     * or the timeout elapses.  This method can be used to implement
     * method {@link Lock#tryLock(long, TimeUnit)}.
     *
     * @param arg the acquire argument.  This value is conveyed to
     *        {@link #tryAcquire} but is otherwise uninterpreted and
     *        can represent anything you like.
     * @param nanosTimeout the maximum number of nanoseconds to wait
     * @return {@code true} if acquired; {@code false} if timed out
     * @throws InterruptedException if the current thread is interrupted
     */
    public final boolean tryAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        return tryAcquire(arg) ||
        	 // 实现超时等待的效果 
            doAcquireNanos(arg, nanosTimeout);
    }
```

最终是靠`doAcquireNanos()`方法实现超时等待的效果

```java
    /**
     * Acquires in exclusive timed mode.
     *
     * @param arg the acquire argument
     * @param nanosTimeout max wait time
     * @return {@code true} if acquired
     */
    private boolean doAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        if (nanosTimeout <= 0L)
            return false;
        final long deadline = System.nanoTime() + nanosTimeout;
        final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return true;
                }
                nanosTimeout = deadline - System.nanoTime();
                if (nanosTimeout <= 0L)
                    return false;
                if (shouldParkAfterFailedAcquire(p, node) &&
                    nanosTimeout > spinForTimeoutThreshold)
                    LockSupport.parkNanos(this, nanosTimeout);
                if (Thread.interrupted())
                    throw new InterruptedException();
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
```

**超时获取锁逻辑与可中断获取锁基本一致，获取锁失败后，增加了一个时间处理。如果当前时间超过截止时间，线程不在等待，直接退出，返回false。否则将线程阻塞置为等待状态排队获取锁。**

***原文链接***：<a href="https://blog.csdn.net/sifanchao/article/details/84343848">https://blog.csdn.net/sifanchao/article/details/84343848</a>