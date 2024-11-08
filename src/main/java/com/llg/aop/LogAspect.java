package com.llg.aop;

import com.alibaba.fastjson.JSON;
import com.llg.mapper.OperateLogMapper;
import com.llg.pojo.OperateLog;
import com.llg.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author 李龙
 * @version 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private  HttpServletRequest request;

    @Around("@annotation(com.llg.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作人的ID-当前登录员工的ID

        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUserId = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用目标方法运行
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();


        //方法返回值
        String resultValue = JSON.toJSONString(result);
        //操作耗时
        Long costTime=end-begin;



        OperateLog operateLog = new OperateLog(null,operateUserId,operateTime,className,methodName,
                methodParams,resultValue,costTime);
        operateLogMapper.insert(operateLog);

       log.info("AOP记录日志,{}",operateLog);

        return result;
    }
}
