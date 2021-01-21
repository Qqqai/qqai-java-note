# jvm

## 		jvm整体结构

​			![image-20200902174721220](https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082710.png)



##### `本地方法栈`：

> ##### 		有`native`关键字标记的方法，表示需要调用c++接口的方法，或者需要调用操作系统的方法

##### `程序计数器`：

> ##### 		 存储指向下一条指令的地址，也就是对应的即将执行的指令代码，对应的就是java代码中分号区分的每一行  如果方法是由native修饰的则程序计数器是空的   基本可以说  native修饰的方法不归jvm管

##### `方法区  现在有可能叫做元数据区` ：

> ​			 线程共享的 ，存储了每一个类的结构信息，例如运行时的常量池，字段，方法数据，构造函数，和普通方法的字节码内容，这里只是规范在不同的虚拟机里实现方式是不一样的，最典型的就是**永久代和原空间**， **但是 **：
>
> <font color=red size=4>		实例变量存在堆内存中，和方法区无关</font>

##### `堆和栈`：	<font color=blue size=3>栈管理运行，堆管理存储。</font>

##### `栈`： 

> ​		**栈也叫栈内存**，主管java程序的运行，是在**线程创建的时候创建，线程结束的时候回收**, <font color=red>对于栈来说，不存在垃圾回收的问题</font>，只要线程结束这个栈就回收。**生命周期和线程是一致的，对于栈来说线程都是私有的**, <font color=green size=4>8中基本类型的变量，对象的引用变量，实例方法，都是在栈内存中分配的。</font>

##### `栈帧`: 

> ##### 	***栈帧：在java的层面，方法就被放进虚拟机中之后就会转换成栈帧*** ，每一个方法的执行到执行完毕的过程就对应着方法在虚拟机中入栈和出栈的过程，每个方法被调用是自动进入入栈，就在栈顶，该方法执行完毕又会自动出栈。

> <font color=blue size = 4>			栈帧中主要保存的数据分为三类：</font>
>
> <font color=blue size = 4>						本地变量：输入的参数和输出的参数以及方法的内部变量</font>
>
> <font  color=blue size = 4>						栈操作：记录了出栈和入栈的操作</font>
>
> <font color=blue size = 4>						栈帧数据：包括类文件和方法等</font>

##### `栈溢出错误`： java.lang.StackOverflowError  栈溢出错误

​	![image-20200904194022155](https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082720.png)



##### `堆-栈方法区的关系`：

![image-20200904195225536](https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082723.png)

##### <font color=blue size=5>`堆（heap）`： </font>

<img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083154.png" alt="image-20200905221621307 " style="zoom:33%;" />

> ​				1、***新生代***：
>
> ​							1.1、<font color=red>伊甸园区（Eden Space）</font>
>
> ​										**新建的对象实例都会保存在伊甸园区中**
>
> ​										**伊甸园区有一定的阈值，当空间不足时，会发生一次GC就是垃圾清理，伊甸园区的GC也称为YGC也成为轻量								级的GC，这个时候Eden的内存基本清空，只有被调用的对象和静态的对象会被保留他们会进入幸存者0区**
>
> ​							1.2、<font color=red>幸存者0区 from区（ survivor 0 space）</font><font color=red>幸存者1区 to区（survivor 0 space）</font>
>
> ​										**当伊甸园区的内存进行一次GC之后会把没有杀死的对象放进from区中**
>
> ​									<font color=red>但是，幸存者0区和幸存者1区是不固定的，每次GC( <font color=green>此处GC不第一次GC所以此处表示的是伊甸园区和幸存者0区一起GC，GC之后存活的实例才会被放进To区，所以必有一个幸存者区是空的！！！</font>)之后谁是空的，谁就变成TO区也就是幸存者1区</font>
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083304.png" alt="image-20200905222420419 " style="zoom:50%;" />
>
> 

> ​				2、***老年代***：
>
> ​							 **当多次GC之后幸存者1区也被占满了之后，再次GC这时还没有被清楚的对象就会进入养老区就会进入养老区**
>
> ​							**当养老区内存被占满了，就会触发Full GC = FGC重量级的GC  清除养老区的内容**
>
> ​							**当养老区的内容实在无法弹出时久会抛出异常：<font color=red> java.lang.OutOfMemoryError   ==  OOM堆外内存溢出错误</font>**
>

> ​				3、***元空间***：
>
> ​							**永久存储区是一个常驻内存区域，用于存放JDK自身所携带的Class,Interface的元数据，也就是说它存储的是运行环境必须的类信息，被装载进此区域的数据是不会被垃圾回收器回收掉的，关闭JVM才会释放此区域所占用的内存。**
>

> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083254.png" alt="image-20200905224140979 " style="zoom:50%;" />



##### `堆参数调优: `

> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083215.png" alt="image-20200905224740509" style="zoom:33%;" />

> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083247.png" alt="image-20200905224858112" style="zoom:33%;" />

> ​						<font color=red>在测试环境中我们jvm的参数可以随意设置，但是在实际的开发项目中jvm最大空间和最小空间必须是一致的，因为<font color=green>需要避免jvm和应用程序争抢内存造成实际内存和理论内存不符合，内存忽高忽低造成抖动</font></font>  
>

> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083233.png" alt="image-20200905225125007" style="zoom:50%;" />

> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083322.png" alt="image-20200905225521861" style="zoom:50%;" />

