### 1、dome：

```java
public class Main {
    public static void main(String[] args) {

    }
    public void func() {
        synchronized (this) {
            System.out.println("java!!!");
        }
        System.out.println("hello world!");
    }
}
```

编译:

```txt
javac Main.java
```

报错：

```txt
D:\idea\sources\jvm\qqai\src\qqai\synchronizedtest>javac Main.java
Main.java:4: 错误: 编码 GBK 的不可映射字符 (0x97)
 * 鎻忚堪锛歴ynchronized鍏抽敭瀛?
                       ^
```

修改命令重新编译：

```
javac -encoding UTF-8 Main.java
```

成功，或者javap命令反编译：

```
javap -c -s -v -l Main.class
```

获得整个类的字节码文件：

```
......
 public void func();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter				// 这里就是synchronized关键字在代码块中能够锁定线程的原因，
         4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         7: ldc           #3                  // String java!!!
         9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        12: aload_1
        13: monitorexit					//完成后这个标志位表明释放这个线程锁
        14: goto          22
        17: astore_2
        18: aload_1
......
```

### 2、dome

```java
public class Main2 {
    public synchronized void func() {
        System.out.println("hello world");
    }
}
```

编译：

```
javac -encoding UTF-8 Main2.java
```

成功，或者javap命令反编译：

```
javap -c -s -v -l Main2.class
```

获得整个类的字节码文件：

```
......
 public synchronized void func();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED   //这时关键字修饰在方法上，ACC_SYNCHRONIZED这个标志就是表示这是一个被关键字修饰的同步代码。
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String hello world
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 12: 0
        line 13: 8
}
.......
```

### 3、总结

synchronized关键字，应用于方法和代码块上，如果方法或代码块加上了static修饰，synchronized关键字获取的就是这个**类锁**

如果没有static关键字，那么这个synchronized获取到的就是当前对象锁。

synchronized关键字在1.6有过优化，自旋锁，偏向锁，轻量级锁....   

synchronized和ReenTrantLock比较：

​	二者都是可重入锁

​	synchronized关键字依赖于jvm而ReenTrantLock依赖于Api

​	ReenTrantLock相对于synchronized关键字来说，添加了许多高级的功能，比如说<font color = red size = 4>等待可中断、可实现公平锁、可实现选择性通知（锁可以绑定多个条件）</font>

​	