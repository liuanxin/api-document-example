package com.github.liuanxin.web;

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

    @ApiMethod(value = "用户列表", develop = Develop.USER, index = 1, desc = "管理员查询用户<br><br>" +
            "比如约定好的 appId 是 aaa, secret 是 xyz, 要传递的参数是 id=123&name=x&gender=1<br>" +
            "1. 将参数值按自然序排列: gender=1&id=123&name=x<br>" +
            "2. 生成时间戳(到秒即可, 此处仅示例)及随机数(不带 - 的 32 位 uuid, 此处仅示例): timestamp=11111&nonce=abc123<br>" +
            "3. 将 排序后的参数 + 时间戳的值 + 随机数的值 + appId 的值 + secret 的值 拼起来: gender=1&id=123&name=x&11111&abc123&aaa&xyz<br>" +
            "4. 生成签名值: md5(gender=1&id=123&name=x&ms=11111&nonce=abcde&appId=abc&appSecret=xyz) = a241f4ef3a4bc866df88bbdeeb021696<br>" +
            "<br>最终发送时:<br>" +
            "请求头(加上 appId 及加密后的值): x-app: abc, x-sign: a241f4ef3a4bc866df88bbdeeb021696<br>" +
            "参数(在原来的基础上加上时间戳及随机数): id=123&name=x&gender=1 & timestamp=11111&nonce=abc123")
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
