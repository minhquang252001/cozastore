package com.example.cozastoreweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class Redisconfig {

    @Value("${redis.host}")
    public String host;

    @Value("${redis.port}")
    public int port;
    //Tạo kết nối tới Redis
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);

        return new LettuceConnectionFactory(configuration);
    }
    //Mặc định ridis đã có dùng @primary sẽ chạy ưu tiên này trước
    //RedisConnectionFactory là con của LettuceConnectionFactory và đã đưa lên @Bean nên thằng con có những
    //dữ liệu của thằng cha và RedisTemplate<String,String> trả về dạng String để tất cả ngôn ngữ khác có thể
    //sử dụng được và chạy trước Redis mặc định.
    @Bean
    @Primary
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }



}
