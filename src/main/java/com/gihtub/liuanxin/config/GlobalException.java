package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @see org.springframework.boot.autoconfigure.web.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 */
@ControllerAdvice
public class GlobalException {

    @Value("${online:false}")
    private boolean online;

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<JsonResult> noHandler(NoHandlerFoundException e) {
        // debug log
        String msg = String.format("Not found(%s %s)", e.getHttpMethod(), e.getRequestURL());
        return new ResponseEntity<>(JsonResult.notFound(msg), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<JsonResult> missParam(MissingServletRequestParameterException e) {
        // debug log
        String msg = String.format("Missing required param(%s), type(%s)", e.getParameterName(), e.getParameterType());
        return new ResponseEntity<>(JsonResult.badRequest(msg), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<JsonResult> other(Throwable e) {
        String msg;
        if (online) {
            msg = "Request Error, We will handle it as soon as possible";
        } else if (e instanceof NullPointerException) {
            msg = "Null Point Exception, Contact the backend to view the log for processing";
        } else {
            msg = e.getMessage();
        }
        // error log
        return new ResponseEntity<>(JsonResult.fail(msg), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
