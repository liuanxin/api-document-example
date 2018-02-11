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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@ApiGroup(value = WebConstant.USER_DESC, index = 1)
public class UserExampleController {

    @ApiMethod(title = "用户列表", develop = WebConstant.USER_DEVELOP1, index = 1)
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> list(DemoDto demoDto, Page page) {
        return JsonResult.success("test");
    }

    @ApiMethod(title = "用户详情", develop = WebConstant.USER_DEVELOP2, index = 0)
    @PostMapping("/{id}")
    public JsonResult<DemoVo> demo2(@PathVariable("id") @ApiParam(desc = "xxx") Long id) {
        return JsonResult.success("test2");
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoVo>> demo3(@ApiParam(desc = "xxx") String name, DemoDto demoDto) {
        return JsonResult.success("test3");
    }
}
