package com.llg.service;

import com.llg.pojo.Dept;
import com.llg.pojo.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李龙
 * @version 1.0
 */

public interface DeptService {

    List<Dept> list();

    void deleteById(Integer id) throws Exception;

    void insert(Dept dept);

    void update(Dept dept);


    Dept findById(Integer id);
}
