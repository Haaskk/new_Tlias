package com.llg.exception;

import com.llg.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 李龙
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception e){
        e.getMessage();
        return Result.error("对不起,操作失败,查看操作对象是否已存在");
    }
}
