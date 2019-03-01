package com.gihtub.liuanxin.util;

/** 返回码 */
public enum JsonCode {

    SUCCESS(200, "成功. 操作数据或显示 msg 给用户看, 依具体的业务而定"),

    BAD_REQUEST(400, "参数有误(输出 response body 即可)"),

    NOT_LOGIN(401, "未登录, 导到登录页"),

    NOT_PERMISSION(403, "无权限(输出 response body 即可)"),

    NOT_FOUND(404, "未找到相应处理"),

    FAIL(500, "内部错误、业务异常(输出 response body 即可)")

    // , SERVICE_FAIL(1000, "业务异常(输出 response body 即可)")
    ;

    int flag;
    String msg;
    JsonCode(int flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public int getFlag() {
        return flag;
    }
    public String getMsg() {
        return msg;
    }
}
