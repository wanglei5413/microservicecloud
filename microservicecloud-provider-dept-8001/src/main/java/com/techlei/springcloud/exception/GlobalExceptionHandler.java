package com.techlei.springcloud.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 全局异常捕获
 */
@ControllerAdvice("com.techlei.springcloud.controller")
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public HashMap<String, String> handlerException(HttpServletRequest request, Throwable e){
        HashMap<String, String> ret = new HashMap<>();
        e.printStackTrace();
        ret.put("code","500");
        ret.put("msg","出错了");
        return ret;
    }

}
