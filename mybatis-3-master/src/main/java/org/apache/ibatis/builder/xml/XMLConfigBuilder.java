/**
 * Copyright 2009-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ibatis.builder.xml;

import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.builder.BaseBuilder;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.loader.ProxyFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.JdbcType;

/**
 * @author Clinton Begin
 * @author Kazuki Shimizu
 */
public class XMLConfigBuilder extends BaseBuilder {

  private boolean parsed;
  private final XPathParser parser;
  private String environment;
  private final ReflectorFactory localReflectorFactory = new DefaultReflectorFactory();

  public XMLConfigBuilder(Reader reader) {
    this(reader, null, null);
  }

  public XMLConfigBuilder(Reader reader, String environment) {
    this(reader, environment, null);
  }

  public XMLConfigBuilder(Reader reader, String environment, Properties props) {
    this(new XPathParser(reader, true, props, new XMLMapperEntityResolver()), environment, props);
  }

  public XMLConfigBuilder(InputStream inputStream) {
    this(inputStream, null, null);
  }

  public XMLConfigBuilder(InputStream inputStream, String environment) {
    this(inputStream, environment, null);
  }

  public XMLConfigBuilder(InputStream inputStream, String environment, Properties props) {
    this(new XPathParser(inputStream, true, props, new XMLMapperEntityResolver()), environment, props);
  }

  private XMLConfigBuilder(XPathParser parser, String environment, Properties props) {
    super(new Configuration());
    ErrorContext.instance().resource("SQL Mapper Configuration");
    this.configuration.setVariables(props);
    this.parsed = false;
    this.environment = environment;
    this.parser = parser;
  }

  /**
   * 解析流 获取配置信息
   *
   * @return 配置信息
   */
  public Configuration parse() {
    // 判断这个xml文件是否被解析过
    if (parsed) {
      throw new BuilderException("Each XMLConfigBuilder can only be used once.");
    }
    // 开始解析 解析状态值为true
    parsed = true;
    // XPathParser解析流对象获取node 从跟标签configuration到此标签技术的所有内容都在 root对象中
    XNode root = parser.evalNode("/configuration");
    // 解析root配置信息 并做出对应配置
    parseConfiguration(root);
    // 配置对象
    return configuration;
  }

  /**
   * NOTE 根据解析后得xml文件信息设置mybatis参数 创建sessionFactory对象的核心方法
   *
   * @param root 解析后得xml配置文件信息
   */
  private void parseConfiguration(XNode root) {
    try {
      // 是否有外部文件引入 properties
      propertiesElement(root.evalNode("properties"));
      // setting配置  log...
      Properties settings = settingsAsProperties(root.evalNode("settings"));
      // 根据setting信息加载vfs到Configuration中
      loadCustomVfs(settings);
      // 根据setting信息加载log对象到Configuration中
      loadCustomLogImpl(settings);
      // 别名配置
      typeAliasesElement(root.evalNode("typeAliases"));
      // 插件配置  拦截器
      pluginElement(root.evalNode("plugins"));
      // 对象工厂
      objectFactoryElement(root.evalNode("objectFactory"));
      // MyBatis 提供在构造对象的时候，对于指定的对象进行特殊的加工
      objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
      // MyBatis 用于缓存 Reflector 的功能
      reflectorFactoryElement(root.evalNode("reflectorFactory"));
      // 解析setting内容并设置属性
      settingsElement(settings);
      // 读取数据源配置
      // read it after objectFactory and objectWrapperFactory issue #631
      environmentsElement(root.evalNode("environments"));
      // MyBatis 可以根据不同的数据库厂商执行不同的语句 databaseIdProvider用于指定数据库厂商标识
      databaseIdProviderElement(root.evalNode("databaseIdProvider"));
      // 类型处理器 NOTE 重写类型处理器或创建你自己的类型处理器来处理不支持的或非标准的类型
      typeHandlerElement(root.evalNode("typeHandlers"));
      // 注册mapper文件
      mapperElement(root.evalNode("mappers"));
    } catch (Exception e) {
      throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
    }
  }

