#  pring5

## 1、Spring简介

### 1.1、Spring的概念		

Spring是分层的JavaSE/EE应用Full-stack轻量级开源框架，以Ioc（Inverse Of Control：控制反转）和AOP（Aspect Oriented Programming：面向切面编程）为核心。

​		提供了展现层SpringMVC和持久层SpringJDBCTemplate以及业务层事务管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的JavaEE企业应用开源框架。

### 1.2、Spring发展历程

1997年，IBM提出了EJB的思想
1998年，SUN制定开发标准规范EJB1.0
1999年，EJB1.1发布
2001年，EJB2.0发布
2003年，EJB2.1发布
2006年，EJB3.0发布

***Rod Johnson ( Spring之父)***
Expert One-to-One J2EEDesign and Development(2002)
阐述了J2EE使用EB开发设计的优点及解决方案
Expert One-to-One J2EEDevelopment withoutEJB(2004)
阐述了J2EE开发不使用EB的解决方式(Spring雏形)

*2017年9月份发布了Spring的最新版本Spring5.0通用版(GA)*

### 1.3、Spring的优势

1）方便解耦

​	通过Spring提供的loC容器，可以将对象间的依赖关系交由Spring进行控制，避免硬编码所造成的过度耦合。
​	用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。

2）AOP编程的支持

​	通过Spring的AOP功能，方便进行面向切面编程，许多不容易用传统OOP实现的功能可以通过AOP轻松实现。

3）声明式事务的支持

​	可以将我们从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活的进行事务管理，提高开发效率和质量。

4）方便程序的测试

​	可以用非容器依赖的编程方式进行几乎所有的测试工作，测试不再是昂贵的操作，而是随手可做的事情。

5）方便集成各种优秀框架

​	Spring对各种优秀框架(Struts、Hibernate、Hessian、Quart等）的支持。

6）降低JavaEE API的使用难度

​	Spring对JavaEEAPI(如JDBC、JavaMail、远程调用等）进行了薄薄的封装层，使这些API的使用难度大为降低

7) Java源码是经典学习范例

​	Spring的源代码设计精妙、结构清晰、匠心独用，处处体现着大师对Java设计模式灵活运用以及对Java技术的高深造诣。它的源代码无意是Java技术的最佳实践的范例。

### 1.4、Spring的体系结构

![image-20201001090526160](C:\Users\ShenLijun\AppData\Roaming\Typora\typora-user-images\image-20201001090526160.png)

## 2、Spring快速入门

### 2.1、Spring程序开发步骤

1. Maven工程----->在pom.xml中导入Spring开发的基本包坐标

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.2.8.RELEASE</version>
   </dependency>
   ```

   

2. 编写Dao接口和实现类

   UserDao

   ```java
   public interface UserDao {
       void save();
   }
   ```

   UserDaoImpl

   ```java
   public class UserDaoImpl implements UserDao {
       public void save() {
           System.out.println("Save running!");
       }
   }
   ```

3. 创建Spring核心配置文件

   Maven工程----->在resources文件夹下创建Spring核心配置文件"applicationContext.xml"

   ![image-20201011091012662](https://qqai-note.oss-cn-beijing.aliyuncs.com/20201011091012.png)

   在applicationContext.xml文件中导入Spring-beans的约束

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
   
   </beans>
   ```

4. 在Spring配置文件中配置Bean(实体类、实现类)

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean id="userDaoImpl" class="com.accp.dao.UserDaoImpl"></bean>
   
   </beans>
   ```

5. 使用Spring的API获得Bean实例

   ```java
   public class MyTest {
   
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
   
           //从context中获取bean对象
           //方式一：
           UserDao daoImpl1 = context.getBean("userDaoImpl", UserDaoImpl.class);
           daoImpl1.save();
   
           //方式二：
           UserDao daoImpl2 = (UserDaoImpl) context.getBean("userDaoImpl");
           daoImpl2.save();
       }
   }
   ```

   

## 3、Spring配置文件详解

### 3.1、Bean标签基本配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="userDaoImpl" class="com.accp.dao.UserDaoImpl"></bean>
    
</beans>
```

用于配置对象交由Spring来创建。
默认情况下它调用的是类中的无参构造函数，如果没有无参构造函数则不能创建成功。

基本属性:

- **id**: Bean实例在Spring容器中的唯一标识
- **class**: Bean的全限定名称

### 3.2、Bean的作用域

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userDaoImpl" class="com.accp.dao.UserDaoImpl" scope="prototype"></bean>

</beans>
```

**scope**：指对象的作用范围

| 取值范围       | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| singleton      | 默认值，单例的                                               |
| prototype      | 多例的                                                       |
| request        | WEB项目中，Spring创建一个Bean的对象，将对象存入到request域中 |
| session        | WEB项目中，Spring创建一个Bean的对象，将对象存入到session域中 |
| global session | WEB项目中，应用在Portlet环境，如果没有Portlet环境那么globalSession相当<br/>于session |

**1）当scope的取值为singleton时**

Bean的实例化个数：1个
Bean的实例化时机:当Spring核心文件被加载时，实例化配置的Bean实例
Bean的生命周期：
	对象创建：当应用加载，创建容器时，对象就被创建了
	对象运行：只要容器在，对象一直活着
	对象销毁：当应用卸载，销毁容器时，对象就被销毁了



**2）当scope的取值为prototype**

Bean的实例化个数：多个
Bean的实例化时机:当调用getBean0方法时实例化Bean
	对象创建：当使用对象时，创建新的对象实例
	对象运行：只要对象在使用中，就一直活着
	对象销毁：当对象长时间不用时，被Java的垃圾回收器回收了

### 3.3、Bean声明周期配置

- init-method：指定类中的初始化方法名称
- destroy-method：指定类中销毁方法名称

在Bean对象中编写两个任意名称的方法，一个在初始化对象时执行，一个在对象销毁时执行

```java
public class UserDaoImpl implements UserDao {
    public void init(){
        System.out.println("对象初始化方法");
    }

