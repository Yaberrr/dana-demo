package com.yaber.dana.demo.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yaber.dana.demo.common.enums.TransactionStatusEnum;
import com.yaber.dana.demo.common.model.DanaAdditionalInfo;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.common.serializer.DanaZoneDeserializer;
import lombok.Data;

import java.util.Date;

/**
 * 回调通知vo
 * @author tangyabo
 * @date 2024/5/7
 */
@Data
public class DanaPaymentNotifyVo {

    //交易唯一id
    private String originalPartnerReferenceNo;

    //dana系统中的唯一id
    private String originalReferenceNo;

    //提交支付时请求头中的externalId
    private String originalExternalId;

    //商户id
    private String merchantId;

    //交易金额
    private DanaMoney amount;

    //最新交易状态
    private TransactionStatusEnum latestTransactionStatus;

    //状态描述
    private String transactionStatusDesc;

    //支付创建时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date createdTime;

    //支付完成时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date finishedTime;

    private DanaAdditionalInfo additionalInfo;

}
