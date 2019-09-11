package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.dto.DemoDto;
import com.gihtub.liuanxin.dto.RequestBodyDto;
import com.gihtub.liuanxin.enums.UserType;
import com.gihtub.liuanxin.exception.ServiceException;
import com.gihtub.liuanxin.util.JsonResult;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.util.PageInfo;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiTokens
@RestController
@RequestMapping("/user")
@ApiGroup(value = Develop.USER_DESC, index = 1)
public class UserExampleController {

    @ApiMethod(value = "用户列表", develop = Develop.USER, index = 1, desc = "管理员查询用户")
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> demo1(DemoDto demoDto, Page page) {
        return JsonResult.success("test1");
    }

    @ApiMethod(value = "用户信息", develop = Develop.USER, index = 2, commentInReturnExample = false)
    @GetMapping("/info")
    public JsonResult<PageInfo<DemoVo>> demo2(@ApiParam("用户类型") UserType type) {
        return JsonResult.success("test2");
    }

    @ApiMethod(value = "用户详情", develop = Develop.USER, desc = "当用户点击头像时")
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功, 解析数据"),
            @ApiResponse(code = 500, msg = "异常, 输出 response body")
    })
    @PostMapping("/{id}")
    public JsonResult<DemoVo> demo3(@PathVariable("id") @ApiParam(value = "user id", example = "1") Long id) {
        return JsonResult.success("test3");
    }

    @ApiMethod(value = "用户操作", develop = Develop.USER)
    @PostMapping("/operate")
    public JsonResult<PageInfo<DemoVo>> demo4(@ApiParam("动作(0 从上往下, 1 从下往上, 默认是 0)") Boolean type) {
        if (type != null && !type) {
            throw new ServiceException("动作有误");
        }
        return JsonResult.success("test4");
    }

    @ApiMethod(value = "用户变更", develop = Develop.USER)
    @PostMapping("/detail")
    public JsonResult<List<DemoVo>> demo5(@RequestBody RequestBodyDto demoDto) {
        return JsonResult.success("test5");
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoVo>> demo4(String name, DemoDto demoDto) {
        return JsonResult.success("test4");
    }
}
