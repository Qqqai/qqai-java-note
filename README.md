# qqai-java-note
毕业提升自己，做的关于java的复习，包名类名可能不太好看，但是我认为很多东西写的还是很详细的
关于md笔记和mmap文档这些大部分属于直接抄袭，部分自己总结，不过抄袭的我会注明出处，而且会在里面加上自己的理解。

###  springmvc不支持html后缀的文件，要不换成jsp要不在web.xml配置上下面这句话
```xml
    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.html</url-pattern>
    </servlet-mapping>
```
