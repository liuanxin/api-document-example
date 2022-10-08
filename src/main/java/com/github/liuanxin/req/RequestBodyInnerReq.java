package com.github.liuanxin.req;

import com.github.liuanxin.api.annotation.ApiParam;
import com.github.liuanxin.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RequestBodyInnerReq {

    @ApiParam("用户 id")
    private Long id;

    @ApiParam(value = "性别", dataType = "int", style = "color:green")
    private Gender gender;

    @ApiParam("地址")
    private AddressReq address;

    @ApiParam("订单")
    private List<OrderReq> orderList;

    @ApiParam("其他")
    private Map<String, OtherReq> otherMap;

    @Getter
    @Setter
    public static class AddressReq {

        @ApiParam(value = "地址 id", required = true)
        private Long id;

        @ApiParam("详细地址")
        private String address;
    }

    @Getter
    @Setter
    public static class OrderReq {

        @ApiParam(value = "订单 id", required = true)
        private Long id;

        @ApiParam("总价格")
        private BigDecimal totalAmount;

        @ApiParam("订单项")
        private List<OrderItemReq> itemList;
    }

    @Getter
    @Setter
    public static class OrderItemReq {

        @ApiParam(value = "订单项 id", required = true)
        private Long id;

        @ApiParam("商品名称")
        private String productName;

        @ApiParam("商品价格")
        private BigDecimal price;

        @ApiParam("商品数量")
        private Integer number;
    }

    @Getter
    @Setter
    public static class OtherReq {

        @ApiParam(value = "其他信息 id", required = true)
        private Long id;

        @ApiParam("其他信息")
        private String other;
    }
}