    public void destory(){
        System.out.println("对象销毁方法");
    }
}
```

在applicationContext.xml文件中进行配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="userDaoImpl" class="com.accp.dao.UserDaoImpl" scope="singleton" init-method="init" destroy-method="destory"></bean>

</beans>
```

测试

```java
@Test
public void test2(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    //需要手动关闭才能看到方法的销毁执行
    ((ClassPathXmlApplicationContext) context).close);
}
```



### 3.4、Bean实例化三种方式

##### 工厂**静态**方法实例化

编写静态工厂类及方法

```java
public class StaticFactory {
    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
```

配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean id="userDaoImpl" class="com.accp.factory.StaticFactory" factory-method="getUserDao"></bean>

</beans>
```

##### 无参构造方法实例化

编写一个类（UserDaoImpl.java）

```java
public class UserDaoImpl  {
    
    public UserDaoImpl(){
        System.out.println("UserDaoImpl被创建了");
    }
}

```

配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userDaoImpl" class="com.accp.dao.UserDaoImpl"></bean>

</beans>
```

##### 工厂**实例**方法实例化

编写工厂实例类

```java
public class DynameicFactory {
    
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
```

配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean id="dynameicFactory" class="com.accp.factory.DynameicFactory"></bean>
        <bean id="userDaoImpl" factory-bean="dynameicFactory" factory-method="getUserDao"></bean>

</beans>
```

### 3.5、依赖注入（DI）

#### 3.5.1、依赖注入概念

依赖注入(Dependency Injection)：它是Spring框架核心IOC的具体实现。

IOC解耦只是降低他们的依赖关系，但不会消除。例如:业务偿仍会调用持久层的方法。
在编写程序时，通过控制反转，把对象的创建交给了Spring,但是代码中不可能出现没有依赖的情况。

那这种业务层和持久层的依赖关系，在使用Spring之后，就让Spring来维护了。
简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己去获取。

#### 3.5.2、注入对象

将Dao层对象注入到Service层的两种方式

- **构造方法**
- **set方法**



**set注入**

 Service层

```java
public class UserServiceImpl implements UserService {

    //通过set方法注入
    private UserDaoImpl userDao;
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
    }
}
```

**还可以使用p命名空间注入**

需要先引入p命名空间：xmlns:p="http://www.springframework.org/schema/p"

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userDao" class="com.accp.dao.UserDaoImpl"></bean>

    <bean id="userService" class="com.accp.service.UserServiceImpl" p:userDao-ref="userDao"></bean>
</beans>
```

**通过构造方法注入**

```java
public class UserServiceImpl implements UserService {

    //通过构造方法注入
    private UserDaoImpl userDao;
    public  UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
    }
}
```

配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userDao" class="com.accp.dao.UserDaoImpl"></bean>

    <bean id="userService" class="com.accp.service.UserServiceImpl">
        <constructor-arg ref="userDao"/>
    </bean>

</beans>
```

#### 3.5.3、Bean的依赖注入数据类型

注入除了引用Bean注入还可以注入普通数据类型、引用数据类型、集合。

注入数据的三种数据类型

- 普通数据类型
- 引用数据类型
- 集合数据类型



**普通数据类型注入**

创建一个实体类：Person.java    

```java
public class Person {
    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
```

配置applicationContext.xml注入属性值

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="person" class="com.accp.pojo.Person">
        <property name="name" value="孙奕秋"/>
        <property name="age" value="22"/>
    </bean>

</beans>
```

