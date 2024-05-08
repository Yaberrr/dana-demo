package com.yaber.dana.demo.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yaber.dana.demo.common.model.DanaAdditionalInfo;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.common.model.additionalInfo.DanaUrlParam;
import com.yaber.dana.demo.common.serializer.DanaZoneSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

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

    //回调url
    private List<DanaUrlParam> urlParams;

    /**
     * ------------------可选-----------------
     */

    //支付过期时间
    @JsonSerialize(using = DanaZoneSerializer.class)
    private Date validUpTo;

    //商铺id
    private String externalStoreId;


    //禁用支付方式
    //private String disabledPayMethods;

    //指定支付方式
    //payOptionDetails



}
