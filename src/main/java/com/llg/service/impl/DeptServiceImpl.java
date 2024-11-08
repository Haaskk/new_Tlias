package com.llg.service.impl;

import com.llg.mapper.DeptMapper;
import com.llg.mapper.EmpMapper;
import com.llg.pojo.Dept;
import com.llg.pojo.DeptLog;
import com.llg.service.DeptLogService;
import com.llg.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 李龙
 * @version 1.0
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        List<Dept> list = deptMapper.list();
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            deptMapper.delete(id); //根据ID删除部门数据

            empMapper.deleteByDeptId(id); //根据部门ID删除该部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作,此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public Dept findById(Integer id) {
        Dept resDept = deptMapper.findById(id);
        resDept.setCreateTime(LocalDateTime.now());
        resDept.setUpdateTime(LocalDateTime.now());
        return resDept;
    }


}