 **List、Map、Properties注入**

创建User.java

```java
public class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

创建Person.java

```java
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {
    private List<String> stringList;
    private Map<String, User> personMap;
    private Properties properties;

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, User> getPersonMap() {
        return personMap;
    }

    public void setPersonMap(Map<String, User> personMap) {
        this.personMap = personMap;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Person{" +
                "stringList=" + stringList +
                ", \npersonMap=" + personMap +
                ", \nproperties=" + properties +
                '}';
    }
}
```

配置applicationContext.xml进行值注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--创建两个User对象-->
    <bean id="user1" class="com.accp.pojo.User">
        <property name="name" value="克里斯"/>
        <property name="age" value="30"/>
    </bean>
    <bean id="user2" class="com.accp.pojo.User">
        <property name="name" value="曲忠辉"/>
        <property name="age" value="50"/>
    </bean>

    <bean id="person" class="com.accp.pojo.Person">
        <!--List集合注入-->
        <property name="stringList">
            <list>
                <value>孙奕秋</value>
                <value>吴恪之</value>
                <value>林宇明</value>
            </list>
        </property>
        <!--Map集合注入-->
        <property name="personMap">
            <map>
                <entry key="演员1" value-ref="user1"></entry>
                <entry key="演员2" value-ref="user2"></entry>
            </map>
        </property>
        <!--Properties注入-->
        <property name="properties">
            <props>
                <prop key="driver">com.mysql.jdbc.Driver</prop>
                <prop key="url">jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8</prop>
                <prop key="username">root</prop>
                <prop key="password">123456</prop>
            </props>
        </property>
    </bean>

</beans>
```

#### 3.5.4、引入其他配置文件（分模块开发）




实际开发中，Spring的配置内容非常多，这就导致Spring配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件中，而在Spring主配置文件通过import标签进行加载

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
		
    	<!--引入其他配置文件-->
		<import resource="app.xml"/>
    
</beans>
```

引入properties类型的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--引入Propertis配置文件-->
    <context:property-placeholder location="jdbc.properties"/>
</beans>
```

**<u>Spring的重点配置</u>**

```xml
<bean>标签
    id属性:在容器中Bean实例的唯一标识，不允许重复
    class属性:要实例化的Bean的全限定名
    scope属性:Bean的作用范围，常用是singleton(默认)和prototype
    <property>标签:属性注入
        name属性:属性名称
        value属性:注入的普通属性值
        ref属性:注入的对象引用值
    <list>标签
    <map>标签
    <properties>标签
    <constructor-arg>有参构造函数标签
<import>标签:导入其他的spring的分文件
```

## 4、Spring相关API

### 4.1、ApplicationContext的集成体系

**ApplicationContext**：接口类型，代表应用上下文，可以通过其实例获得Spring容器中的Bean对象

### 4.2、ApplicationContext的实现类

- ClassPathXmlApplicationContext
  它是从类的根路径下加载配置文件推荐使用这种

- FileSystemXmlApplicationContext
  它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置。

-  AnnotationConfigApplicationContext
  当使用注解配置容器对象时，需要使用此类来创建spring容器。它用来读取注解。

  

  ```java
  public static void main(String[] args) {
  
      //加载xml配置文件   路径为项目下的相对路径   推荐使用
      ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml");
  
      //加载注解配置类 
      ApplicationContext context2 = new AnnotationConfigApplicationContext("com.accp.service");
  
      //加载xml配置文件  路径为系统的绝对路径   不推荐使用
      ApplicationContext context3 = new  	        FileSystemXmlApplicationContext("E:\\development\\IdeaProjects\\Spring_01\\src\\main\\resources\\beans.xml");
  
  }
  ```

  

### 4.3、getBean()的使用

```java
public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    //指定id和类型，不需要进行类型转换
    Person person1 = context.getBean("person", Person.class);

    //指定id，需要进行类型转换
    Person person2 = (Person) context.getBean("person");

    //指定类型，不需要进行类型转换，前提是Beans容器中只有一个Person类型的bean
    Person person3 = context.getBean(Person.class);

}
```



## 5、Spring配置数据源

### 5.1、数据源（连接池）的作用

- 数据源(连接池)是提高程序性能如出现的

- 事先实例化数据源，初始化部分连接资源

- 使用连接资源时从数据源中获取

- 使用完毕后将连接资源归还给数据源

  

常见的数据源(连接池):**DBCP**、**C3PO**、**BoneCP**、**Druid**等

### 5.2、手动配置数据源（C3P0）

导入坐标（jar包）

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.35</version>
</dependency>

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
</dependency>

<dependency>
    <groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.5.1</version>
</dependency>
```

编写Java代码

```java
@Test   //创建c3p0数据源
public void test1() throws Exception {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    dataSource.setUser("root");
    dataSource.setPassword("123456");

    Connection connection = dataSource.getConnection();
    System.out.println(connection);
    connection.close();
    System.out.println(connection);
}
```

### 5.3、手动配置数据源（druid）

导入坐标（jar包）

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.35</version>
</dependency>

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
</dependency>

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.10</version>
</dependency>
```

编写Java代码

```java
@Test   //创建druid数据源
public void test2() throws Exception {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
    druidDataSource.setUsername("root");
    druidDataSource.setPassword("123456");

    DruidPooledConnection connection = druidDataSource.getConnection();
    System.out.println(connection);
    connection.close();
    System.out.println(connection);
}
```

### 5.4、使用配置文件配置数据源连接参数

Maven工程---->在resources文件夹下新建**jdbc.properties**文件,并补充以下代码

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306
jdbc.username=root
jdbc.password=123456
```

编写Java代码

```java
@Test   //创建c3p0数据源  加载Properties配置文件
public void test3() throws Exception {
    //读取配置文件
    ResourceBundle rb = ResourceBundle.getBundle("jdbc");
    String driver = rb.getString("jdbc.driver");
    String url = rb.getString("jdbc.url");
    String username = rb.getString("jdbc.username");
    String password = rb.getString("jdbc.password");

    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass(driver);
    dataSource.setJdbcUrl(url);
    dataSource.setUser(username);
    dataSource.setPassword(password);

    Connection connection = dataSource.getConnection();
    System.out.println(connection);
    connection.close();
    System.out.println(connection);
}
```

### 5.5、使用Spring配置数据源

导入坐标（jar包）----使用C3P0数据源为例，druid也一样

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.35</version>
</dependency>

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
</dependency>

<dependency>
    <groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.5.1</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.8.RELEASE</version>
</dependency>
```

配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Driver"/>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"/>
        <property name="user" value="root"/>
        <property name="password" value="123456"/>
    </bean>
</beans>
```



为了更加解耦，可以通过applicationContext.xml文件引用jdbc.properties来配置数据源

编写jdbc.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306
jdbc.username=root
jdbc.password=123456
```

配置beans.xml，引入jdbc.properties

applicationContext.xml引入properties需要引入约束

```
xmlns:context="http://www.springframework.org/schema/context"
http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    
	<!--引入jdbc.properties-->  
    <context:property-placeholder location="jdbc.properties"/>

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
</beans>
```



## 6、Spring注解开发

### 6.1、注解的优势

Spring是轻代码而重配置的框架，配置比较繁重，影响开发效率，所以注解开发是一种趋势，注解代替xml配置文件可以简化配置，提高开发效率。

### 6.2、原始注解

Spring原始注解主要是替代<Bean>的配置

| 注解           | 说明                                           |
| -------------- | ---------------------------------------------- |
| @Component     | 使用类上用于实例化Bean                         |
| @Controller    | 使用在web层用于实例化Bean                      |
| @Service       | 使用在Service上用于实例化Bean                  |
| @Repository    | 使用在Dao层上用于实例化Bean                    |
| @Autowired     | 使用在字段上用于根据类型依赖注入               |
| @Qualifier     | 结合@Autowried一起使用用于根据名称进行依赖注入 |
| @Resource      | 相当于@Autowired+@Qualifier,按照名称进行注入   |
| @Value         | 注入普通属性                                   |
| @Scope         | 标注的Bean的作用范围                           |
| @PostConstruct | 使用在方法上标注该方法是Bean的初始化方法       |
| @PreDestroy    | 使用在方法上标注该方法时Bean的销毁方法         |

注解的使用

dao层

```java
//@Component("userDao")
@Repository("userDao")	//实例化UserDaoImpl并赋予bean的id为userDao
public class UserDaoImpl implements UserDao {

    public void save() {
        System.out.println("save running!");
    }
}
```

service层

```java
//@Component("userService")
@Service("userService")	//实例化UserServiceImpl并赋予bean的id为userService
@Scope("singleton")	//设置该bean的作用域为单例模式
public class UserServiceImpl implements UserService {

    //@Autowired
    //@Qualifier("userDao")		
    @Resource(name="userDao")		//@Autowired + @Qualifier("userDao")	= @Resource(name="userDao")
    private UserDao userDao;

    @Value("${jdbc.url}")	//为driver赋值为配置文件的jdbc.url值，前提是在beans.xml中引入这个配置文件
    private String driver;

    public void save() {
        System.out.println(driver);
        userDao.save();
    }

    @PostConstruct		//相当于init-method 在对象初始化时执行的方法
    public void init(){
        System.out.println("UserServiceImpl创建初始化方法");
    }

    @PreDestroy		//相当于destory-method 在对象销毁时执行的方法
    public void destory(){
        System.out.println("UserServiceImpl销毁方法");
    }
}
```

applicationContext.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--配置组件扫描：扫描com.accp包下的所有bean-->
    <context:component-scan base-package="com.accp"/>	

</beans>
```

注解注意事项：

- 使用注解为属性赋值时可以不用封装属性的setter方法
- 使用注解完成后还应该在applicationContext.xml中配置组件扫描，这样注解才能使用生效
- 如果想看到销毁方法执行，那么需要手动关闭ClassPathXmlApplicationContext对象



### 6.3、新注解

| 注解            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| @Configuration  | 用于指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解 |
| @ComponentScan  | 用于指定Spring在初始化容器时要扫描的包。<br/>作用和在Spring的xml配置文件中的<br/><context:component-scan base-package="com.itheima"/>—样 |
| @Bean           | 使用在方法上，标注将该方法的放回值存储到Spring容器中         |
| @PropertySource | 用于加载.properties文件中的配置                              |
| @Import         | 用于导入其他配置类                                           |

新注解的使用

```java
@Import({DataSourceConfiguration.class})	//导入DataSourceConfiguration配置类
@Configuration      //该标志是Spring的核心配置类
@ComponentScan("com.accp")  //==  <context:component-scan base-package="com.accp"/>
@PropertySource("classpath:jdbc.properties")    //加载配置文件
public class SpringConfiguration {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean("dataSource")		//将该方法的放回值存储到Spring容器中
    public DataSource getDataSource() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
```

测试

```java
@Test
public void test1() throws SQLException {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    DataSource dataSource = context.getBean("dataSource", DataSource.class);
    Connection connection = dataSource.getConnection();
    System.out.println(connection);

    //手动关闭容器---这样才能看到bean的销毁方法执行
    ((AnnotationConfigApplicationContext) context).close();
}
```

注意：加载Spring注解配置类用AnnotationConfigApplicationContext实现类

## 7、Spring整合Junit

原始Junit测试Spring的问题
在测试类中，每个测试方法都有以下两行代码:

```java
Applicationcontext ac = new ClassPathXmlApplicationcontext("applicationContext.xml");
Accountservice as = ac.getBean("accountservice",Accountservice.class);
```

这两行代码的作用是获取容器，如果不写的话，直接会提示空指针异常。所以又不能轻易删掉。

上述问题解决思路：Spring集成Junit

### 7.1、Spring集成Junit

让SpringJunit负责创建Spring容器，但是需要将配置文件的名称告诉它将需要进行测试Bean直接在测试类中进行注入

#### 7.1.1、 Spring集成Junit步骤

1. 导入spring集成Junit的坐标

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-test</artifactId>
       <version>5.0.8.RELEASE</version>
   </dependency>
   
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.13</version>
       <scope>test</scope>
   </dependency>
   ```

   

2. 使用@Runwith注解替换原来的运行期

3. 使用@contextconfiguration指定配置文件或配置类

4. 使用@Autowired注入需要测试的对象

5. 创建测试方法进行测试

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   //@ContextConfiguration("classpath:beans.xml")
   @ContextConfiguration(classes = SpringConfigruation.class)	
   public class MyTest {
   
       @Autowired
       private UserService userService;
   
       @Test
       public void test(){
           userService.save();
       }
   }
   ```

使用说明：

- @ContextConfiguration("classpath:applicationContext.xml") 是加载xml配置文件
- @ContextConfiguration(classes = SpringConfigruation.class)是加载注解实现类
- @Autowired在需要测试的bean对象上添加

## 8、AOP

### 8.1、AOP简介

**AOP**为Aspect Oriented Programming的缩写，意思为**面向切面编程**，是通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。


AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的**耦合度降低**，**提高程序的可重用性**，同时**提高了开发的效率**。

### 8.2、AOP的作用及其优势

作用：在程序运行期间，在不修改源码的情况下对方法进行功能增强
优势：减少重复代码，提高开发效率，并且便于维护

### 8.3、AOP的底层实现

实际上，AOP的底层是通过Spring提供的的动态代理技术实现的。在运行期间，Spring通过动态代理技术动态的生成代理对象，代理对象方法执行时进行增强功能的介入，在去调用目标对象的方法，从而完成功能的增强。

### 8.4、AOP的动态代理技术

常用的动态代理技术

- JDK代理：基于接口的动态代理技术

  ```java
  import java.lang.reflect.InvocationHandler;
  import java.lang.reflect.Method;
  import java.lang.reflect.Proxy;
  
  /**
   * 描述：动态代理
   *
   * @author qqai
   * @createTime 2020-08-21 22:21
   */
  
  public class DynamicProxy implements InvocationHandler {
  
      private Object object;  //笔记  被代理的对象
  
      /**
       * 笔记  构造器 实例化被代理的对象
       *
       * @param object
       */
      public DynamicProxy(Object object) {
          super();
          this.object = object;
      }
  
      /**
       * 笔记 增强方法
       *
       * @param proxy  代理对象
       * @param method 代理类通过该接口继承方法，调用哪个方法久会哪个方法久会进入这个代理  这个method就代表那个方法  值是方法名 反射方式获取到方法执行结果
       * @param args   参数
       * @return
       * @throws Throwable
       */
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          System.out.println("增强前");
          Object re = method.invoke(object, args);//代理对象的方法和参数
          System.out.println("代理后");
  //        System.out.println(re);  //如果这个代理的方法由返回值 可以在这里获得到
          return re;
      }
  
      /**
       * 笔记 提供给外界的获取代理后对象的对象
       *
       * @return
       */
      public Object getObject() {
          //object.getClass().getClassLoader()(代理对象的类加载器), object.getClass().getInterfaces()(指定的接口), this(本类)
          return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
      }
  
  }
  ```

- cglib（第三方）：基于父类的动态代理技术

  ```java
  import org.springframework.cglib.proxy.Enhancer;
  import org.springframework.cglib.proxy.MethodInterceptor;
  import org.springframework.cglib.proxy.MethodProxy;
  
  import java.lang.reflect.Method;
  
  /**
   * @author qqai
   * @createTime 2020/9/28 14:56
   * @description：测试spring自带的cglib动态代理
   */
  
  public class TestCglib {
      public static void main(String[] args) {
          //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
  //        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\idea\\sources\\jvm\\qqai\\spring\\src\\main\\java\\spring");
  
          //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
          Enhancer enhancer = new Enhancer();
          //设置目标类的字节码文件
          enhancer.setSuperclass(Dog.class);
          //设置回调函数
          enhancer.setCallback(new MyMethodInterceptor());
  
          //这里的creat方法就是正式创建代理类
          Dog proxyDog = (Dog) enhancer.create();
          //调用代理类的eat方法
          proxyDog.eat();
          proxyDog.run("kitty");
      }
  
  }
  
  class MyMethodInterceptor implements MethodInterceptor {
  
      @Override
      public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
          System.out.println("这里是对目标类进行增强！！！");
          //注意这里的方法调用，不是用反射哦！！！
          Object object = proxy.invokeSuper(obj, args);
          System.out.println(object);//方法执行返回值
          return object;
      }
  }
  
