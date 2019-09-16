package com.gihtub.liuanxin.dto;

import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.gihtub.liuanxin.exception.ServiceException;
import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DemoDto {

    @ApiParam(value = "用户 id", must = true)
    private Long userId;

    // 文档收集 会将 enum 的 getCode: getValue 信息(没有则拼接 name)附加进说明
    @ApiParam(value = "性别", dataType = "int")
    private Gender gender;

    @ApiParam("商品类别")
    private ProductType[] types;

    @ApiParam(datePattern = "YYYY-MM-DD HH:mm:ss", must = true)
    private Date date;

    public void basicCheck() {
        if (userId == null || userId <= 0) {
            throw new ServiceException("用户 id 必须传入且要是数字且必须大于 0");
        }
    }
}
