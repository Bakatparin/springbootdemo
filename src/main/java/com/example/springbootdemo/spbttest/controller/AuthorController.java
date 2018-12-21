package com.example.springbootdemo.spbttest.controller;

import com.example.springbootdemo.spbttest.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {
    @Value("${author.name}")
    private String authorNmae;
    @Value("${author.age}")
    private String authorage;
    @Value("${content}")
    private String content;

    @Autowired
    private Author author;


    @RequestMapping(value={"/hello","/hi"})
    public String hello(){
        //return "作者："+authorNmae+",年龄："+authorage+"--------"+content+"-----------"+author.getName()+author.getAge();
        return "index";
    }

    //http://localhost:8080/hello1/22
    @RequestMapping(value="/hello1/{id}")
    public String hello1(@PathVariable("id") Integer id){
        return "id:"+id;//22
    }

    //http://localhost:8080/hello2?id=100
    @RequestMapping(value="/hello2",method = RequestMethod.GET)
    //@GetMapping("/hello2")
    public String hello2(@RequestParam(value="id",defaultValue = "0",required = false) Integer id){
        return "id:"+id;//100
    }


}
