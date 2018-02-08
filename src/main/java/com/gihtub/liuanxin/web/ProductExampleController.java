package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.WebConstant;
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
@ApiGroup(WebConstant.PRODUCT_DESC)
public class ProductExampleController {

    @ApiMethod(title = "商品测试接口", develop = WebConstant.PRODUCT_DEVELOP1)
    @GetMapping("/demo")
    public JsonResult<PageInfo<DemoVo>> demo(@ApiParam(desc = "商品名") String name,
                                             DemoDto demoDto, Page page) {
        return JsonResult.success("test");
    }

    @ApiMethod(title = "商品测试接口2", develop = WebConstant.PRODUCT_DEVELOP2)
    @GetMapping("/demo2")
    public JsonResult<PageInfo<DemoVo>> demo2(@ApiParam(desc = "yy") String name,
                                             DemoDto demoDto) {
        return JsonResult.success("test2");
    }
}
