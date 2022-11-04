package com.github.liuanxin.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.liuanxin.api.annotation.*;
import com.github.liuanxin.constant.Develop;
import com.github.liuanxin.enums.UserType;
import com.github.liuanxin.req.DemoReq;
import com.github.liuanxin.req.RequestBodyInnerReq;
import com.github.liuanxin.req.RequestBodyReq;
import com.github.liuanxin.res.DemoRes;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.Page;
import com.github.liuanxin.util.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@ApiGroup(value = Develop.USER_DESC, index = 1)
@Slf4j
@RequiredArgsConstructor
public class UserExampleController {

    private final ObjectMapper objectMapper;

    @ApiMethod(value = "用户列表", develop = Develop.USER, index = 1, desc = "管理员查询用户")
    @GetMapping
    public JsonResult<PageInfo<DemoRes>> demo1(DemoReq demoReq, Page page) {
        return JsonResult.success("test1", DemoRes.testPageData());
    }

    @ApiMethod(value = "用户信息", develop = Develop.USER, index = 2, commentInReturnExample = false)
    @GetMapping("/info")
    public JsonResult<PageInfo<DemoRes>> demo2(@ApiParam("用户类型") UserType type) {
        return JsonResult.success("test2", DemoRes.testPageData());
    }

    @ApiMethod(value = "用户详情", develop = Develop.USER, desc = "当用户点击头像时")
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功, 解析数据"),
            @ApiResponse(code = 500, msg = "异常, 输出 response body")
    })
    @PostMapping("/{id}")
    public JsonResult<DemoRes> demo3(@PathVariable("id") @ApiParam(value = "user id", example = "1") Long id) {
        return JsonResult.success("test3", DemoRes.testData());
    }

//    @ApiMethod(value = "用户操作", develop = Develop.USER)
//    @PostMapping("/operate")
//    public JsonResult<PageInfo<DemoVo>> demo4(@ApiParam("动作(0 从上往下, 1 从下往上, 默认是 0)") Boolean type) {
//        if (type != null && !type) {
//            throw new ServiceException("动作有误");
//        }
//        return JsonResult.success("test4");
//    }

    @ApiMethod(value = "使用 RequestBody", develop = Develop.USER)
    @PostMapping("/detail")
    public JsonResult<List<DemoRes>> demo5(@RequestBody RequestBodyReq demoDto) {
        return JsonResult.success("test5", DemoRes.testListData());
    }

    @ApiMethod(value = "使用 RequestBody(嵌套)", develop = Develop.USER)
    @PostMapping("/add-info")
    public JsonResult<List<DemoRes>> demo6(@RequestBody RequestBodyInnerReq demoDto) {
        return JsonResult.success("test6", DemoRes.testListData());
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoRes>> demo4(String name, DemoReq demoReq) {
        return JsonResult.success("test4");
    }
}