  /**
   * 设置setting信息
   */
  private Properties settingsAsProperties(XNode context) {
    if (context == null) {
      return new Properties();
    }
    // 根据上下文获取到setting信息 并按照键值对的形式存到props对象中
    Properties props = context.getChildrenAsProperties();
    // Check that all settings are known to the configuration class  NOTE Configuration的Reflector是默认被缓存的
    MetaClass metaConfig = MetaClass.forClass(Configuration.class, localReflectorFactory);
    // 获取所有setting
    for (Object key : props.keySet()) {
      // 判断configuration class中有没有这个key属性
      if (!metaConfig.hasSetter(String.valueOf(key))) {
        throw new BuilderException("The setting " + key + " is not known.  Make sure you spelled it correctly (case sensitive).");
      }
    }
    // 返回properties对象
    return props;
  }

  /**
   * 加载setting信息到Configuration中
   */
  private void loadCustomVfs(Properties props) throws ClassNotFoundException {
    // 获取vfsImpl
    String value = props.getProperty("vfsImpl");
    // 判断
    if (value != null) {
      // 获取全部vfs
      String[] clazzes = value.split(",");
      // 迭代
      for (String clazz : clazzes) {
        // class对象不为空
        if (!clazz.isEmpty()) {
          // 加载这个类 获取class对象
          @SuppressWarnings("unchecked")
          Class<? extends VFS> vfsImpl = (Class<? extends VFS>) Resources.classForName(clazz);
          // 设置
          configuration.setVfsImpl(vfsImpl);
        }
      }
    }
  }

  /**
   * @param props
   */
  private void loadCustomLogImpl(Properties props) {
    // log对象
    Class<? extends Log> logImpl = resolveClass(props.getProperty("logImpl"));
    // 设置
    configuration.setLogImpl(logImpl);
  }

  /**
   * 配置别名
   */
  private void typeAliasesElement(XNode parent) {
    // parent是否位空  为空表示没有配置别名
    if (parent != null) {
      // 获取子标签
      for (XNode child : parent.getChildren()) {
        // 是否整包扫描
        if ("package".equals(child.getName())) {
          // 包名
          String typeAliasPackage = child.getStringAttribute("name");
          // 根据包名注册所有别名
          configuration.getTypeAliasRegistry().registerAliases(typeAliasPackage);
        } else {
          // 按照类型注册别名
          String alias = child.getStringAttribute("alias");
          String type = child.getStringAttribute("type");

          try {
            // 尝试获取class对象
            Class<?> clazz = Resources.classForName(type);
            // 别名为空
            if (alias == null) {
              // 注册
              typeAliasRegistry.registerAlias(clazz);
            } else {
              // 注册
              typeAliasRegistry.registerAlias(alias, clazz);
            }
          } catch (ClassNotFoundException e) {
            throw new BuilderException("Error registering typeAlias for '" + alias + "'. Cause: " + e, e);
          }
        }
      }
    }
  }

  /**
   * 插件
   */
  private void pluginElement(XNode parent) throws Exception {
    // 查询是否配置
    if (parent != null) {
      // 获取子节点
      for (XNode child : parent.getChildren()) {
        // 获取拦截器
        String interceptor = child.getStringAttribute("interceptor");
        // 获取配置信息
        Properties properties = child.getChildrenAsProperties();
        // 解析别名配置 获取构造器  创建实例
        Interceptor interceptorInstance = (Interceptor) resolveClass(interceptor).getDeclaredConstructor().newInstance();
        // 设置属性
        interceptorInstance.setProperties(properties);
        configuration.addInterceptor(interceptorInstance);
      }
    }
  }

  /**
   * 指定对象工厂
   */
  private void objectFactoryElement(XNode context) throws Exception {
    // 判断是否指定了对象工厂  没有指定configuration对象初始化会初始化一个默认的工厂
    if (context != null) {
      // 获取全限定类路径
      String type = context.getStringAttribute("type");
      // 如果指定的对象工厂有属性  此处获取
      Properties properties = context.getChildrenAsProperties();
      // 解析别名配置 获取构造器  创建实例
      ObjectFactory factory = (ObjectFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      // 设置属性
      factory.setProperties(properties);
      // 设置属性
      configuration.setObjectFactory(factory);
    }
  }

  /**
   * 配置定义的objectWrapperFactory
   */
  private void objectWrapperFactoryElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      // 获取配置的ObjectWrapperFactory
      ObjectWrapperFactory factory = (ObjectWrapperFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      configuration.setObjectWrapperFactory(factory);
    }
  }

