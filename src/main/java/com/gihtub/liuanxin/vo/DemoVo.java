package com.gihtub.liuanxin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.gihtub.liuanxin.enums.UserType;
import com.gihtub.liuanxin.util.PageInfo;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoVo {

    @ApiReturn(value = "用户 id", example = "123")
    private Long id;

    @ApiReturn(value = "用户名称", example = "张三")
    private String name;

    // 文档收集 会将 enum 的 getCode: getValue 信息(没有则拼接 name)附加进说明
    @ApiReturn(value = "性别", type = "int")
    private Gender gender;

    @ApiReturn(value = "商品类型", example = "discount")
    private ProductType[] productTypes;

    @ApiReturn(value = "用户类型", example = "vip")
    private List<UserType> userTypes;

    @ApiReturn("一个 List 示例")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DemoOneVo> ones;

    @ApiReturn("一个 Map 示例")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<Integer, DemoTwoVo> twos;

    @ApiReturn("一个 Map")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, List<DemoThreeVo>> threes;

    @ApiReturn(value = "创建时间", example = "2018-01-01 12:13:14")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiReturn(value = "更新时间", example = "2019-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoOneVo {
        @ApiReturn(value = "一 id", example = "111")
        private Long oneId;

        @ApiReturn(value = "一字符", example = "abc")
        private String one;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoTwoVo {
        @ApiReturn(value = "二 id", example = "222")
        private Long twoId;

        @ApiReturn(value = "二数组", example = "一二三")
        private String[] twoArray;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoThreeVo {
        @ApiReturn(value = "三 id", example = "222")
        private Long threeId;

        @ApiReturn(value = "三列表", example = "三二一")
        private List<String> threeList;
    }


    public static DemoVo testData() {
        return new DemoVo(
                123L, "张三", Gender.Male,
                new ProductType[] { ProductType.Normal, ProductType.Discount },
                Arrays.asList(UserType.Normal, UserType.Vip),
                Arrays.asList(new DemoOneVo(13579L, "一"), new DemoOneVo(24680L, "二")),
                new HashMap<Integer, DemoTwoVo>() {{
                    put(111, new DemoTwoVo(147L, new String[] { "四", "七" }));
                    put(444, new DemoTwoVo(369L, new String[] { "六", "九" }));
                }},
                new HashMap<String, List<DemoThreeVo>>() {{
                    put("name", Arrays.asList(
                            new DemoThreeVo(159L, Arrays.asList("五", "九")),
                            new DemoThreeVo(357L, Arrays.asList("三", "七"))
                    ));
                }},
                new Date(),
                new Date()
        );
    }
    public static List<DemoVo> testListData() {
        return Arrays.asList(testData(), new DemoVo(
                321L, "李四", Gender.Female,
                new ProductType[] { ProductType.Discount },
                Arrays.asList(UserType.Normal, UserType.Vip),
                Arrays.asList(new DemoOneVo(97531L, "〇"), new DemoOneVo(86420L, "拾")),
                new HashMap<Integer, DemoTwoVo>() {{
                    put(222, new DemoTwoVo(741L, new String[] { "七", "四" }));
                    put(333, new DemoTwoVo(963L, new String[] { "九", "六" }));
                }},
                new HashMap<String, List<DemoThreeVo>>() {{
                    put("id", Arrays.asList(
                            new DemoThreeVo(951L, Arrays.asList("九", "五")),
                            new DemoThreeVo(753L, Arrays.asList("七", "三"))
                    ));
                }},
                new Date(),
                new Date()
        ));
    }
    public static PageInfo<DemoVo> testPageData() {
        return new PageInfo<>(100, testListData());
    }
    public static Map<String, DemoVo> testMapData() {
        return new HashMap<String, DemoVo>() {{ put("some", testData()); }};
    }
}
