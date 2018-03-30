package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @see org.springframework.boot.autoconfigure.web.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 */
@ControllerAdvice
public class GlobalException {

    private static final HttpStatus FAIL = HttpStatus.INTERNAL_SERVER_ERROR;

    private static final Pattern REQUIRED_PARAMETER = Pattern.compile(".*?\'(.*?)\'.*?");

    @Value("${online:false}")
    private boolean online;

    /*
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<JsonResult> service(ServiceException e) {
        String msg = e.getMessage();
        // debug log
        return new ResponseEntity<>(JsonResult.fail(msg), FAIL);
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<JsonResult> notLogin(NotLoginException e) {
        String msg = e.getMessage();
        // debug log
        return new ResponseEntity<>(JsonResult.notLogin(msg), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<JsonResult> forbidden(ForbiddenException e) {
        String msg = e.getMessage();
        // debug log
        return new ResponseEntity<>(JsonResult.notPermission(msg), HttpStatus.FORBIDDEN);
    }
    */

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<JsonResult> noHandler(NoHandlerFoundException e) {
        // debug log
        return new ResponseEntity<>(JsonResult.notFound(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<JsonResult> missParam(MissingServletRequestParameterException e) {
        // debug log
        Matcher matcher = REQUIRED_PARAMETER.matcher(e.getMessage());
        String showMsg = "缺少必须的参数";
        if (matcher.find()) {
            showMsg += "(" + matcher.group(1) + ")";
        }
        return new ResponseEntity<>(JsonResult.badRequest(showMsg), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(JsonResult.fail(msg), FAIL);
    }
}
