package com.gihtub.liuanxin.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** <span style="color:red;">!!!此实体类请只在 Controller 中使用, 且只调用其 static 方法!!!</span> */
@Setter
@Getter
@NoArgsConstructor
public class JsonResult<T> {

    // @ApiReturn("返回码")
    // private JsonCode code;

    @ApiReturn("返回说明")
    private String msg;

    @ApiReturn("返回数据. 实体 {\"id\":1} 还是数组 [{\"id\":1},{\"id\":2}] 依业务")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private JsonResult(JsonCode code, String msg) {
        // this.code = code;
        this.msg = msg;
    }
    private JsonResult(JsonCode code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }


    // ---------- 在 controller 中请只使用下面的静态方法. 不要 new JsonResult()... 这样操作 ----------

    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<T>(JsonCode.SUCCESS, msg);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<T>(JsonCode.SUCCESS, msg, data);
    }


    public static <T> JsonResult<T> badRequest(String msg) {
        // return new JsonResult<T>(JsonCode.BAD_REQUEST, msg);
        return new JsonResult<T>(JsonCode.FAIL, msg);
    }
    public static <T> JsonResult<T> needLogin(String msg) {
        return new JsonResult<T>(JsonCode.NOT_LOGIN, msg);
    }
    public static <T> JsonResult<T> needPermission(String msg) {
        // return new JsonResult<T>(JsonCode.NOT_PERMISSION, msg);
        return new JsonResult<T>(JsonCode.FAIL, msg);
    }
    public static <T> JsonResult<T> notFound() {
        // return new JsonResult<T>(JsonCode.NOT_FOUND, "404");
        return new JsonResult<T>(JsonCode.FAIL, "404");
    }
    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<T>(JsonCode.FAIL, msg);
    }

    // public static <T> JsonResult<T> xxxServiceFail(String msg) {
    //     return new JsonResult<T>(JsonCode.SERVICE_FAIL, msg);
    // }
}
