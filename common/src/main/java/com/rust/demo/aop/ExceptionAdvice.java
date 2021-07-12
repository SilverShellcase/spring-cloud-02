package com.rust.demo.aop;

import com.rust.demo.common.Result;
import com.rust.demo.exception.ServiceException;
import com.rust.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Configuration
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ServiceException.class)
    public Result handleBadRequest(ServiceException ex) {
        log.error("业务异常:", ex);
        return ResultUtil.failed(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleError(Exception ex) {
        log.error("服务器内部异常:", ex);
        return ResultUtil.error(ex.getMessage());
    }
}
