package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.dto.DemoDto;
import com.gihtub.liuanxin.util.JsonResult;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.util.PageInfo;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@ApiGroup(value = Develop.USER_DESC, index = 1)
public class UserExampleController {

    @ApiMethod(title = "用户列表", develop = Develop.USER, index = 1, desc = "管理员查询用户")
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> demo1(DemoDto demoDto, Page page) {
        return JsonResult.success("test1");
    }

    @ApiMethod(title = "用户信息", develop = Develop.USER, index = 2, commentInReturnExample = false)
    @GetMapping("/info")
    public JsonResult<PageInfo<DemoVo>> demo2() {
        return JsonResult.success("test2");
    }

    @ApiMethod(title = "用户详情", develop = Develop.USER, desc = "当用户点击头像时")
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功, 解析数据"),
            @ApiResponse(code = 500, msg = "异常, 输出 msg")
    })
    @PostMapping("/{id}")
    public JsonResult<DemoVo> demo3(@PathVariable("id") @ApiParam(value = "user id", example = "1") Long id) {
        return JsonResult.success("test3");
    }

    @ApiMethod(title = "用户操作", develop = Develop.USER)
    @PostMapping("/operate")
    public JsonResult<PageInfo<DemoVo>> demo4(@ApiParam(value = "动作", paramType = ParamType.Header) String type) {
        return JsonResult.success("test4");
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoVo>> demo4(@ApiParam("xxx") String name, DemoDto demoDto) {
        return JsonResult.success("test4");
    }
}
