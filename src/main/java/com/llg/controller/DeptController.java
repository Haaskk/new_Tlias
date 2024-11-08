package com.llg.controller;

import com.llg.anno.Log;
import com.llg.pojo.Dept;
import com.llg.pojo.Result;
import com.llg.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李龙
 * @version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     *
     * @return
     */
    @GetMapping/*("/depts")*/
    public Result list() {
        log.info("查询部门的全部信息");
        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    /**
     * 根据id删除部门
     *
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     *
     * @param dept
     * @return
     */
    @Log
    @PostMapping/*("/depts")*/
    public Result add(@RequestBody Dept dept) {
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result emp(@PathVariable Integer id) {
        Dept resDept = deptService.findById(id);
        return Result.success(resDept);
    }

    /**
     * 根据id修改数据
     *
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        deptService.update(dept);
        return Result.success();
    }
}