  class Dog {
  
      final public void run(String name) {
          System.out.println("狗" + name + "----run");
      }
  
      public int eat() {
          System.out.println("狗----eat");
          return 1;
      }
  }
  ```

#### 基于JDK的动态代理技术

创建目标对象接口（TargetInstance）

```java
public interface TargetInstance {
    void save();
}
```

创建TargetInstance接口实现类

```java
public class Target implements TargetInstance{
    public void save() {
        System.out.println("save running !");
    }
}
```

创建增强对象（Advice）

```java
public class Advice {

    public void before() {
        System.out.println("增强前");
    }

    public void after() {
        System.out.println("增强后");
    }
}
```

实现动态代理并测试

```java
public class Test {

    public static void main(String[] args) {
        final Target target = new Target();
        final Advice advice = new Advice();

        TargetInstance proxy = (TargetInstance) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),  //目标对象类加载器
                target.getClass().getInterfaces(),   //目标对象接口类加载器
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        advice.before();
                        Object invoke = method.invoke(target, args);
                        advice.after();
                        return invoke;
                    }
                }
        );
        proxy.save();
    }
}
```



#### 基于cjlib的动态代理技术

先导入cjlib坐标---Spring-context集成cjlib

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.8.RELEASE</version>
</dependency>
```

创建目标对象（Target）

