package com.yaber.dana.demo.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yaber.dana.demo.common.enums.ServiceCodeEnum;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.common.serializer.DanaZoneSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 查询支付vo
 * @author tangyabo
 * @date 2024/4/30
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanaPaymentQueryVo {

    //交易唯一id
    private String originalPartnerReferenceNo;

    //商户id
    private String merchantId;

    //交易类型
    private ServiceCodeEnum serviceCode;

    /**
     * ------------------可选-----------------
     */

    //dana唯一id
    private String originalReferenceNo;

    //提交支付时请求头中的externalId
    private String originalExternalId;

    //金额
    private DanaMoney amount;

    //交易时间
    @JsonSerialize(using = DanaZoneSerializer.class)
    private Date transactionDate;
}
