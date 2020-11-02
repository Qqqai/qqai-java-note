---
title: Java-IO
date: 2020-10-12
tags:
- Java
- IO
---

## I/O

### 分类

根据数据的流向分：输入流和输出流

- 输入流：把数据从其他设备上读取到**内存**中的流
- 输出流：把数据从**内存**读取到其他设备的流

根据数据的类型分：**字节流**和**字符流**

- 字节流：以**字节**为单位，读取数据的流

- 字符流：以**字符**为单位，读取数据的流

  ![截屏2020-07-02 下午7.08.35](https://typorastore.oss-cn-beijing.aliyuncs.com/img/20200702190836.png)

顶级父类

|        | 输入流                  | 输出流                   |
| ------ | ----------------------- | ------------------------ |
| 字节流 | `InputStream`字节输入流 | `OutputStream`字节输出流 |
| 字符流 | `Reader`字符输入流      | `Writer`字符输出流       |

### 字节流

一切文件数据(文本、图片、视频等)在存储时，都是以二进制数字的形式保存，都一个一个的字节，那么传输时一 样如此。所以，字节流可以传输任意文件数据。在操作流的时候，我们要时刻明确，无论使用什么样的流对象，底 层传输的始终为二进制数据。

#### 字节输出流[OutputStream]

`java.io.OutputStream` 抽象类是表示字节输出流的所有类的超类，将指定的字节信息写出到目的地。它定义了字

节输出流的基本共性功能方法

- `public void close()` :关闭此输出流并释放与此流相关联的任何系统资源。
- ` public void flush() `:刷新此输出流并强制任何缓冲的输出字节被写出。
- ` public void write(byte[] b)` :将 b.length字节从指定的字节数组写入此输出流。
- ` public void write(byte[] b, int off, int len)` :从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
- `public abstract void write(int b)` :将指定的字节输出流。

> close方法，当完成流的操作时，必须调用此方法，释放系统资源。

##### `FileOutputStream`类

`java.io.FileOutputStream` 类是文件输出流，用于将数据写出到文件。

1. 写出字节:` write(int b) `方法，每次可以写出一个字节数据，代码使用演示:

```java
 public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream("a.txt");
        fileOutputStream.write(97);
        fileOutputStream.write(97);
        fileOutputStream.write(97);
        fileOutputStream.close();
    }
// a.txt 
// aaa
```

2. 写出字节数组:` write(byte[] b)` ，每次可以写出数组中的数据，代码使用演示:

```java
public static void main(String[] args) throws IOException {
    FileOutputStream fileOutputStream=new FileOutputStream("a.txt");
    byte[] a="123456".getBytes();
    fileOutputStream.write(a);
    fileOutputStream.close();
}
// a.txt
// 123456
```

3.  写出指定长度字节数组:` write(byte[] b, int off, int len)` ,每次写出从off索引开始，`len`个字节，代码 使用演示:

```java
public static void main(String[] args) throws IOException {
    FileOutputStream fileOutputStream=new FileOutputStream("a.txt");
    byte[] a="123456".getBytes();
    fileOutputStream.write(a,0,3);
    fileOutputStream.close();
}
// a.txt
// 123
```

4. 追加续写

   ```java
   // 源码 第二个参数代表是否追加
   public FileOutputStream(String name, boolean append)
       throws FileNotFoundException
   {
       this(name != null ? new File(name) : null, append);
   }
   
   public static void main(String[] args) throws IOException {
           FileOutputStream fileOutputStream=new FileOutputStream("a.txt",true);
           byte[] a="123456".getBytes();
           fileOutputStream.write(a,0,3);
           fileOutputStream.close();
       }
   // a.txt
   // 123123
   ```

5. 换行

   ```java
   public static void main(String[] args) throws IOException {
       FileOutputStream fileOutputStream=new FileOutputStream("a.txt",true);
       byte[] a="123456".getBytes();
       fileOutputStream.write("\r".getBytes());
       fileOutputStream.write(a,0,3);
       fileOutputStream.close();
   }
   //  123123
   //  123
   ```

   > 回车符 \r 和换行符 \n : 回车符:回到一行的开头(return)。
   >
   > 换行符:下一行(newline)。 系统中的换行:
   >
   > Windows系统里，每行结尾是 回车+换行 ，即 \r\n ; 
   >
   > Unix系统里，每行结尾只有 换行 ，即 \n ;
   >
   > Mac系统里，每行结尾是 回车 ，即 \r 。从 Mac OS X开始与Linux统一。

#### 字节输入流[InputStream]

`java.io.InputStream `抽象类是表示字节输入流的所有类的超类，可以读取字节信息到内存中。它定义了字节输入流的基本共性功能方法。

- `public void close()` :关闭此输入流并释放与此流相关联的任何系统资源。
- `public abstract int read() `: 从输入流读取数据的下一个字节。
- `public int read(byte[] b) `: 从输入流中读取一些字节数，并将它们存储到字节数组 b中 。

##### `FileInputStream`类

`java.io.FileInputStream `类是文件输入流，从文件中读取字节。

1. 读取字节

   ```java
   public static void main(String[] args) throws IOException {
       InputStream inputStream=new FileInputStream("a.txt");
       int b = 0;
       while ((b=inputStream.read()) != -1) {
           System.out.println((char)b);
       }
       inputStream.close();
   }
   //2
   //3
   //1
   //2
   //3
   
   //1
   //2
   //3
   ```

   > 小贴士:
   >
   > 1. 虽然读取了一个字节，但是会自动提升为int类型。
   >
   > 2. 流操作完毕后，必须释放系统资源，调用close方法，千万记得。

2. 读取字节数组

   `read(byte[] b) `，每次读取b的长度个字节到数组中，返回读取到的有效字节个数，读

   取到末尾时，返回 -1 ，代码使用演示:

   ```java
   public static void main(String[] args) throws IOException {
           // 使用文件名称创建流对象.
           FileInputStream fis = new FileInputStream("a.txt"); // 文件中为abcde 
           // 定义变量，作为有效个数
           int len;
           // 定义字节数组，作为装字节数据的容器
           byte[] b = new byte[2];
           // 循环读取
           while ((len = fis.read(b)) != -1) {
               // 每次读取后,把数组的有效字节部分，变成字符串打印
               System.out.println(new String(b, 0, len));// len 每次读取的有效字节个数 }
               // 关闭资源 fis.close();
   
           }
       }
   ab
   cd
   e
   
   ```

   > 使用数组读取，每次读取多个字节，减少了系统间的IO操作次数，从而提高了读写的效率，建议开发中使 

3. 复制粘贴

   ```java
       // 使用文件名称创建流对象.
       FileInputStream fis = new FileInputStream("a.txt"); //abcde
       FileOutputStream ois=new FileOutputStream("b.txt");
       // 定义变量，作为有效个数
       int len;
       // 定义字节数组，作为装字节数据的容器
       byte[] b = new byte[2];
       // 循环读取
       while ((len = fis.read(b)) != -1) {
           // 读几个写几个
           ois.write(b,0,len);
       }
   
       // 关闭资源  先开的后关
       ois.close();
       fis.close();
   
   ```

   > 流的关闭原则:先开后关，后开先关。

### 字符流

当使用字节流读取文本文件时，可能会有一个小问题。就是遇到中文字符时，可能不会显示完整的字符，那是因为 一个中文字符可能占用多个字节存储。所以Java提供一些字符流类，以字符为单位读写数据，专门用于处理文本文 件。

#### 字符输入流[Reader]

`java.io.Reader` 抽象类是表示用于读取字符流的所有类的超类，可以读取字符信息到内存中。它定义了字符输入

流的基本共性功能方法。

- `public void close()` :关闭此流并释放与此流相关联的任何系统资源。
- `public int read()` : 从输入流读取一个字符。
- `public int read(char[] cbuf)` : 从输入流中读取一些字符，并将它们存储到字符数组 cbuf中 。

#####  `FileReader`类

`java.io.FileReader` 类是读取字符文件的便利类。构造时使用系统默认的字符编码和默认字节缓冲区。

> 1. 字符编码:字节与字符的对应规则。Windows系统的中文编码默认是GBK编码表。 idea中UTF-8
> 2. 字节缓冲区:一个字节数组，用来临时存储字节数据。

构造方法

`FileReader(File file)` : 创建一个新的` FileReader` ，给定要读取的File对象。

`FileReader(String fileName)` : 创建一个新的 `FileReader `，给定要读取的文件的名称。

当你创建一个流对象时，必须传入一个文件路径。类似于`FileInputStream` 。

1. 读取字符: read 方法，每次可以读取一个字符的数据，提升为`int`类型，读取到文件末尾，返回 -1 ，循环读 取，代码使用演示:

```java
 public static void main(String[] args) throws IOException {
        Reader reader=new FileReader("a.txt");
        int b;
        while ((b=reader.read()) != -1) {
            System.out.println((char)b);
        }
    }
```

> 小贴士:虽然读取了一个字符，但是会自动提升为`int`类型。

2. 使用字符数组读取:` read(char[] cbuf) `，每次读取b的长度个字符到数组中，返回读取到的有效字符个数， 读取到末尾时，返回 -1 ，代码使用演示:

   ```java
   public static void main(String[] args) throws IOException {
        Reader reader=new FileReader("a.txt");
        // 保存有效字符的个数
        int len;
        // 用来保存字符
        char[] b=new char[2];
        while ((len=reader.read(b)) != -1) {
            System.out.println(new String(b));
        }
    }
   ```

#### 字符输出流[Writer]

`java.io.Writer`抽象类是表示用于写出字符流的所有类的超类，将指定的字符信息写出到目的地。它定义了字节 输出流的基本共性功能方法。

- `void write(int c) `写入单个字符。
- `void write(char[] cbuf)` 写入字符数组。
- `abstract void write(char[] cbuf, int off, int len)` 写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
- `void write(String str)` 写入字符串。
- `void write(String str, int off, int len) `写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
- `void flush() `刷新该流的缓冲。
- ` void close() `关闭此流，但要先刷新它。

#####  `FileWriter`类

`java.io.FileWriter` 类是写出字符到文件的便利类。构造时使用系统默认的字符编码和默认字节缓冲区。

`FileWriter(File file`) : 创建一个新的` FileWriter`，给定要读取的File对象。

