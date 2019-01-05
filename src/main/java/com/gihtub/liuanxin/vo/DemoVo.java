package com.gihtub.liuanxin.vo;

import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoVo {

    @ApiReturn(value = "用户 id", example = "123")
    private Long id;

    @ApiReturn(value = "用户名称", example = "张三")
    private String name;

    // 文档收集 会将 enum 的 getCode: getValue 信息(没有则拼接 name)附加进说明
    @ApiReturn(value = "性别", type = "int")
    private Gender gender;

    @ApiReturn(value = "商品类型", type = "int")
    private ProductType type;

    @ApiReturn(value = "返回 Map")
    private Map<Integer, DemoOtherVo> map;


    @Getter
    @Setter
    public static class DemoOtherVo {
        @ApiReturn(value = "其他的 id", example = "12345")
        private Long otherId;

        @ApiReturn(value = "描述", example = "一二三")
        private String desc;
    }
}
