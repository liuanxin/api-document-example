package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.WebConstant;
import com.gihtub.liuanxin.dto.DemoDto;
import com.gihtub.liuanxin.util.JsonResult;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.util.PageInfo;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@ApiGroup(value = WebConstant.USER_DESC, index = 1)
public class UserExampleController {

    @ApiMethod(title = "用户列表", develop = WebConstant.USER_DEVELOP1, index = 1)
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> demo1(DemoDto demoDto, Page page) {
        return JsonResult.success("test1");
    }

    @ApiMethod(title = "用户信息", develop = WebConstant.USER_DEVELOP1, index = 3)
    @GetMapping("/info")
    public JsonResult<PageInfo<DemoVo>> demo2() {
        return JsonResult.success("test2");
    }

    @ApiMethod(title = "用户详情", develop = WebConstant.USER_DEVELOP2, index = 2)
    @ApiResponses({
            @ApiResponse(code = 400, msg = "请求参数有误"),
            @ApiResponse(code = 500, msg = "请求异常")
    })
    @PostMapping("/{id}")
    public JsonResult<DemoVo> demo3(@PathVariable("id") @ApiParam(desc = "xxx") Long id) {
        return JsonResult.success("test3");
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoVo>> demo4(@ApiParam(desc = "xxx") String name, DemoDto demoDto) {
        return JsonResult.success("test4");
    }
}
