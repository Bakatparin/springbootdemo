package com.example.springbootdemo.spbttest.controller;

import com.example.springbootdemo.spbttest.repository.GirlRepository;
import com.example.springbootdemo.spbttest.entity.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Create by Bakatparin
 * on 2018/9/28
 */
@RestController
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;

    /**
     * 查询女生列表
     * @return
     */
    @GetMapping("/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @PostMapping("/getById")
    public Girl getById(@RequestParam("id") Integer id){
        return girlRepository.getOne(id);
    }

    /**
     * 添加
     * @param name
     * @param age
     * @param capSize
     * @return
     */
    @PutMapping("/add")
    public Girl add(@RequestParam("name") String name,
                    @RequestParam("age") Integer age,
                    @RequestParam("capSize") String capSize){
        Girl girl = new Girl();
        girl.setName(name);
        girl.setAge(age);
        girl.setCupSize(capSize);
        return girlRepository.save(girl);
    }

    /**
     * 添加（验证）
     * @param girl
     * @param bindingResult 验证结果（随机输出验证错误信息，我无法保证顺序）
     * @return
     */
    @PutMapping("/add1")
    public Girl add1(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return girlRepository.save(girl);
    }

    /**
     * 根据id修改
     * @param id
     * @param name
     * @param age
     * @param capSize
     * @return
     */
    @PutMapping("/updateById/{id}")
    public Girl updateById(@PathVariable("id") Integer id,
                    @RequestParam("name") String name,
                    @RequestParam("age") Integer age,
                    @RequestParam("capSize") String capSize){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setName(name);
        girl.setAge(age);
        girl.setCupSize(capSize);
        return girlRepository.save(girl);
    }

    /**
     * 修改
     * @param girl
     * @return
     */
    @PutMapping("/updateById1/{id}")
    public Girl updateById1(Girl girl){
        return girlRepository.save(girl);
    }

    /**
     * 根据id删除
     * @param id
     */
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }

    /**
     * 通过年龄查询
     * @param age
     * @return
     */
    @GetMapping("/getByAge/{age}")
    public List<Girl> getByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * 多条件查询
     * @param age
     * @param capSize
     * @return
     */
    @PostMapping("/getByAgeAndCapSize")
    public List<Girl> getByAgeAndCapSize(@RequestParam("age") Integer age, @RequestParam("capSize")String capSize){
        return girlRepository.findByAgeAndCupSize(age,capSize);
    }

    /**
     * 多条件查询
     * @param girl
     * @return
     */
    @PostMapping("/getByAgeAndCapSize1")
    public List<Girl> getByAgeAndCapSize1(Girl girl){
        int age = girl.getAge();
        String capSize = girl.getCupSize();
        return girlRepository.findByAgeAndCupSize(age,capSize);
    }

}