  /**
   * 配置  <reflectorFactory type=""/>
   */
  private void reflectorFactoryElement(XNode context) throws Exception {
    if (context != null) {
      // 获取
      String type = context.getStringAttribute("type");
      // 实例
      ReflectorFactory factory = (ReflectorFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      // 设置
      configuration.setReflectorFactory(factory);
    }
  }

  /**
   * 获取properties文件对象信息 NOTE 引入的外部配置文件  如db.properties
   *
   * @param context
   * @throws Exception
   */
  private void propertiesElement(XNode context) throws Exception {
    if (context != null) {
      Properties defaults = context.getChildrenAsProperties();
      String resource = context.getStringAttribute("resource");
      String url = context.getStringAttribute("url");
      if (resource != null && url != null) {
        throw new BuilderException(
            "The properties element cannot specify both a URL and a resource based property file reference.  Please specify one or the other.");
      }
      if (resource != null) {
        defaults.putAll(Resources.getResourceAsProperties(resource));
      } else if (url != null) {
        defaults.putAll(Resources.getUrlAsProperties(url));
      }
      Properties vars = configuration.getVariables();
      if (vars != null) {
        defaults.putAll(vars);
      }
      parser.setVariables(defaults);
      configuration.setVariables(defaults);
    }
  }

  /**
   * setting配置解析  自带默认值
   */
  private void settingsElement(Properties props) {
    // 结果集自动映射
    configuration.setAutoMappingBehavior(AutoMappingBehavior.valueOf(props.getProperty("autoMappingBehavior", "PARTIAL")));
    // 设置自动映射未知列的行为
    configuration
        .setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.valueOf(props.getProperty("autoMappingUnknownColumnBehavior", "NONE")));
    // 二级缓存
    configuration.setCacheEnabled(booleanValueOf(props.getProperty("cacheEnabled"), true));
    // 代理工厂
    configuration.setProxyFactory((ProxyFactory) createInstance(props.getProperty("proxyFactory")));
    // 懒加载  如果查询订单并且关联查询用户信息。如果先查询订单信息即可满足要求，当我们需要查询用户信息时再查询用户信息。把对用户信息的按需去查询就是延迟加载。
    configuration.setLazyLoadingEnabled(booleanValueOf(props.getProperty("lazyLoadingEnabled"), false));
    // 积极加载
    configuration.setAggressiveLazyLoading(booleanValueOf(props.getProperty("aggressiveLazyLoading"), false));
    // 是否允许单一语句返回多结果集（需要兼容驱动）。
    configuration.setMultipleResultSetsEnabled(booleanValueOf(props.getProperty("multipleResultSetsEnabled"), true));
    // 使用列标签代替列名。不同的驱动在这方面会有不同的表现， 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果。
    configuration.setUseColumnLabel(booleanValueOf(props.getProperty("useColumnLabel"), true));
    // 受全局useGeneratedKeys参数控制，添加记录之后将返回主键id
    configuration.setUseGeneratedKeys(booleanValueOf(props.getProperty("useGeneratedKeys"), false));
    // 设置执行器  SIMPLE, REUSE, BATCH
    // SimpleExecutor:每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象
    // ReuseExecutor:执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map内，供下一次使用。简言之，就是重复使用Statement对象
    // BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同
    configuration.setDefaultExecutorType(ExecutorType.valueOf(props.getProperty("defaultExecutorType", "SIMPLE")));
    // defaultStatementTimeout：表示在MyBatis配置文件中默认查询超时间，单位秒，不设置则无限等待
    configuration.setDefaultStatementTimeout(integerValueOf(props.getProperty("defaultStatementTimeout"), null));
    // MySQL不支持fetchSize，默认为一次性取出所有数据。所以容易导致OOM，如果是Oracle的话就是默认取出fetchSize条数据。裸露JDBC防止OOM可以调用statement的enableStreamingResults方法,MyBatis应该在<select fetchSize="-2147483648">
    configuration.setDefaultFetchSize(integerValueOf(props.getProperty("defaultFetchSize"), null));
    // 结果集类型
    configuration.setDefaultResultSetType(resolveResultSetType(props.getProperty("defaultResultSetType")));
    // 自动驼峰命名转换
    configuration.setMapUnderscoreToCamelCase(booleanValueOf(props.getProperty("mapUnderscoreToCamelCase"), false));
    // 允许在嵌套语句中使用分页（RowBounds）。 If allow, set the false.
    configuration.setSafeRowBoundsEnabled(booleanValueOf(props.getProperty("safeRowBoundsEnabled"), false));
    // 一级缓存 实现的方式是每一个SqlSession中都持有了自己的缓存，一种是SESSION级别，即在一个Mybatis会话中执行的所有语句，都会共享这一个缓存。一种是STATEMENT级别，可以理解为缓存只对当前执行的这一个statement有效。
    configuration.setLocalCacheScope(LocalCacheScope.valueOf(props.getProperty("localCacheScope", "SESSION")));
    // 对传入的null值得处理方式
    configuration.setJdbcTypeForNull(JdbcType.valueOf(props.getProperty("jdbcTypeForNull", "OTHER")));
    // lazyLoadTriggerMethods默认情况下仅仅支持自动将equals,clone,hashCode,toString这几个方法定义为延迟加载的加载触发方法。
    configuration.setLazyLoadTriggerMethods(stringSetValueOf(props.getProperty("lazyLoadTriggerMethods"), "equals,clone,hashCode,toString"));
    // 是否开启自定义的结果集转义器
    configuration.setSafeResultHandlerEnabled(booleanValueOf(props.getProperty("safeResultHandlerEnabled"), true));
    // mapper文件处理驱动
    configuration.setDefaultScriptingLanguage(resolveClass(props.getProperty("defaultScriptingLanguage")));
    // 设置枚举类型处理器
    configuration.setDefaultEnumTypeHandler(resolveClass(props.getProperty("defaultEnumTypeHandler")));
    // 在一般查询中，如果用map接受查询结果时，会自动将查询结果为null的字段忽略，这样就造成取参数时报空指针异常的情况。如果设置了这条属性之后，mybatis就不会忽略这些字段，你依然能get到这些key，只不过value为null，这样也方便。
    configuration.setCallSettersOnNulls(booleanValueOf(props.getProperty("callSettersOnNulls"), false));
    // useActualParamName的作用：允许使用方法签名中的名称作为语句参数名称   如果useActualParamName设置为true时，则传递参数需要使用  #{arg0}-#{argn}或者#{param1}-#{paramn}
    configuration.setUseActualParamName(booleanValueOf(props.getProperty("useActualParamName"), true));
    // 当返回行的所有列都是空时，MyBatis默认返回null。当开启这个设置时，MyBatis会返回一个空实例。请注意，它也适用于嵌套的结果集（i.e.collection and association）。（从3.4.2开始）
    configuration.setReturnInstanceForEmptyRow(booleanValueOf(props.getProperty("returnInstanceForEmptyRow"), false));
    // 指定MyBatis增加到日志名称的前缀。
    configuration.setLogPrefix(props.getProperty("logPrefix"));
    // 指定一个提供Configuration实例的类。这个被返回的Configuration实例用来加载被反序列化对象的懒加载属性值。这个类必须包含一个签名方法static Configuration getConfiguration()。
    configuration.setConfigurationFactory(resolveClass(props.getProperty("configurationFactory")));
    // 是否自动缩进sql空格
    configuration.setShrinkWhitespacesInSql(booleanValueOf(props.getProperty("shrinkWhitespacesInSql"), false));
    // sql构建器
    configuration.setDefaultSqlProviderType(resolveClass(props.getProperty("defaultSqlProviderType")));
  }

