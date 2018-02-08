package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.WebConstant;
import com.gihtub.liuanxin.dto.DemoDto;
import com.gihtub.liuanxin.util.JsonResult;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.util.PageInfo;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiIgnore;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@ApiGroup(WebConstant.USER_DESC)
public class UserExampleController {

    @ApiMethod(title = "用户测试接口", develop = WebConstant.USER_DEVELOP1)
    @GetMapping("/demo")
    public JsonResult<PageInfo<DemoVo>> demo(@ApiParam(desc = "用户名") String name,
                                             DemoDto demoDto, Page page) {
        return JsonResult.success("test");
    }

    @ApiMethod(title = "用户测试接口2", develop = WebConstant.USER_DEVELOP2)
    @GetMapping("/demo2")
    public JsonResult<PageInfo<DemoVo>> demo2(@ApiParam(desc = "xxx") String name,
                                              DemoDto demoDto) {
        return JsonResult.success("test2");
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoVo>> demo3(@ApiParam(desc = "xxx") String name,
                                              DemoDto demoDto) {
        return JsonResult.success("test2");
    }
}
