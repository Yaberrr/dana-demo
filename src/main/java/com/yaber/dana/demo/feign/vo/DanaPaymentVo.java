package com.yaber.dana.demo.feign.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yaber.dana.demo.feign.model.DanaAdditionalInfo;
import com.yaber.dana.demo.feign.model.DanaMoney;
import lombok.Data;

/**
 * 发起支付vo
 * @author tangyabo
 * @date 2024/4/29
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanaPaymentVo {

    //交易唯一id， 重试支付需用相同id
    private String partnerReferenceNo;

    //平台id
    private String merchantId;

    //支付金额
    private DanaMoney amount;

    //附加信息
    private DanaAdditionalInfo additionalInfo;


    //------------------可选-----------------

    //支付过期时间
    private String validUpTo;

    //商铺id
    private String externalStoreId;


    //禁用支付方式
    //private String disabledPayMethods;

    //指定支付方式
    //payOptionDetails



}