```java
public class Target {
    public void save() {
        System.out.println("save running !");
    }
}
```

创建增强对象（Advice）

```java
public class Advice {
    public void before(){
        System.out.println("增强前");
    }

    public void  after(){
        System.out.println("增强后");
    }
}
```

实现动态代理并测试

```java
public class Test {

    public static void main(final String[] args) {
        final Target target = new Target();
        final Advice advice = new Advice();

        //1.创建增强器
        Enhancer enhancer = new Enhancer();

        //2.设置父类(目标)
        enhancer.setSuperclass(Target.class);

        //3.设置回调
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //执行前置
                advice.before();
                //执行目标
                Object invoke = method.invoke(target, args);
                //执行后置
                advice.after();
                return invoke;
            }
        });

        //4.创建代理对象
        Target o = (Target) enhancer.create();
        o.save();
    }
}
```

两种动态代理的特点：

- JDK的动态代理实现依赖于接口
- cjlib的动态代理实现依赖于父类



### 8.5、AOP相关概念

Spring的AOP实现底层就是对上面的动态代理的代码进行了封装，封装后我们只需要对需要关注的部分进行代码编写，并通过配置的方式完成指定目标的方法增强。

在正式讲解AOP的操作之前，我们必须理解AOP的相关术语，常用的术语如下:

- Target(目标对象)︰代理的目标对象
- Proxy(代理)∶一个类被AOP织入增强后，就产生一个结果代理类
- Pointcut(切入点)∶所谓切入点是指我们要对哪些Joinpoint进行拦截的定义
- Joinpoint(连接点)︰所谓连接点是指那些被拦截到的点。在spring中,这些点指的是方法，因为spring只支持方法类型的连接点
- Advice(通知/增强）∶所谓通知是指拦截到Joinpoint之后所要做的事情就是通知
- Aspect(切面)︰是切入点和通知(引介)的结合
- Weaving(织入)︰是指把增强应用到目标对象来创建新的代理对象的过程。spring采用动态代理织入，而AspectJ采用编译期织入和类装载期织入



### 8.6、AOP开发明确事项

**需要编写的内容**

- 编写核心业务代码（目标类的目标方法)

- 编写切面类，切面类中有通知(增强功能方法)

- 在配置文件中，配置织入关系，即将哪些通知与哪些连接点进行结合

  

**AOP技术的实现内容**

​		Spring框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行。



**AOP底层使用哪种代理技术**

​	  在spring中，框架会根据目标类是否实现了接口来决定采用哪种动态代理的方式

### 8.7、基于XML的AOP开发

#### **快速入门**

1. 导入AOP相关坐标

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.2.8.RELEASE</version>
   </dependency>
   
   <dependency>
       <groupId>org.aspectj</groupId>
       <artifactId>aspectjweaver</artifactId>
       <version>1.8.4</version>
       <scope>runtime</scope>
   </dependency>
   ```

   

2. 创建目标接口和目标类（内部有切点)

   目标接口：TargetInstance

   ```java
   public interface TargetInstance {
       void save();
   }
   ```

   目标类：Target

   ```java
   public class Target implements TargetInstance{
   
       public void save() {
           System.out.println("save running !");
       }
   }
   ```

   

3. 创建切面类（内部有增强方法)：MyAspect

   ```java
   public class MyAspect {
   
       public void before(){
           System.out.println("前置增强");
       }
   
       public void after(){
           System.out.println("后置增强");
       }
   }
   ```

   

4. 将目标类和切面类的对象创建权交给spring

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!-- 目标对象 -->
       <bean id="target" class="com.accp.aop.Target" init-method="init" destroy-method="destory"></bean>
   
       <!-- 切面对象 -->
       <bean id="myAspect" class="com.accp.aop.MyAspect"></bean>
   </beans>
   ```

   

