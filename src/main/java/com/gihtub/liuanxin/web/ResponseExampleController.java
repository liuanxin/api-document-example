package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/response")
@ApiGroup("response-响应")
public class ResponseExampleController {

    @ApiMethod(title = "响应实体", develop = Develop.PRODUCT, index = 3)
    @ApiResponses({
            @ApiResponse(code = 404, msg = "当 name 传入 abc 时返回, 表示没有找到请求"),
            @ApiResponse(code = 200, msg = "正常返回数据")
    })
    @GetMapping("/demo-object")
    public ResponseEntity<DemoVo> demoObject(@ApiParam(value = "商品名", textarea = true) @RequestParam("name") String abc,
                                             @ApiParam(value = "头1", paramType = ParamType.Header) @RequestHeader Long id,
                                             @ApiParam(value = "头2", paramType = ParamType.Header) @RequestHeader("some") String xyz,
                                             Page page) {
        if ("abc".equals(abc)) {
            return ResponseEntity.notFound().build();
            // return new ResponseEntity<>(new DemoVo(), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(new DemoVo(123L, "张三", Gender.Male, ProductType.Discount, null));
        }
    }

    @ApiMethod(title = "响应 List", develop = Develop.PRODUCT, index = 4)
    @ApiResponses({
            @ApiResponse(code = 500, msg = "当 name 传入 xyz 时返回, 表示后台错误(展示歉意并说明后台将会尽快处理)"),
            @ApiResponse(code = 200, msg = "正常返回数据")
    })
    @GetMapping("/demo-list")
    public ResponseEntity<List<DemoVo>> demoList(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(500).build();
        } else {
            return ResponseEntity.ok(Collections.singletonList(
                    new DemoVo(123L, "张三", Gender.Male, ProductType.Discount, null)
            ));
        }
    }

    @ApiMethod(title = "响应 Map", develop = Develop.PRODUCT, index = 5)
    @ApiResponses({
            @ApiResponse(code = 403, msg = "当 name 传入 xyz 时返回, 表示没有权限(导去登录页面)"),
            @ApiResponse(code = 200, msg = "正常返回数据")
    })
    @GetMapping("/demo-map")
    public ResponseEntity<Map<String, DemoVo>> demoMap(@ApiParam(value = "商品名", textarea = true) String name,
                                                       Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(401).build();
        } else {
            return ResponseEntity.ok(Collections.singletonMap(
                    "123", new DemoVo(123L, "张三", Gender.Male, ProductType.Discount, null)
            ));
        }
    }


    // 下面的返回结果无法被解析

    @ApiMethod(title = "响应无法被解析1", develop = Develop.PRODUCT, index = 6)
    @GetMapping("/demo-error")
    public Object demoError(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return new HashMap<>();
    }

    @ApiMethod(title = "响应无法被解析2", develop = Develop.PRODUCT, index = 7)
    @GetMapping("/demo-error2")
    public ResponseEntity demoError2(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return ResponseEntity.ok(null);
    }

    @ApiMethod(title = "响应无法被解析3", develop = Develop.PRODUCT, index = 7)
    @GetMapping("/demo-error3")
    public ResponseEntity<Object> demoError3(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return ResponseEntity.ok(new HashMap<>());
    }
}
