package com.gihtub.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageInfo<T> {

    @ApiReturn("总条数. 根据此值和 page limit 构建分页按钮")
    private int total;

    @ApiReturn("当前页的数据")
    private List<T> list;
}
