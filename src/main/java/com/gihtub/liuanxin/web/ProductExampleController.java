package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.dto.DemoDto;
import com.gihtub.liuanxin.util.JsonResult;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.util.PageInfo;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@ApiGroup(value = Develop.PRODUCT_DESC, index = 2)
public class ProductExampleController {

    @ApiMethod(title = "商品列表", develop = Develop.PRODUCT)
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> demo(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return JsonResult.success("test");
    }

    @ApiMethod(title = "商品详情", develop = Develop.PRODUCT)
    @GetMapping("/info")
    public JsonResult<DemoVo> demo2(@ApiParam("yy") Long id, DemoDto demoDto) {
        return JsonResult.success("test2");
    }
}
