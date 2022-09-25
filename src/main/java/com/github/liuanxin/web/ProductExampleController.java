package com.github.liuanxin.web;

import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import com.github.liuanxin.api.annotation.ApiTokens;
import com.github.liuanxin.constant.Develop;
import com.github.liuanxin.enums.ProductType;
import com.github.liuanxin.req.DemoReq;
import com.github.liuanxin.res.DemoRes;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.Page;
import com.github.liuanxin.util.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@ApiGroup(value = Develop.PRODUCT_DESC, index = 2)
public class ProductExampleController {

    @ApiMethod(value = "商品列表(分页)", develop = Develop.PRODUCT, index = -1)
    @GetMapping
    public JsonResult<PageInfo<DemoRes>> page(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return JsonResult.success("列表", DemoRes.testPageData());
    }

    @ApiMethod(value = "商品详情(实体)", develop = Develop.PRODUCT, index = 0)
    @GetMapping("/{id}")
    public JsonResult<DemoRes> id(@PathVariable("id") @ApiParam("商品 id") Long id) {
        return JsonResult.success("详情", DemoRes.testData());
    }

    @ApiTokens
    @ApiMethod(value = "商品列表(List)", develop = Develop.PRODUCT, index = 1)
    @GetMapping("/list")
    public JsonResult<List<DemoRes>> list(@ApiParam("商品类型") ProductType productType) {
        return JsonResult.success("列表", DemoRes.testListData());
    }

    @ApiMethod(value = "商品关系(Map)", develop = Develop.PRODUCT, index = 2)
    @GetMapping("/map")
    public JsonResult<Map<String, DemoRes>> map(@ApiParam("yy") Long id, DemoReq demoReq) {
        demoReq.basicCheck();
        return JsonResult.success("map", DemoRes.testMapData());
    }
}
