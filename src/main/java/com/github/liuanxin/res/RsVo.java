package com.github.liuanxin.res;

import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RsVo {

    private int id;

    @ApiReturn("名称")
    private String name;

    private RsVo self;


    public static RsVo testData() {
        return new RsVo(111, "aaa", new RsVo().setId(222).setName("bbb"));
    }
}
