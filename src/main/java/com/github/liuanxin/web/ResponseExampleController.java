package com.github.liuanxin.web;

import com.github.liuanxin.api.annotation.*;
import com.github.liuanxin.constant.Develop;
import com.github.liuanxin.enums.Gender;
import com.github.liuanxin.exception.ServiceException;
import com.github.liuanxin.res.DemoRes;
import com.github.liuanxin.res.R0Res;
import com.github.liuanxin.res.R1Res;
import com.github.liuanxin.res.RsVo;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.Page;
import com.github.liuanxin.util.PageInfo;
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

    @ApiMethod(value = "响应实体", develop = Develop.PRODUCT, index = 1)
    @ApiResponses({
            @ApiResponse(code = 200, msg = "正常返回数据"),
            @ApiResponse(code = 404, msg = "当 name 传入 abc 时返回, 表示没有找到请求")
    })
    @PostMapping("/demo-object")
    public ResponseEntity<DemoRes> demoObject(@ApiParam(value = "商品名", textarea = true) @RequestParam("name") String abc,
                                              @ApiParam(value = "头1", paramType = ParamType.Header) @RequestHeader Long id,
                                              @ApiParam(value = "头2", paramType = ParamType.Header) @RequestHeader("some") String xyz,
                                              @ApiParam(value = "性别", example = "2") Gender gender,
                                              @ApiParam("文件上传1") MultipartFile file1,
                                              @ApiParam(value = "文件上传2", required = true) MultipartFile file2,
                                              @ApiParam(value = "创建时间", datePattern = "yyyy-MM-dd") Date create,
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
        return ResponseEntity.ok(DemoRes.testData());
    }

    @ApiMethod(value = "响应 List", develop = Develop.PRODUCT, index = 2)
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功"),
            @ApiResponse(code = 500, msg = "xxx 错误", type = {
                    @ApiReturnType(value = JsonResult.class, genericParent = PageInfo.class, generic = DemoRes.class)
            })
    })
    @GetMapping("/demo-list")
    public ResponseEntity<List<DemoRes>> demoList(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(500).build();
        } else {
            return ResponseEntity.ok(DemoRes.testListData());
        }
    }

    @ApiMethod(value = "响应 Map", develop = Develop.PRODUCT, index = 3)
    @ApiResponses({
            @ApiResponse(code = 200, msg = "正常返回数据"),
            @ApiResponse(code = 403, msg = "当 name 传入 xyz 时返回, 表示没有权限(导去登录页面)")
    })
    @GetMapping("/demo-map")
    public ResponseEntity<Map<String, DemoRes>> demoMap(@ApiParam(value = "商品名", textarea = true) String name,
                                                        Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(403).build();
        } else {
            return ResponseEntity.ok(DemoRes.testMapData());
        }
    }


    @ApiMethod(value = "自定义返回类型 1", develop = Develop.PRODUCT, index = 4, returnType = {
            @ApiReturnType(value = JsonResult.class, genericParent = Map.class, generic = {String.class, DemoRes.class })
    })
    @GetMapping("/demo-custom1")
    public Object customReturn1(@ApiParam(value = "名", textarea = true) String name, Page page) {
        return new HashMap<>();
    }

    @ApiMethod(value = "自定义返回类型 2", develop = Develop.PRODUCT, index = 5, returnType = {
            @ApiReturnType(value = JsonResult.class, genericParent = PageInfo.class, generic = List.class, genericChild = DemoRes.class)
    })
    @PostMapping("/demo-custom2")
    public ResponseEntity customReturn2(@ApiParam(value = "品名", textarea = true) String name, Page page) {
        return ResponseEntity.ok(null);
    }


    @ApiMethod(value = "递归示例 1", develop = Develop.PRODUCT, index = 6)
    @GetMapping("/demo-recursive1")
    public JsonResult<RsVo> recursive1() {
        return JsonResult.success("r1", RsVo.testData());
    }
    @ApiMethod(value = "递归示例 2", develop = Develop.PRODUCT, index = 7)
    @GetMapping("/demo-recursive2")
    public JsonResult<R0Res> recursive2(@ApiParam(value = "商品名", textarea = true) String name) {
        return JsonResult.success("r2", R0Res.testData());
    }
    @ApiMethod(value = "递归示例 3", develop = Develop.PRODUCT, index = 8)
    @GetMapping("/demo-recursive3")
    public ResponseEntity<R1Res> recursive3(@ApiParam(value = "商品名", textarea = true) String name, Page page) {
        return ResponseEntity.ok(R1Res.testData());
    }


    // 下面的返回结果无法被解析

    @ApiMethod(value = "响应无法被解析 1", develop = Develop.PRODUCT)
    @GetMapping("/demo-error1")
    public ResponseEntity demoError1(@ApiParam(value = "创建时间", datePattern = "yyyy-MM-dd") Date create, Page page) {
        return ResponseEntity.ok("ok");
    }

    @ApiMethod(value = "响应无法被解析 2", develop = Develop.PRODUCT)
    @GetMapping("/demo-error2")
    public ResponseEntity<Object> demoError2(
            @ApiParam(value = "日期", dataType = "date", datePattern = "HH:mm:ss") String time,
            Page page) {
        return ResponseEntity.ok(new HashMap<>());
    }
}
