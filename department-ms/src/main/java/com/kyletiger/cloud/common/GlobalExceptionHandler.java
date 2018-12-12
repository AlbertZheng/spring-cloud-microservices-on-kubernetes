package com.kyletiger.cloud.common;


import com.kyletiger.cloud.common.entity.ErrorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorEntity<String> jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("GlobalExceptionHandler: ", e);

        ErrorEntity<String> err = new ErrorEntity<>();
        err.setCode(ErrorEntity.SERVER_ERROR);
        err.setMessage(e.getMessage());
        err.setUrl(request.getRequestURL().toString());
        err.setData("演示如何编写Spring Boot中Web应用的统一异常处理！");
        return err;
    }
}
