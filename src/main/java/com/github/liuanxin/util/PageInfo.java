package com.github.liuanxin.util;

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

    @ApiReturn("SELECT COUNT(*) FROM ... 的结果")
    private int total;

    @ApiReturn("SELECT ... FROM ... LIMIT 0, 15 的结果")
    private List<T> list;
}
