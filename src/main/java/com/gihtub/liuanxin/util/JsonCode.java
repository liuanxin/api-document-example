package com.gihtub.liuanxin.util;

/** 返回码 */
public enum JsonCode {

    /** 将 data 解析后渲染页面(依业务而定, 也可能显示 msg 给用户看, 如 收货地址添加成功 这种) */
    SUCCESS(200, "成功. 操作数据或显示 msg 给用户看, 依具体的业务而定"),

    // 跟 500 的处理方式是一致的
    // BAD_REQUEST(400, "参数有误(输出 msg 即可)"),

    /** 导向登录页面引导用户登录 */
    NOT_LOGIN(401, "未登录, 导到登录页"),

    // 跟 500 的处理方式是一致的
    // NOT_PERMISSION(403, "无权限, 提示用户无权限(输出 msg 即可)"),

    /** 不需要额外处理 */
    NOT_FOUND(404, "未找到相应处理(不需要处理)"),

    /** 显示 msg 给用户看 */
    FAIL(500, "参数错误、无权限、内部错误、失败等(输出 msg 即可)");

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
