# Spring整合MyBatis

## 1、回忆MyBatis

步骤：

1. 导入相关jar包
2. 编写核心配置文件
3. 编写接口
4. 编写mapper.xml
5. 测试

## 2、MyBatis-Spring-方式一

MyBati-Spring会帮助你将MjBais 代码无缝地整合到spring 中。它将允许MVBais参与到Ssping的事务管理之中，创建映射器mapper和sga.1essicn并注入到cean中，以及将Mlypats的异常转换为Spring的DataAccessException。最终，可以做到应用代码不依赖于MyBatis，Spring或MyBatis-Spring.



MyBatis整合Spring主要是改变了SqlSessionFactory的创建方式，在传统的MyBatis中SqlSessionFactory对象的创建是由SqlSessionFactoryBuilder通过build()方法读取mybatis-config.xml配置文件来配置的，而整合了Spring这个SqlSessionFactory就可以通过Spring的SqlSessionFactoryBean对象来创建，它可以代替mybatis-config.xml的所有配置，从而实现了丢弃mybatis-config.xml的配置文件。



**MyBatis整合Spring快速入门**

1. 导入jar包

   ```xml
   <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis-spring</artifactId>
       <version>2.0.0</version>
   </dependency>
   
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.0.8.RELEASE</version>
   </dependency>
   
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.11</version>
   </dependency>
   
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>5.1.35</version>
   </dependency>
   
   <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis</artifactId>
       <version>3.5.5</version>
   </dependency>
   
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>5.2.0.RELEASE</version>
   </dependency>
   
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
   ```

   

2. 编写数据源配置

   ```xml
   <!--DataSource-：使用Spring的数据源替换Mybatis的配置：C3P0、Druid，这里使用的Spring-jdbc自带的数据源管理器-->
   <bean id="dataSource" class="com.mchange.v2.c3p0.DriverManagerDataSource">
       <property name="driverClass" value="com.mysql.jdbc.Driver" />
       <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test?UseUnicode=true&amp;characterEncoding=utf-8" />
       <property name="user" value="root" />
       <property name="password" value="123456" />
   </bean>
   ```

   

3. SqlSessionFactory

   ```xml
   <!-- SqlSessionFactory-->
   <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
       <property name="dataSource" ref="dataSource"/>
       <!--引入外部mybatis配置文件-->
       <property name="configLocation" value="classpath:mybatis-config.xml"/>
       <!--引入mapper.xml文件-->
       <property name="mapperLocations" value="classpath:com/accp/mapper/*.xml"/>
   </bean>
   ```

   

4. SqlSessoinTemplate

   ```xml
   <!--SqlSession对象-->
   <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
       <constructor-arg ref="sqlSessionFactory"/>
   </bean>
   ```

   

5. 需要给接口添加实现类

   ```java
   public class DeptMapperImpl implements DeptMapper{
       private SqlSessionTemplate sqlSessionTemplate;
       public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
           this.sqlSessionTemplate = sqlSessionTemplate;
       }
   
   
       public List<Dept> selectDept() {
           return sqlSessionTemplate.getMapper(DeptMapper.class).selectDept();
       }
   }
   ```

   

6. 将自己写的实现类注册到Spring中测试使用

   ```xml
   <bean id="deptMapper" class="com.accp.mapper.DeptMapperImpl">
       <property name="sqlSessionTemplate" ref="sqlSessionTemplate"/>
   </bean>
   ```

   ```java
   @Test
   public void test1(){
       ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
       DeptMapperImpl deptMapper = context.getBean("deptMapper", DeptMapperImpl.class);
       List<Dept> deptList = deptMapper.selectDept();
       for (Dept dept : deptList) {
           System.out.println(dept);
       }
   }
   ```

   

## 3、MyBatis-Spring-方式二

方式二简化了方式一的基础上简化了 private SqlSessionTemplate sqlSessionTemplate;对象在实现类中的注入

**修改前：**

```java
public class DeptMapperImpl implements DeptMapper{
    private SqlSessionTemplate sqlSessionTemplate;
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Dept> selectDept() {
        return sqlSessionTemplate.getMapper(DeptMapper.class).selectDept();
    }
}
```

beans.xml

```xml
<bean id="deptMapper" class="com.accp.mapper.DeptMapperImpl">
    <property name="sqlSessionTemplate" ref="sqlSessionTemplate"/>
</bean>
```



**修改后：**

```java
public class DeptMapperImpl extends SqlSessionDaoSupport implements DeptMapper{
    
    public List<Dept> selectDept() {
        return getSqlSession().getMapper(DeptMapper.class).selectDept();
    }
}
```

beans.xml

```xml
<bean id="deptMapper" class="com.accp.mapper.DeptMapperImpl">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```

