  /**
   * 解析environments节点内容
   */
  private void environmentsElement(XNode context) throws Exception {
    if (context != null) {
      // 判断有没有设置environment
      if (environment == null) {
        environment = context.getStringAttribute("default");
      }
      // 可配置多个environment迭代
      for (XNode child : context.getChildren()) {
        // environment唯一标识
        String id = child.getStringAttribute("id");
        // 只有指定的id的environment才能被继续解析 this.environment.equal(id);
        if (isSpecifiedEnvironment(id)) {
          // 事物管理器工厂
          TransactionFactory txFactory = transactionManagerElement(child.evalNode("transactionManager"));
          // 数据源工厂
          DataSourceFactory dsFactory = dataSourceElement(child.evalNode("dataSource"));
          // 从工厂获取到数据源
          DataSource dataSource = dsFactory.getDataSource();
          // 设置Environment.Builder对象 指定id事物工厂数据源
          Environment.Builder environmentBuilder = new Environment.Builder(id)
              .transactionFactory(txFactory)
              .dataSource(dataSource);
          // 设置属性
          configuration.setEnvironment(environmentBuilder.build());
          break;
        }
      }
    }
  }

  /**
   * 指定厂商标识
   */
  private void databaseIdProviderElement(XNode context) throws Exception {
    DatabaseIdProvider databaseIdProvider = null;
    if (context != null) {
      String type = context.getStringAttribute("type");
      // awful patch to keep backward compatibility
      if ("VENDOR".equals(type)) {
        type = "DB_VENDOR";
      }
      Properties properties = context.getChildrenAsProperties();
      // 加载
      databaseIdProvider = (DatabaseIdProvider) resolveClass(type).getDeclaredConstructor().newInstance();
      databaseIdProvider.setProperties(properties);
    }
    // 数据源信息
    Environment environment = configuration.getEnvironment();
    if (environment != null && databaseIdProvider != null) {
      // 数据源标识
      String databaseId = databaseIdProvider.getDatabaseId(environment.getDataSource());
      // 设置标识
      configuration.setDatabaseId(databaseId);
    }
  }

