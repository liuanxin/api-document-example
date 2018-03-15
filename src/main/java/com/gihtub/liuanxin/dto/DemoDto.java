package com.gihtub.liuanxin.dto;

import com.gihtub.liuanxin.enums.Gender;
import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoDto {

    @ApiParam(must = true, desc = "用户 id")
    private Long userId;

    @ApiParam(dataType = "int", desc = "性别")
    private Gender gender;
}
