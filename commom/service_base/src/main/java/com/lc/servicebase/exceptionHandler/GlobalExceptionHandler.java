package com.lc.servicebase.exceptionHandler;

import com.lc.commomutils.ResultClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//RestControllerAdvice来做全局异常处理并且返回数据
@RestControllerAdvice
//表示用到logback日志，需要写入日志信息
@Slf4j
public class GlobalExceptionHandler {

    //知道出现什么异常，才会执行这个方法
    //ControllerAdvice和ExceptionHandler常用于做全局的异常处理
    @ExceptionHandler(Exception.class)
    public ResultClass error(Exception e) {
        e.printStackTrace();
        return ResultClass.Error().message("执行了全局异常提示");
    }

    //特定异常处理，需要具体知道到哪个异常
    @ExceptionHandler(ArithmeticException.class)
    public ResultClass error(ArithmeticException e) {
        e.printStackTrace();
        return ResultClass.Error().message("执行了ArithmeticException异常处理");
    }

    //自定义异常处理
    @ExceptionHandler(GuliException.class)
    public ResultClass error(GuliException e) {
        //将错误信息写入到日志文件中
        log.error(e.getMessage());

        e.printStackTrace();
        return ResultClass.Error().code(e.getCode()).message(e.getMsg());
    }
}