`FileWriter(String fileName) `: 创建一个新的 `FileWriter`，给定要读取的文件的名称。

写出字符: `write(int b)` 方法，每次可以写出一个字符数据，代码使用演示:

```java
 public static void main(String[] args) throws IOException {
        Writer writer=new FileWriter("c.txt");
        writer.write(97);  // a
        writer.write('1'); // 1
        writer.write('c'); // c
        writer.write(30000); // 写出第4个字符，中文编码表中30000对应一个汉字。
        //【注意】关闭资源时,与FileOutputStream不同。如果不关闭,数据只是保存到缓冲区，并未保存到文件。
        writer.close();
    }
```

>  【注意】关闭资源时,与`FileOutputStream`不同。如果不关闭,数据只是保存到缓冲区，并未保存到文件。

关闭和刷新

因为内置缓冲区的原因，如果不关闭输出流，无法写出字符到文件中。但是关闭的流对象，是无法继续写出数据 的。如果我们既想写出数据，又想继续使用流，就需要 flush 方法了。

- flush :刷新缓冲区，流对象可以继续使用。
- close :先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了。

```java
 public static void main(String[] args) throws IOException {
        Writer writer=new FileWriter("c.txt");
        writer.write(97);  // a
        writer.write('1'); // 1
        writer.write('c'); // c
        writer.write(30000); // 写出第4个字符，中文编码表中30000对应一个汉字。
        //【注意】关闭资源时,与FileOutputStream不同。如果不关闭,数据只是保存到缓冲区，并未保存到文件。
        writer.flush();// 刷新缓冲区，流对象可以继续使用。
        writer.write("123"); //继续写
        writer.write("22222");
        writer.close(); //关闭
        writer.write("a"); //java.io.IOException: Stream closed
        writer.close();
    }
```

