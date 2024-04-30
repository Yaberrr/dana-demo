package com.yaber.dana.demo.feign.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * dana订单信息 订单和支付一对一关系
 * @author tangyabo
 * @date 2024/4/30
 */
@Data
@AllArgsConstructor
public class DanaOrder {

    //订单标题
    private String orderTitle;


}
