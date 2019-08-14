package com.gihtub.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    @ApiParam("当前页数")
    private Integer page;

    @ApiParam("每页条数")
    private Integer limit;
}
