package com.kyletiger.cloud.common;


import com.kyletiger.cloud.common.entity.ErrorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Global Exception Handler..
 *
 * @author 郑立松 - Albert Zheng <lisong.zheng@gmail.com>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorEntity<String> jsonErrorHandler(HttpServletRequest request, Exception cause) throws Exception {
        LOGGER.error("Global Exception Handler: ", cause);

        ErrorEntity<String> err = new ErrorEntity<>();
        err.setCode(ErrorEntity.SERVER_ERROR);
        err.setMessage(cause.getMessage());
        err.setUrl(request.getRequestURL().toString());
        err.setData("演示如何编写Spring Boot中Web应用的统一异常处理！");

        return err;
    }
}
