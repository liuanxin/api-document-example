package com.gihtub.liuanxin.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JsonResult<T> {

    @ApiReturn(desc = "返回码. 根据此值控制页面扭转: 0.显示 msg, 1.业务处理, 10.导向登录页 等")
    private int code;

    @ApiReturn(desc = "返回说明. 如: 用户名密码错误, 收货地址添加成功 等")
    private String msg;

    @ApiReturn(desc = "返回的数据. 返回实体 {\"id\":1} 还是列表 [{\"id\":1},{\"id\":2}] 依具体的业务而定")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<T>(0, msg);
    }
    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<T>(1, msg);
    }
}