> 小贴士:即便是flush方法写出了数据，操作的最后还是要调用close方法，释放系统资源。

写出其他数据

1. 写出字符数组 : `write(char[] cbuf)`和 `write(char[] cbuf, int off, int len) `，每次可以写出字符数 组中的数据，用法类似`FileOutputStream`，代码使用演示:

   ```java
   public static void main(String[] args) throws IOException {
       Writer writer=new FileWriter("c.txt");
       char[] c="123".toCharArray();
       writer.write(c); // 写字符数组
       String s="12312312";
       writer.write(s,0,3); // 写字符串
   
   
       writer.close();
   }
   ```

> 字符流，只能操作文本文件，不能操作图片，视频等非文本文件。
>
> 当我们单纯读或者写文本文件时 使用字符流 其他情况使用字节流

## BIO，NIO，AIO的区别

简答

- BIO：`Block IO `同步阻塞式 IO，就是我们平常使用的传统 IO，它的特点是模式简单使用方便，并发处理能力低。
- NIO：`Non IO `同步非阻塞 IO，是传统 IO 的升级，客户端和服务器端通过 Channel（通道）通讯，实现了多路复用。
- AIO：`Asynchronous IO` 是 NIO 的升级，也叫 NIO2，实现了异步非堵塞 IO ，异步 IO 的操作基于事件和回调机制。

