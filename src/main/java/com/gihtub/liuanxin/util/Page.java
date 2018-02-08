package com.gihtub.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    @ApiParam(desc = "当前页数. 不传或传入 0, 或负数, 或非数字则默认是 1")
    private Integer page;

    @ApiParam(desc = "每页条数. 不传或传入 0, 或负数, 或非数字, 或大于 1000 则默认是 15")
    private Integer limit;
}
