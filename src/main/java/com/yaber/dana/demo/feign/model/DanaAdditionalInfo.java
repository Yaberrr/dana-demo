package com.yaber.dana.demo.feign.model;

import lombok.Data;

/**
 * Dana支付附加信息
 * @author tangyabo
 * @date 2024/4/30
 */
@Data
public class DanaAdditionalInfo {

    //产品代码
    private String productCode;

    //需填写订单标题
    private DanaOrder order;

    //商户所从事的产业代码
    private String mcc;

    //环境信息
    private DanaEnvInfo envInfo;


}
