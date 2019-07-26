package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.gihtub.liuanxin.exception.ServiceException;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/response")
@ApiGroup("response-响应")
public class ResponseExampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExampleController.class);

    @ApiMethod(value = "响应实体", develop = Develop.PRODUCT, index = 3)
    @ApiResponses({
            @ApiResponse(code = 404, msg = "当 name 传入 abc 时返回, 表示没有找到请求"),
            @ApiResponse(code = 200, msg = "正常返回数据")
    })
    @PostMapping("/demo-object")
    public ResponseEntity<DemoVo> demoObject(@ApiParam(value = "商品名", textarea = true) @RequestParam("name") String abc,
                                             @ApiParam(value = "头1", paramType = ParamType.Header) @RequestHeader Long id,
                                             @ApiParam(value = "头2", paramType = ParamType.Header) @RequestHeader("some") String xyz,
                                             @ApiParam("文件上传1") MultipartFile file1,
                                             @ApiParam(value = "文件上传2", must = true) MultipartFile file2,
                                             Page page) {
        if ("abc".equals(abc)) {
            throw new ServiceException("商品名有误");
        }
        if (id == null || id < 0) {
            throw new ServiceException("头1 要有值且必须大于等于 0");
        }
        if (xyz == null || "".equals(xyz.trim())) {
            throw new ServiceException("头2 不能为空");
        }
        if (file2 == null) {
            throw new ServiceException("文件上传2 不能为空");
        }

        String fileName = file2.getOriginalFilename();
        if (fileName == null || !fileName.matches("(?i)^(.*)\\.(ico|jpeg|jpg|bmp|png)$")) {
            throw new ServiceException("请上传图片文件");
        }

        // save file
        try {
            String saveFile = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
            file2.transferTo(new File("/" + saveFile));
        } catch (IOException e) {
            String msg = "保存文件异常";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(msg, e);
            }
            throw new ServiceException(msg);
        }

        // 正常返回
        return ResponseEntity.ok(new DemoVo(123L, "张三", Gender.Male, ProductType.Discount, null));
    }

    @ApiMethod(value = "响应 List", develop = Develop.PRODUCT, index = 4)
    @ApiResponses({
            @ApiResponse(code = 500, msg = "当 name 传入 xyz 时返回, 后台错误"),
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

    @ApiMethod(value = "响应 Map", develop = Develop.PRODUCT, index = 5)
    @ApiResponses({
            @ApiResponse(code = 403, msg = "当 name 传入 xyz 时返回, 表示没有权限(导去登录页面)"),
            @ApiResponse(code = 200, msg = "正常返回数据")
    })
    @GetMapping("/demo-map")
    public ResponseEntity<Map<String, DemoVo>> demoMap(@ApiParam(value = "商品名", textarea = true) String name,
                                                       Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(403).build();
        } else {
            return ResponseEntity.ok(Collections.singletonMap(
                    "123", new DemoVo(123L, "张三", Gender.Male, ProductType.Discount, null)
            ));
        }
    }


    // 下面的返回结果无法被解析

    @ApiMethod(value = "响应无法被解析1", develop = Develop.PRODUCT, index = 6)
    @GetMapping("/demo-error")
    public Object demoError(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return new HashMap<>();
    }

    @ApiMethod(value = "响应无法被解析2", develop = Develop.PRODUCT, index = 7)
    @PostMapping("/demo-error2")
    public ResponseEntity demoError2(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return ResponseEntity.ok(null);
    }

    @ApiMethod(value = "响应无法被解析3", develop = Develop.PRODUCT, index = 7)
    @GetMapping("/demo-error3")
    public ResponseEntity<Object> demoError3(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return ResponseEntity.ok(new HashMap<>());
    }
}
