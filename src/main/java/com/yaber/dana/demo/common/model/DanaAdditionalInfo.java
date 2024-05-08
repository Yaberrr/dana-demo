package com.yaber.dana.demo.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yaber.dana.demo.common.model.additionalInfo.*;
import lombok.Data;

import java.util.List;

/**
 * Dana支付附加信息
 * @author tangyabo
 * @date 2024/4/30
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanaAdditionalInfo {

    /**
     * ------------发起支付必填--------------
     */

    //产品代码
    private String productCode;

    //商户所从事的产业代码
    private String mcc;

    //订单信息
    private DanaOrder order;

    //环境信息
    private DanaEnvInfo envInfo;

    /**
     * ------------查询返回-------------
     */

    //关闭原因
    private String closeReason;

    //时间详情
    private DanaTimeDetail timeDetail;

    //状态详情
    private DanaStatusDetail statusDetail;

    //用户信息
    private DanaBuyer buyer;

    //支付详情
    private List<DanaPaymentInfo> paymentViews;

    /**
     * -----------支付回调通知-----------
     */

    //支付详情
    private DanaPaymentInfo paymentInfo;

    //商铺信息
    private DanaShopInfo shopInfo;

    private String extendInfo;
}