5. 在applicationContext.xml中配置织入关系

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!-- 目标对象 -->
       <bean id="target" class="com.accp.aop.Target" init-method="init" destroy-method="destory"></bean>
   
       <!-- 切面对象 -->
       <bean id="myAspect" class="com.accp.aop.MyAspect"></bean>
   
       <!-- 配置织入：告诉spring框架 哪些方法(切点)需要进行哪些增强(前置，后置) -->
       <aop:config>
           <!-- 声明切面 -->
           <aop:aspect ref="myAspect">
               <!-- 切面：切点+通知 -->
               <aop:pointcut id="myref" expression="execution(public void com.accp.aop.Target.save())"/>
   			 <!-- 前置增强-->
               <aop:before method="before" pointcut-ref="myref" />
               <!-- 后置增强-->
               <aop:after method="after" pointcut-ref="myref" />
           </aop:aspect>
       </aop:config>
   
   </beans>
   ```

   

6. 测试代码

   ```java
   public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           TargetInstance target = context.getBean("target", TargetInstance.class);
           target.save();
   }
   ```



#### **切点表达式的写法**

表达式语法:

```
execution([修饰符] 返回值类型 包名.类名.方法名(参数))
```

- 访问修饰符可以省略
- 返回值类型、包名、类名、方法名可以使用星号*代表任意
- 包名与类名之间一个点.代表当前包下的类，两个点..表示当前包及其子包下的类
- 参数列表可以使用两个点..表示任意个数，任意类型的参数列表

例如

```
execution(public void com.accp.aop.Target.method())
execution (void com.accp.aop.Target.*(..))
execution (* com.accp.aop.*.*( ..))
execution(* com.accp. aop..*.*(..))
execution(* *..*.*(..))
```

#### 通知的类型

通知的配置语法:

```
<aop:通知类型method=“切面类中方法名” pointcut=“切点表达式"></aop:通知类型>
```

| 名称         | 标签                  | 说明                                                         |
| ------------ | --------------------- | ------------------------------------------------------------ |
| 前置通知     | <aop:before>          | 用于配置前置通知。指定增强的方法在切入点方法之前执行         |
| 后置通知     | <aop:after-returning> | 用于配置后置通知。指定增强的方法在切入点方法之后执行         |
| 环绕通知     | <aop:around>          | 用于配置环绕通知。指定增强的方法在切入点方法之前和之后都<br/>执行 |
| 异常抛出通知 | <aop:throwing>        | 用于配置异常抛出通知。指定增强的方法在出现异常时执行         |
| 最终通知     | <aop:after>           | 用于配置最终通知。无论增强方式执行是否有异常都会执行         |

### 8.8、基于注解的AOP开发

#### 快速入门

基于注解的aop开发步骤:

1. 创建目标接口和目标类（内部有切点)

   目标接口

   ```java
   public interface TargetInstance {
       void save();
   }
   ```

   目标类

   ```java
   @Component("target")
   public class Target implements TargetInstance{
   
       public void save() {
           System.out.println("save running !");
       }
   }
   ```

   

2. 创建切面类（内部有增强方法)

   切面类

   ```java
   @Component("myAspect")
   @Aspect     //标注当前类是一个切面类
   public class MyAspect {
   
       @Before("execution(* com.accp.aop.Target.*(..)))")
       public void before(){
           System.out.println("前置增强");
       }
   
       @After("execution(* com.accp.aop.Target.*(..)))")
       public void after(){
           System.out.println("后置增强");
       }
   }
   ```

   

3. 将目标类和切面类的对象创建权交给spring

   ```
   @Component("myAspect")   @Component(""target"")
   ```

   

4. 在切面类中使用注解配置织入关系

   ```xml
   @Aspect   @Before("execution(* com.accp.aop.Target.*(..)))")    @After("execution(* com.accp.aop.Target.*(..)))")
   ```

   

5. 在配置文件中开启组件扫描和AOP的自动代理

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
   
       <context:component-scan base-package="com.accp"/>
   
       <!-- 配置织入：告诉spring框架 哪些方法(切点)需要进行哪些增强(前置，后置) -->
       <aop:aspectj-autoproxy/>
   
   </beans>
   ```

   

6. 测试

   ```java
   public class MyTest {
   
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           TargetInstance target = context.getBean("target", TargetInstance.class);
           target.save();
       }
   }
   ```

#### 注解的通知类型

