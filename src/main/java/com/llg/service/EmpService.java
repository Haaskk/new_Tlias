package com.llg.service;

import com.llg.pojo.Emp;
import com.llg.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 李龙
 * @version 1.0
 */

public interface EmpService {
    PageBean pageBean(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp findById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
