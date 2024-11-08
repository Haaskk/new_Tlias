package com.llg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llg.mapper.EmpMapper;
import com.llg.pojo.Emp;
import com.llg.pojo.PageBean;
import com.llg.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 李龙
 * @version 1.0
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

   /* @Override
    public PageBean pageBean(Integer page, Integer pageSize) {
        //获取总记录数
        Integer total = empMapper.total();

        //获取emp列表
        Integer start = (page - 1) * pageSize;
        List<Emp> list = empMapper.list(start, pageSize);

        //将list封装到pageBean
        PageBean pageBean = new PageBean(total, list);
        return pageBean;
    }*/


    @Override
    public PageBean pageBean(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);

        //执行查询操作
        List<Emp> list = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) list;

        //将list封装到pageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.save(emp);
    }

    @Override
    public Emp findById(Integer id) {
        Emp emp = empMapper.findById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
