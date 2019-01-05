package com.gihtub.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {

    @ApiReturn("总条数. 根据此值和 page limit 构建分页按钮")
    private int total;

    @ApiReturn("当前页的数据")
    private List<T> list;
}
