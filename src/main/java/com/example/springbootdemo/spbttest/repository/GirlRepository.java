package com.example.springbootdemo.spbttest.repository;

import com.example.springbootdemo.spbttest.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by Bakatparin
 * on 2018/9/28
 */
public interface GirlRepository extends JpaRepository<Girl,Integer> {

    /**
     * 通过年龄查询
     * @param age
     * @return
     */
    public List<Girl> findByAge(Integer age);

    /**
     * 多条件查询
     * @param age
     * @param capSize
     * @return
     */
    public List<Girl> findByAgeAndCupSize(Integer age,String capSize);

}
