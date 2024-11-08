package com.llg.controller;

import com.llg.pojo.Emp;
import com.llg.pojo.Result;
import com.llg.service.EmpService;
import com.llg.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李龙
 * @version 1.0
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;


    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        Emp e = empService.login(emp);

        //获取jwt令牌,并且下放给请求端
        if(e!=null){
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            claims.put("name",e.getName());
            String jwt = JwtUtils.generateJwt(claims);

            return Result.success(jwt);
        }

        return Result.error("账号或者密码错误,请重新登录！");
    }
}
