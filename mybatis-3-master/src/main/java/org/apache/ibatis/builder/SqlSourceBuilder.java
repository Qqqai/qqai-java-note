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
package org.apache.ibatis.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;

/**
 * @author Clinton Begin
 */
public class SqlSourceBuilder extends BaseBuilder {

  private static final String PARAMETER_PROPERTIES = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

  public SqlSourceBuilder(Configuration configuration) {
    super(configuration);
  }

  /**
   * 解析sql
   */
  public SqlSource parse(String originalSql, Class<?> parameterType, Map<String, Object> additionalParameters) {
    // 全局配置获取参数解析器
    ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration, parameterType, additionalParameters);
    // #{} 解析器 指定openTaken为#{}
    GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
    String sql;
    if (configuration.isShrinkWhitespacesInSql()) {
      sql = parser.parse(removeExtraWhitespaces(originalSql));
    } else {
      // 使用解析器解析#{} 为 ?
      sql = parser.parse(originalSql);
    }
    // 返回解析后的 StaticSqlSource
    return new StaticSqlSource(configuration, sql, handler.getParameterMappings());
  }

  public static String removeExtraWhitespaces(String original) {
    StringTokenizer tokenizer = new StringTokenizer(original);
    StringBuilder builder = new StringBuilder();
    boolean hasMoreTokens = tokenizer.hasMoreTokens();
    while (hasMoreTokens) {
      builder.append(tokenizer.nextToken());
      hasMoreTokens = tokenizer.hasMoreTokens();
      if (hasMoreTokens) {
        builder.append(' ');
      }
    }
    return builder.toString();
  }

  private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {

    private List<ParameterMapping> parameterMappings = new ArrayList<>();
    private Class<?> parameterType;
    private MetaObject metaParameters;

    public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType, Map<String, Object> additionalParameters) {
      super(configuration);
      this.parameterType = parameterType;
      this.metaParameters = configuration.newMetaObject(additionalParameters);
    }

    public List<ParameterMapping> getParameterMappings() {
      return parameterMappings;
    }

    /**
     * 解析#{} 调用此方法 全部替换为 ?  且添加参数映射
     */
    @Override
    public String handleToken(String content) {
      parameterMappings.add(buildParameterMapping(content));
      return "?";
    }


    /**
     * 构建参数映射
     */
    private ParameterMapping buildParameterMapping(String content) {
      /**
       * 将 #{xxx} 占位符中的内容解析成 Map。大家可能很好奇一个普通的字符串是怎么 解析成 Map 的,举例说明一下。如下: #{age,javaType=int,jdbcType=NUMERIC,typeHandler=MyTypeHandler}
       * 上面占位符中的内容最终会被解析成如下的结果: { "property": "age", "typeHandler": "MyTypeHandler", "jdbcType": "NUMERIC", "javaType": "int" }
       * parseParameterMapping内部依赖 ParameterExpression 对字符串进行解析, ParameterExpression 的逻辑不是很复杂,这里就不分析了。
       */
      Map<String, String> propertiesMap = parseParameterMapping(content);
      String property = propertiesMap.get("property");
      Class<?> propertyType;

      // metaParameters 为 DynamicContext 成员变量 bindings 的元信息对象
      if (metaParameters.hasGetter(property)) { // issue #448 get type from additional params
        propertyType = metaParameters.getGetterType(property);
        /*
         * parameterType 是运行时参数的类型。如果用户传入的是单个参数,比如 Article
         * 对象,此时 parameterType 为 Article.class。如果用户传入的多个参数,比如
         * [id = 1, author = "coolblog"],MyBatis 会使用 ParamMap 封装这些参数,
         * 此时 parameterType 为 ParamMap.class。如果 parameterType 有相应的
         * TypeHandler,这里则把 parameterType 设为 propertyType
         */
      } else if (typeHandlerRegistry.hasTypeHandler(parameterType)) {
        propertyType = parameterType;
      } else if (JdbcType.CURSOR.name().equals(propertiesMap.get("jdbcType"))) {
        propertyType = java.sql.ResultSet.class;
      } else if (property == null || Map.class.isAssignableFrom(parameterType)) {
        // 如果 property 为空,或 parameterType 是 Map 类型,
        // 则将 propertyType 设为 Object.class
        propertyType = Object.class;
      } else {
        // 代码逻辑走到此分支中,表明 parameterType 是一个自定义的类,
        // 比如 Article,此时为该类创建一个元信息对象
        MetaClass metaClass = MetaClass.forClass(parameterType, configuration.getReflectorFactory());
        // 检测参数对象有没有与 property 想对应的 getter 方法
        if (metaClass.hasGetter(property)) {
          // 获取成员变量的类型
          propertyType = metaClass.getGetterType(property);
        } else {
          propertyType = Object.class;
        }
      }

      ParameterMapping.Builder builder = new ParameterMapping.Builder(configuration, property, propertyType);
      // 将 propertyType 赋值给 javaType
      Class<?> javaType = propertyType;
      String typeHandlerAlias = null;
      // 遍历 propertiesMap
      for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
        String name = entry.getKey();
        String value = entry.getValue();
        if ("javaType".equals(name)) {
          // 如果用户明确配置了 javaType,则以用户的配置为准
          javaType = resolveClass(value);
          builder.javaType(javaType);
        } else if ("jdbcType".equals(name)) {
          // 解析 jdbcType
          builder.jdbcType(resolveJdbcType(value));
        } else if ("mode".equals(name)) {
          builder.mode(resolveParameterMode(value));
        } else if ("numericScale".equals(name)) {
          builder.numericScale(Integer.valueOf(value));
        } else if ("resultMap".equals(name)) {
          builder.resultMapId(value);
        } else if ("typeHandler".equals(name)) {
          typeHandlerAlias = value;
        } else if ("jdbcTypeName".equals(name)) {
          builder.jdbcTypeName(value);
        } else if ("property".equals(name)) {
          // Do Nothing
        } else if ("expression".equals(name)) {
          throw new BuilderException("Expression based parameters are not supported yet");
        } else {
          throw new BuilderException(
              "An invalid property '" + name + "' was found in mapping #{" + content + "}.  Valid properties are " + PARAMETER_PROPERTIES);
        }
      }
      if (typeHandlerAlias != null) {
        // 解析 TypeHandler
        builder.typeHandler(resolveTypeHandler(javaType, typeHandlerAlias));
      }
      // 构建 ParameterMapping 对象
      return builder.build();
    }

    private Map<String, String> parseParameterMapping(String content) {
      try {
        return new ParameterExpression(content);
      } catch (BuilderException ex) {
        throw ex;
      } catch (Exception ex) {
        throw new BuilderException(
            "Parsing error was found in mapping #{" + content + "}.  Check syntax #{property|(expression), var1=value1, var2=value2, ...} ", ex);
      }
    }
  }

}
