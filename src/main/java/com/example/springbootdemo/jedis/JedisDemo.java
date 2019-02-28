package com.example.springbootdemo.jedis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Create by Bakatparin
 * on 2018/10/11
 */
@RestController
public class JedisDemo {

    @GetMapping("/jedis/test1")
    public String test1(){
        //Jedis jedis = new Jedis("192.168.197.130",6379);
        Jedis jedis = new Jedis("47.107.87.79",6379);//阿里云地址
        //Jedis jedis =new Jedis("192.168.10.226",6371);//192.168.10.226 -p 6371
        jedis.set("login","root");
        jedis.set("password","haha5206.");
        System.out.println(jedis.get("name"));
        return jedis.get("name");
    }


    @GetMapping("/jedis/test2")
    public String test2(){
        //1.获取缓存池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //设置最大空闲连接
        config.setMaxIdle(3);

        //2.获得缓存池
        JedisPool jedisPool = new JedisPool(config,"47.107.87.79",6379);

        //3.通过缓存池获得Jedis对象
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return jedis.get("name");
    }
}
