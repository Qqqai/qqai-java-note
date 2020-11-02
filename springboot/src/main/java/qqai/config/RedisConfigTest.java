package qqai.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author qqai
 * @createTime 2020/10/9 10:38
 * @description：redisconfig 定制
 */

@Configuration
// 绑定spring.cache前缀的配置文件内容
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfigTest {
    /**
     * 笔记 自定义 redisTemplate  这是一种方法 还有一种方法
     *
     * @param factory
     * @return
     */
    @Bean
    RedisTemplate<String, Object> CustomRedisTemplateOne(RedisConnectionFactory factory) {
        // 创建出redisTemplate实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(factory);
        // 设置key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置hashKey的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置hashValue的序列化方式
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        // 设置value的序列化方式
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 添加实例到ioc容器中
        return redisTemplate;
    }

    //  标记 在类上加上 @EnableConfigurationProperties(CacheProperties.class) 这个注解这种配置方式才能生效，这个是在自定义RedisCacheConfiguration配置工厂
    //  标记 CacheProperties就是yml文件中对于redis得配置 修改这个配工厂之后再加载RedisTemplate就会自动按照这个工厂得内容进行配置
    //  标记 这种配置方式虽然可以 但是比较麻烦 是前几个版本得配置模式  RedisCacheConfiguration这个类也被优化了
//    @Bean  // 标记  不加这个注解这个类得配置是不生效得  因为在ioc容器中没有这个自定义得 spring就会去找默认得配置
//    RedisCacheConfiguration CustomRedisTemplateTwo(CacheProperties cacheProperties) {
//        //获取配置文件配置的属性
//        CacheProperties.Redis redis = cacheProperties.getRedis();
//        //获取redisCacheConfig对象
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        //设置key的序列化方式
//        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
//        //设置值的序列化方式
//        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//        //判断配置文件是否设置过期时间
//        if (redis.getTimeToLive() != null) {
//            config = config.entryTtl(redis.getTimeToLive());
//        }
//        //配置文件是否设置cacheName的前缀
//        if (redis.getKeyPrefix() != null) {
//            config = config.prefixCacheNameWith(redis.getKeyPrefix());
//        }
//        //配置文件是否允许存入null值   （必须为true  解决缓存穿透的问题）
//        if (!redis.isCacheNullValues()) {
//            config = config.disableCachingNullValues();
//        }
//        //配置文件是否设置允许使用前缀  默认为true  如果没有设置前缀而且制定了分区名  那么分区名就是前缀
//        if (!redis.isUseKeyPrefix()) {
//            config = config.disableKeyPrefix();
//        }
//        return config;
//    }
}
