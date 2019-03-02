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

    // 应该只有响应编码就可以了, 当前实体表示处理成功后的返回, 200 以外的响应编码统一处理
    // @ApiReturn("返回码")
    // private int code;

    @ApiReturn("返回说明. 如: 收货地址添加成功 等")
    private String msg;

    @ApiReturn("返回的数据. 实体 {\"id\":1} 还是列表 [{\"id\":1},{\"id\":2}] 依具体的业务而定")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private JsonResult(/*int code, */String msg) {
        // this.code = code;
        this.msg = msg;
    }
    private JsonResult(/*int code, */String msg, T data) {
        // this.code = code;
        this.msg = msg;
        this.data = data;
    }


    // --------------------

    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<T>(msg);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<T>(msg, data);
    }

    /*
    public static <T> JsonResult<T> badRequest(String msg) {
        return new JsonResult<T>(JsonCode.BAD_REQUEST.getFlag(), msg);
    }
    public static <T> JsonResult<T> notLogin(String msg) {
        return new JsonResult<T>(JsonCode.NOT_LOGIN.getFlag(), msg);
    }
    public static <T> JsonResult<T> notPermission(String msg) {
        return new JsonResult<T>(JsonCode.NOT_PERMISSION.getFlag(), msg);
    }
    public static <T> JsonResult<T> notFound() {
        return new JsonResult<T>(JsonCode.NOT_FOUND.getFlag(), "404");
    }
    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<T>(JsonCode.FAIL.getFlag(), msg);
    }
    */

    /*
    public static <T> JsonResult<T> serviceFail(String msg) {
        return new JsonResult<T>(JsonCode.SERVICE_FAIL.getFlag(), msg);
    }
    */
}