  /**
   * 获取事务管理器配置
   */
  private TransactionFactory transactionManagerElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      Properties props = context.getChildrenAsProperties();
      // 根据别名获取事物管理器工厂实例
      TransactionFactory factory = (TransactionFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      // 设置属性
      factory.setProperties(props);
      // 返回这个工厂
      return factory;
    }
    throw new BuilderException("Environment declaration requires a TransactionFactory.");
  }

  /**
   * 数据源
   */
  private DataSourceFactory dataSourceElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      Properties props = context.getChildrenAsProperties();
      // 根据type获取连接池属性获取数据源工厂
      DataSourceFactory factory = (DataSourceFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      // 属性
      factory.setProperties(props);
      // 返回
      return factory;
    }
    throw new BuilderException("Environment declaration requires a DataSourceFactory.");
  }

  /**
   * 类型处理器
   */
  private void typeHandlerElement(XNode parent) {
    if (parent != null) {
      for (XNode child : parent.getChildren()) {
        // 按照包路径配置
        if ("package".equals(child.getName())) {
          // handler包名
          String typeHandlerPackage = child.getStringAttribute("name");
          // 注册类型处理器
          typeHandlerRegistry.register(typeHandlerPackage);
        } else {
          // 按照全限定类名配置
          String javaTypeName = child.getStringAttribute("javaType");
          String jdbcTypeName = child.getStringAttribute("jdbcType");
          String handlerTypeName = child.getStringAttribute("handler");
          Class<?> javaTypeClass = resolveClass(javaTypeName);
          JdbcType jdbcType = resolveJdbcType(jdbcTypeName);
          Class<?> typeHandlerClass = resolveClass(handlerTypeName);
          if (javaTypeClass != null) {
            if (jdbcType == null) {
              typeHandlerRegistry.register(javaTypeClass, typeHandlerClass);
            } else {
              typeHandlerRegistry.register(javaTypeClass, jdbcType, typeHandlerClass);
            }
          } else {
            typeHandlerRegistry.register(typeHandlerClass);
          }
        }
      }
    }
  }

  /**
   * 注册mapper文件
   */
  private void mapperElement(XNode parent) throws Exception {
    if (parent != null) {
      for (XNode child : parent.getChildren()) {
        // 按包扫描
        if ("package".equals(child.getName())) {
          String mapperPackage = child.getStringAttribute("name");
          // 添加
          configuration.addMappers(mapperPackage);
        } else {
          String resource = child.getStringAttribute("resource");
          String url = child.getStringAttribute("url");
          String mapperClass = child.getStringAttribute("class");
          if (resource != null && url == null && mapperClass == null) {
            ErrorContext.instance().resource(resource);
            try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
              XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
              mapperParser.parse();
            }
          } else if (resource == null && url != null && mapperClass == null) {
            ErrorContext.instance().resource(url);
            try (InputStream inputStream = Resources.getUrlAsStream(url)) {
              XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, url, configuration.getSqlFragments());
              mapperParser.parse();
            }
          } else if (resource == null && url == null && mapperClass != null) {
            Class<?> mapperInterface = Resources.classForName(mapperClass);
            configuration.addMapper(mapperInterface);
          } else {
            throw new BuilderException("A mapper element may only specify a url, resource or class, but not more than one.");
          }
        }
      }
    }
  }

  private boolean isSpecifiedEnvironment(String id) {
    if (environment == null) {
      throw new BuilderException("No environment specified.");
    }
    if (id == null) {
      throw new BuilderException("Environment requires an id attribute.");
    }
    return environment.equals(id);
  }

}
