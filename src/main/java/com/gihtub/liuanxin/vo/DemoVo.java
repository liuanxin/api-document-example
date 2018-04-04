package com.gihtub.liuanxin.vo;

import com.gihtub.liuanxin.enums.Gender;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoVo {

    @ApiReturn("用户 id")
    private String userId;

    @ApiReturn(value = "性别", type = "int")
    private Gender gender;
}
