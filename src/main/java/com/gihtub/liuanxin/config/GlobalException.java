package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.exception.ServiceException;
import com.gihtub.liuanxin.util.JsonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

/**
 * @see org.springframework.boot.web.servlet.error.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
 */
@ControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalException.class);

    @Value("${online:false}")
    private boolean online;

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> service(ServiceException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("业务异常", e);
        }
        return ResponseEntity.status(JsonCode.FAIL.getFlag()).body(e.getMessage());
    }

    // 其他的自定义异常


    // ... spring 的内部异常 ...

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> noHandler(NoHandlerFoundException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("404", e);
        }
        String msg = String.format("未找到请求(%s %s)", e.getHttpMethod(), e.getRequestURL());
        return ResponseEntity.status(JsonCode.NOT_FOUND.getFlag()).body(msg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missParam(MissingServletRequestParameterException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("缺少参数", e);
        }
        String msg = String.format("缺少必要的参数(%s), 类型(%s)", e.getParameterName(), e.getParameterType());
        return ResponseEntity.status(JsonCode.BAD_REQUEST.getFlag()).body(msg);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> missHeader(MissingRequestHeaderException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("缺少头", e);
        }
        return ResponseEntity.status(JsonCode.BAD_REQUEST.getFlag()).body(e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> notSupported(HttpRequestMethodNotSupportedException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method 错误", e);
        }
        String msg = "不支持此种请求方式";
        if (!online) {
            msg += String.format(". 当前(%s), 支持(%s)", e.getMethod(), Arrays.toString(e.getSupportedMethods()));
        }
        return ResponseEntity.status(JsonCode.FAIL.getFlag()).body(msg);
    }


    // ... 其他的异常 ...


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> other(Throwable e) {
        String msg;
        if (online) {
            msg = "请求错误, 我们会尽快处理";
        } else if (e instanceof NullPointerException) {
            msg = "空指针异常, 请联系后台技术查看日志进行处理";
        } else {
            msg = e.getMessage();
        }
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("未知异常", e);
        }
        return ResponseEntity.status(JsonCode.FAIL.getFlag()).body(msg);
    }
}