> ##### `GC`:
>
> ​	**GC算法：分代收集算法**
>
> ###### 				**`分代收集算法：`** 
>
> ​							**次数上频繁收集Young区 ,<font color=red>复制计算</font>** 
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082751.png" alt="image-20200906150337065" style="zoom:40%;" />
>
> 
>
> ​							**次数上较少收集Old区，<font color=red>标记清除或者标记整理混合实现</font>**
>
> ​									
>
> ​							**基本不动元空间的内容**
>
> ###### 	`GC算法的总体概述：`
>
> ​					<img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082751.png" alt="image-20200906144145407" style="zoom:50%;" />
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083335.png" alt="image-20200906144322314" style="zoom:50%;" />
>
> ###### 	`GC的四大算法`：
>
> ​					引用计数法，***<font color=red>复制计算法，标记清除，标记压缩</font>***。
>
> ###### 	<font color=red>复制计算法</font>:
>
> ​						<font color=red>年轻代</font>使用的复制计算法，就是<font color=red>把伊甸园区和幸存者0区的对象复制到TO区</font>
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083443.png" alt="image-20200906150635519" style="zoom:50%;" />
>
> ​					 <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083343.png" alt="image-20200906150930920" style="zoom:50%;" />
>
> ​	***<font color=red>标记清除</font>***：
>
> ​					先标记，再清楚，但是内存碎片化了，不利于对象的调用，而且会扫描两次才能清除，耗时长
>
> ​					<img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082804.png" alt="image-20200906152019182" style="zoom:50%;" />
>

​	





1. ​	***程序 = 算法 + 数据结构***
2. ​	**程序 = 框架 + 业务逻辑 **
3. ​	***队列： FIFO  first input first output***
4. ​	***栈：FILO first input last output***





### class loader

> #### 	java字节码文件反编译命令： javap -verbose
>
> ​		加载class文件，class文件在文件开头有特定的文件标识，将class文件的字节码内容加载到内存中，并将这些内容转换成方法区中的运行时数据结构并且`ClassLoader`只负责class文件的加载，至于它是否可以运行，则由`Execution Engine`决定
>
> ##### 		种类： 
>
> ​		`BootstrapClassLoader` :  c++的加载器，事虚拟机的加载器也称为 根加载器  加载常用的很多类， 在jre下的rt.jar包中由完全定义  
>
> 跟加载器启动时，会自动找到这个 rt.jar---runtime.jar包下的所有的类全部加载进去
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011083352.png" alt="跟加载器 " style="zoom:50%;" />
>
> 
>
> `	AppClassLoader`：  应用程序类加载器，类加载起的前缀  sun.misc.Launcher 这个是jvm相关功能的入口程序
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082809.png" alt="image-20200902185248944 " style="zoom:50%;" />
>
> 
>
> `	ExtClassLoader`： 扩展类的加载器，加载关于javax包下的类
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082815.png" alt="image-20200902185645689 " style="zoom:50%;" />
>
> `	java.lang.ClassLoader`：用户程序加载器，  用户可以自定义类加载器  ClassLoader  抽象类只能被继承不能被实例化 所以这里可以继承这个类自定义自己的loader
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082817.png" alt="image-20200902191107803 " />
>
> 
>
> ## 双亲委派机制
>
> ​		在加载一个类的时候  是按照类加载器的顺序从上往下找的  先找跟加载器->扩展加载器-> 程序加载器-> 如果有自定义的classloader会找到这一步，  但是如果我们定义一个java.lang.String类的话，在这个我们定义的string类中由main方法但是这个类是由程序类加载器加载的， 然而 Jdk提供的string类是由跟加载器提供的 所以找到的会是jdk的string类  但这个类就没有main方法  所以会报错在:     
>
> ​		类 java.lang.String 中找不到 main 方法    
>
> 这就是所谓的沙箱安全问题
>
> ​	双亲委派机制就作用就是不会造成类污染，加载所有的类都会先去最顶级的类加载器先加载如果找不到才会调用字类的类加载器加载 所以只要上层类加载器找到了这个类子类的同名同包的类就不会被加载
>
> <img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082819.png" alt="image-20200902191522113 " style="zoom:50%;" />



##### `JMM内存原型`:

​			<img src="https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011082822.png" alt="image-20200906201850857" style="zoom:50%;" />

###### 			<font color=red>`volatile`</font> 关键字：

​					**加上这个关键字就表示在内存中这个参数对其他线程是共享的**

​					

```java
/**
 * 描述：JMM内存模型
 *
 * @author qqai
 * @createTime 2020-09-06 20:03
 */

public class JMMTestClass {
    //笔记 加上这个关键字就表示在内存中这个参数对其他线程是共享的 只要某一个线程中这个值被修改了 那么其他共用线程也会立即修改
    volatile int num = 0;
    
    public void changeNumTo1025() {
        this.num = 1025;
    }
    public static void main(String[] args) {
        JMMTestClass jmmTestClass = new JMMTestClass();
        new Thread(() -> {
            //新建线程 测试共享变量再修改之后会怎么进行数据读取
            try {
                Thread.sleep(3000);
                jmmTestClass.changeNumTo1025();
                System.out.println(Thread.currentThread().getName() + "->num->" + jmmTestClass.num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        //main线程执行
        while (jmmTestClass.num == 0) {
            System.out.println("main->num->0");
        }
        //共享变量的值被修改了
        System.out.println(Thread.currentThread().getName() + "->num" + jmmTestClass.num);
    }
}
	
```

