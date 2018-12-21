package com.example.springbootdemo.spbttest.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Create by Bakatparin
 * on 2018/9/26
 */
@Component
@ConfigurationProperties(prefix = "author")
@PropertySource("classpath:application.yml")/*指定配置文件*/
public class Author {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
