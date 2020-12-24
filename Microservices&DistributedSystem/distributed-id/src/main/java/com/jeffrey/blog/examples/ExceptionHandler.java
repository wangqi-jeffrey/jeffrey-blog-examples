package com.jeffrey.blog.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Description:
 *
 * @author WQ
 * @date 2020/12/23 11:43 AM
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        log.error("系统出现未知异常: {}", ExceptionUtils.getStackTrace(e));
    }
}