通知的配置语法:**@通知注解(“切点表达式")**

| 名称         | 标签             | 说明                                                         |
| ------------ | ---------------- | ------------------------------------------------------------ |
| 前置通知     | @Before          | 用于配置前置通知。指定增强的方法在切入点方法之前执行         |
| 后置通知     | @After-returning | 用于配置后置通知。指定增强的方法在切入点方法之后执行         |
| 环绕通知     | @Around          | 用于配置环绕通知。指定增强的方法在切入点方法之前和之后都执行 |
| 异常抛出通知 | @Throwing        | 用于配置异常抛出通知。指定增强的方法在出现异常时执行         |
| 最终通知     | @After           | 用于配置最终通知。无论增强方式执行是否有异常都会执行         |

#### 切点表达式的抽取


同xml配置aop一样，我们可以将切点表达式抽取。抽取方式是在切面内定义方法，在该方法上使用@Pointcut注解定义切点表达式，然后在在增强注解中进行引用。具体如下:

```java
@Component("myAspect")
@Aspect     //标注当前类是一个切面类
public class MyAspect {

    @Before("pointCut()")   //"pointCut()"引用切入点
    public void before(){
        System.out.println("前置增强");
    }

    @After("pointCut()")   //"pointCut()"引用切入点
    public void after(){
        System.out.println("后置增强");
    }

    //抽取一个公共切入点
    @Pointcut("execution(* com.accp.aop.Target.*(..)))")
    public void pointCut(){}
}
```

注意：配置切入点需要在一个方法上，不能独自存在

## 9、JdbcTemplate

### jdbcTemplate概述

它是spring框架中提供的一个对象，是对原始繁琐的JdbcAPI对象的简单封装。spring框架为我们提供了很多的操作模板类。例如:操作关系型数据的JdbcTemplate和HibernateTemplate，操作nosql数据库的RedisTemplate，操作消息队列的JmsTemplate等等。



### jdbcTemplate开发步骤

1. 导入spring-jdbc、spring-tx、mysql-connector-java、spring-context、c3p0坐标

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>5.2.0.RELEASE</version>
   </dependency>
   
   <!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
   <dependency>
       <groupId>com.mchange</groupId>
       <artifactId>c3p0</artifactId>
       <version>0.9.5.1</version>
   </dependency>
   
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>5.1.35</version>
   </dependency>
   
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.11</version>
   </dependency>
   
   <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-tx</artifactId>
       <version>5.2.0.RELEASE</version>
   </dependency>
   
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.2.8.RELEASE</version>
   </dependency>
   ```

   

2. 创建数据库表和实体

   ```mysql
   CREATE TABLE `dept`  (
     `deptno` int(10) NOT NULL AUTO_INCREMENT,
     `deptname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
     `deptloc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
     PRIMARY KEY (`deptno`) USING BTREE
   ) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
   
   INSERT INTO `dept` VALUES (1, '人事部', '典韦');
   INSERT INTO `dept` VALUES (2, '研发部', '李白');
   INSERT INTO `dept` VALUES (3, '销售部', '韩信');
   INSERT INTO `dept` VALUES (4, '运维部', '貂蝉');
   INSERT INTO `dept` VALUES (5, '哈拉少部', '贼碧玺');
   INSERT INTO `dept` VALUES (6, '雷霆嘎巴', '欧青拉少');
   INSERT INTO `dept` VALUES (7, '哈拉少部', '贼碧玺');
   ```

   实体：Dept.java

   ```java
   package com.accp.pojo;
   
   public class Dept {
       private Integer deptNo;
       private String deptName;
       private String deptLoc;
   
       public Dept(Integer deptNo, String deptName, String deptLoc) {
           this.deptNo = deptNo;
           this.deptName = deptName;
           this.deptLoc = deptLoc;
       }
   
       public Dept() {
       }
   
       public Integer getDeptNo() {
           return deptNo;
       }
   
       public void setDeptNo(Integer deptNo) {
           this.deptNo = deptNo;
       }
   
       public String getDeptName() {
           return deptName;
       }
   
       public void setDeptName(String deptName) {
           this.deptName = deptName;
       }
   
       public String getDeptLoc() {
           return deptLoc;
       }
   
       public void setDeptLoc(String deptLoc) {
           this.deptLoc = deptLoc;
       }
   
       @Override
       public String toString() {
           return "Dept{" +
                   "deptNo=" + deptNo +
                   ", deptName='" + deptName + '\'' +
                   ", deptLoc='" + deptLoc + '\'' +
                   '}';
       }
   }
   
   ```

   

3. 创建JdbcTemplate对象

4. 执行数据操作

   ```java
     public void test1() throws PropertyVetoException {
   
           //创建jdbcTemplate对象
           JdbcTemplate jdbcTemplate = new JdbcTemplate();
   
           //创建数据源对象
           ComboPooledDataSource dataSource = new ComboPooledDataSource();
           dataSource.setDriverClass("com.mysql.jdbc.Driver");
           dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
           dataSource.setUser("root");
           dataSource.setPassword("123456");
   
           //jdbcTemplate设置数据源
           jdbcTemplate.setDataSource(dataSource);
   
           //执行查询操作
           int update = jdbcTemplate.update("delete from dept where deptNo = ?", 1);
           System.out.println(update);
   
       }
   ```

   

   ### Spring产生JdbcTemplate对象

   我们可以将JdbcTemplate的创建权交给Spring，将数据源DataSource的创建权也交给Spring，在Spring容器内部将数据源DataSource注入到JdbcTemplate模版对象中，配置如下:

   database.properties

   ```properties
   jdbc.driver=com.mysql.jdbc.Driver
   jdbc.url = jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8
   jdbc.username=root
   jdbc.password=123456
   ```

   beans.xml配置

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
   
       <!--引入外部jdbc配置文件：database.properties-->
       <context:property-placeholder location="database.properties" />
   
       <!--数据源：DataSource-->
       <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
           <property name="driverClass" value="${jdbc.driver}"/>
           <property name="jdbcUrl" value="${jdbc.url}"/>
           <property name="user" value="${jdbc.username}"/>
           <property name="password" value="${jdbc.password}"/>
       </bean>
   
       <!--JDBCTemplate-->
       <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
           <property name="dataSource" ref="dataSource" />
       </bean>
   </beans>
   ```

   测试

   ```java
   @Test
   public void test1() throws PropertyVetoException {
       ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
       JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
   
       List<Dept> deptList = jdbcTemplate.query("select * from dept", new BeanPropertyRowMapper<Dept>(Dept.class));
       for (Dept dept : deptList) {
           System.out.println(dept);
       }
   }
   ```

   

   **知识要点**

   执行数据库操作

   - 增删改操作:

   ```
   jdbcTemplate.update (sql,params)
   ```

   - 查询操作:

   ```
   jdbcTemplate.query (sql,Mapper, params)
   jdbcTemplate.queryForobject(sql,Mapper,params)
   ```

   

## 10、Spring事务控制

### 编程式事务控制相关对象

#### **1.1 PlatformTransactionManager**（平台事务管理器）

| 方法                                                         | 说明             |
| ------------------------------------------------------------ | ---------------- |
| Transactionstatus getTransaction(TransactionDefination defination) | 获取事务状态信息 |
| void commit (Transactionstatus status)                       | 提交事务         |
| void rollback (Transactionstatus status)                     | 回滚事务         |

**注意：**

PlatformTransactionManager是接口类型，不同的Dao层技术则有不同的实现类，例如:Dao层技术是jdbc
或mybatis时: org.springframework.jdbc.datasource.DataSourceTransactionManager
Dao层技术是hibernate时: org.springframework.orm.hibernate5.HibernateTransactionManager



#### **1.2 TransactionDefinition**

TransactionDefinition是事务的定义信息对象，里面有如下方法:

| 方法                         | 说明               |
| ---------------------------- | ------------------ |
| int getIsolationLevel()      | 获得事务的隔离级别 |
| int getPropogationBehavior() | 获得事务的传播行为 |
| int getTimeout()             | 获得超时时间       |
| boolean isReadOnly           | 是否只读           |



**事务隔离级别**

设置隔离级别，可以解决事务并发产生的问题，如脏读、不可重复读和虚读。

- ISOLATION_DEFAULT
- ISOLATION_READ_UNCOMMITTED
- ISOLATION_READ_COMMITTED
- 工SOLATION_REPEATABLE_READ
- ISOLATION_SERIAL工ZABLE



**事务传播行为**

- SUPPORTS:支持当前事务，如果当前没有事务，就以非事务方式执行(没有事务)
- REQUIRED:如果当前没有事务，就新建一个事努，如果已经存在一个事中，加入到这个事务中。一般的选择（默认值)
- MANDATORY:使用当前的事务，如果当前没有事务，就抛出异常
- REQUERS_NEV新建事务，如果当前在事务中，把当前事务挂起。
- NEVER:以非事务方式运行，如果当前存在事务，抛出异常
- 超时时间:默认值是-1，没有超时限制。如果有，以秒为单位进行设置
- 是否只读:建议查询时设置为只读
- REQUIRED:如果当前没有事务，就新建一个事务，如果已经存在一个事努中，加入到这个事务中。一般的选择（默认值)
- NESTED:如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行REQUIRED类似的操作
- NOT_SUPPORTED:以非事务方式执行操作，如果当前存在事务，就把当前事务挂起