详细回答

- **BIO (Blocking I/O):** 同步阻塞I/O模式，数据的读取写入必须阻塞在一个线程内等待其完成。在活动连接数不是特别高（小于单机1000）的情况下，这种模型是比较不错的，可以让每一个连接专注于自己的 I/O 并且编程模型简单，也不用过多考虑系统的过载、限流等问题。线程池本身就是一个天然的漏斗，可以缓冲一些系统处理不了的连接或请求。但是，当面对十万甚至百万级连接的时候，传统的 BIO 模型是无能为力的。因此，我们需要一种更高效的 I/O 处理模型来应对更高的并发量。
- **NIO (New I/O):** NIO是一种同步非阻塞的I/O模型，在Java 1.4 中引入了NIO框架，对应` java.nio `包，提供了 Channel , Selector，Buffer等抽象。NIO中的N可以理解为Non-blocking，不单纯是New。它支持面向缓冲的，基于通道的I/O操作方法。 NIO提供了与传统BIO模型中的 `Socket` 和 `ServerSocket` 相对应的 `SocketChannel` 和 `ServerSocketChannel` 两种不同的套接字通道实现,两种通道都支持阻塞和非阻塞两种模式。阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反。对于低负载、低并发的应用程序，可以使用同步阻塞I/O来提升开发速率和更好的维护性；对于高负载、高并发的（网络）应用，应使用 NIO 的非阻塞模式来开发
- **AIO (Asynchronous I/O):** AIO 也就是 NIO 2。在 Java 7 中引入了 NIO 的改进版 NIO 2,它是异步非阻塞的IO模型。异步 IO 是基于事件和回调机制实现的，也就是应用操作之后会直接返回，不会堵塞在那里，当后台处理完成，操作系统会通知相应的线程进行后续的操作。AIO 是异步IO的缩写，虽然 NIO 在网络操作中，提供了非阻塞的方法，但是 NIO 的 IO 行为还是同步的。对于 NIO 来说，我们的业务线程是在 IO 操作准备好时，得到通知，接着就由这个线程自行进行 IO 操作，IO操作本身是同步的。查阅网上相关资料，我发现就目前来说 AIO 的应用还不是很广泛，Netty 之前也尝试使用过 AIO，不过又放弃了。