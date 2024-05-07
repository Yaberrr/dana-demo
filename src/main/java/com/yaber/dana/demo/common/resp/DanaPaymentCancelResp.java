package com.yaber.dana.demo.common.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yaber.dana.demo.common.serializer.DanaZoneDeserializer;
import com.yaber.dana.demo.feign.config.DanaConfig;
import lombok.Data;

import java.util.Date;

/**
 * @author tangyabo
 * @date 2024/5/7
 */
@Data
public class DanaPaymentCancelResp extends DanaResp{

    //交易唯一id
    private String originalPartnerReferenceNo;

    //dana系统中的唯一id
    private String originalReferenceNo;

    //提交支付时请求头中的externalId
    private String originalExternalId;

    //交易时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date transactionDate;

    //取消时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date cancelTime;


}