#### **1.3** **TransactionStatus**

TransactionStatus接口提供的是事务具体的运行状态，方法介绍如下。

| 方法                       | 说明           |
| -------------------------- | -------------- |
| boolean hasSavepoint()     | 是否存储回滚点 |
| boolean isCompleted()      | 事务是否完成   |
| boolean isNewTransaction() | 是否是新事务   |
| boolean isRollbackOnly()   | 事务是否回滚   |

### 基于XML的声明式事务控制

**什么是声明式事务控制？**
		Spring的声明式事务顾名思义就是采用声明的方式来处理事务。这里所说的声明，就是指在配置文件中声明，用在Spring配置文件中声明式的处理事务来代替代码式的处理事务。

**声明式事务处理的作用**

- 事务管理不侵入开发的组件。具体来说，业务逻辑对象就不会意识到正在事务管理之中，事实上也应该如此，因为事务管理是属于系统层面的服务，而不是业务逻辑的一部分，如果想要改变事务管理策划的话，也只需要在定义文件中重新配置即可

- 在不需要事务管理的时候，只要在设定文件上修改一下，即可移去事务管理服务，无需改变代码重新编译，这样维护起来极其方便

  说明：Spring声明式事务控制底层就是AOP。

**快速入门**

1. 导入坐标

   ```xml
   <dependency>
       <groupId>com.mchange</groupId>
       <artifactId>c3p0</artifactId>
       <version>0.9.5.1</version>
   </dependency>
   
   <dependency>
       <groupId>org.aspectj</groupId>
       <artifactId>aspectjweaver</artifactId>
       <version>1.8.4</version>
   </dependency>
   
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.2.0.RELEASE</version>
   </dependency>
   
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.13</version>
   </dependency>
   
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>5.1.35</version>
   </dependency>
   
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>5.2.0.RELEASE</version>
   </dependency>
   
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-tx</artifactId>
       <version>5.2.0.RELEASE</version>
   </dependency>
   ```

2. 配置beans.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:tx="http://www.springframework.org/schema/tx"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/tx
          http://www.springframework.org/schema/tx/spring-tx.xsd
          http://www.springframework.org/schema/aop
          https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!--创建数据源对象-->
       <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
           <property name="driverClass" value="com.mysql.jdbc.Driver"/>
           <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"/>
           <property name="user" value="root"/>
           <property name="password" value="123456"/>
       </bean>
   
       <!--配置平台事务管理器-->
       <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <property name="dataSource" ref="dataSource"/>
       </bean>
   
       <!--通知-->
       <tx:advice id="txAdvice" transaction-manager="transactionManager">
           <!--设置事务的属性信息-->
           <tx:attributes>
               <!--给所有方法加上事务管理-->
               <tx:method name="*"/>
           </tx:attributes>
       </tx:advice>
   
       <!--配置事务的织入-->
       <aop:config>
           <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.accp.*.*(..))"></aop:advisor>
       </aop:config>
   
   </beans>
   ```

### 基于注解的声明式事务控制

Bean的配置

```java
@Service("deptService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)	//所有方法配置事务，也可以在方法上配置，遵守就近原则
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    //配置事务
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int updateName(String name, int id) {
        int a = deptDao.updateName(name,id);
        int i = 1/0;
        int b = deptDao.deleteDept(4);
        return a+b;
    }

}
```

beans.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop
       https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/context     https://www.springframework.org/schema/context/spring-context.xsd">

    <!--创建数据源对象-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Driver"/>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"/>
        <property name="user" value="root"/>
        <property name="password" value="123456"/>
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--扫描组件-->
    <context:component-scan base-package="com.accp"/>


    <!--创建平台事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager"/>

</beans>
```

注意事项：

- @Transactional 该注解可以定义在方法上也可以定义在类上，遵守就近原则

- ```
  <!--扫描组件-->
  <context:component-scan base-package="com.accp"/>
  ```

- ```
  <!--配置事务管理器-->
  <tx:annotation-driven transaction-manager="transactionManager"/>
  ```

  

































