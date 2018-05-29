package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: UserController.java
 * @Description: springboot 捕获全局异常
 *                  自定义异常处理助手类 记录异常信息、请求路径、返回页面
 *
 *                 @ControllerAdvice
 *                 @ExceptionHandler
 *
 * @author LH-Yu
 * @Date 2018-5-29
 * @version V1.0
 */
@ControllerAdvice //异常助手类  出现异常进行异常捕获处理
public class BootExceptionHandler {

    public static final String ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest reqest,
                               HttpServletResponse response, Exception e) throws Exception {

    	e.printStackTrace();

		ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", reqest.getRequestURL());
        mav.setViewName(ERROR_VIEW);
        return mav;
    }

}
