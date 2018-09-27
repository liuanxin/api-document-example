package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @see org.springframework.boot.web.servlet.error.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
 */
@ControllerAdvice
public class GlobalException {

    @Value("${online:false}")
    private boolean online;

    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult noHandler(NoHandlerFoundException e) {
        // debug log
        String msg = String.format("未找到请求(%s %s)", e.getHttpMethod(), e.getRequestURL());
        return JsonResult.notFound(msg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult missParam(MissingServletRequestParameterException e) {
        // debug log
        String msg = String.format("缺少必要的参数(%s), 类型(%s)", e.getParameterName(), e.getParameterType());
        return JsonResult.badRequest(msg);
    }

    @ExceptionHandler(Throwable.class)
    public JsonResult other(Throwable e) {
        String msg;
        if (online) {
            msg = "请求错误, 我们会尽快处理";
        } else if (e instanceof NullPointerException) {
            msg = "空指针异常, 请联系后台技术查看日志进行处理";
        } else {
            msg = e.getMessage();
        }
        // error log
        return JsonResult.fail(msg);
    }
}
