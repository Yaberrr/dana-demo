package com.yaber.dana.demo.common.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaber.dana.demo.common.enums.ServiceCodeEnum;
import com.yaber.dana.demo.common.enums.TransactionStatusEnum;
import com.yaber.dana.demo.common.model.DanaAdditionalInfo;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.feign.config.DanaConfig;
import lombok.Data;

import java.util.Date;

/**
 * 查询支付
 * @author tangyabo
 * @date 2024/5/6
 */
@Data
public class DanaPaymentQueryResp {

    //交易唯一id
    private String originalPartnerReferenceNo;

    //dana系统中的唯一id
    private String originalReferenceNo;

    //交易类型
    private ServiceCodeEnum serviceCode;

    //最新交易状态
    private TransactionStatusEnum latestTransactionStatus;

    //状态描述
    private String transactionStatusDesc;

    //交易金额
    private DanaMoney amount;

    //订单标题
    private String title;

    //支付时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DanaConfig.RECEIVE_PATTERN, timezone = "GMT+7")
    private Date paidTime;


    private DanaAdditionalInfo additionalInfo;
}
