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

    @ApiMethod(title = "用户列表", develop = WebConstant.USER_DEVELOP1, index = 1, desc = "管理员查询用户")
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> demo1(DemoDto demoDto, Page page) {
        return JsonResult.success("test1");
    }

    @ApiMethod(title = "用户信息", develop = WebConstant.USER_DEVELOP1, index = 2, commentInReturnExample = false)
    @GetMapping("/info")
    public JsonResult<PageInfo<DemoVo>> demo2() {
        return JsonResult.success("test2");
    }

    @ApiMethod(title = "用户详情", develop = WebConstant.USER_DEVELOP2, desc = "用户在页面头上点击自己的名字或头像时")
    @ApiResponses({
            @ApiResponse(code = 200, msg = "请求成功"),
            @ApiResponse(code = 500, msg = "请求异常")
    })
    @PostMapping("/{id}")
    public JsonResult<DemoVo> demo3(@PathVariable("id") @ApiParam(desc = "用户 id", example = "1") Long id) {
        return JsonResult.success("test3");
    }

    @ApiMethod(title = "用户操作", develop = WebConstant.USER_DEVELOP1)
    @PostMapping("/operate")
    public JsonResult<PageInfo<DemoVo>> demo4(@ApiParam(desc = "动作", paramType = ParamType.Header) String type) {
        return JsonResult.success("test4");
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoVo>> demo4(@ApiParam(desc = "xxx") String name, DemoDto demoDto) {
        return JsonResult.success("test4");
    }
}
