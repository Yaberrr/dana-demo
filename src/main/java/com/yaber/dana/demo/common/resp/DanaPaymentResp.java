package com.yaber.dana.demo.common.resp;

import lombok.Data;

/**
 * 发起支付
 * @author tangyabo
 * @date 2024/5/6
 */
@Data
public class DanaPaymentResp extends DanaResp{

    //dana系统中的唯一id
    private String referenceNo;

    //交易唯一id， 重试支付需用相同id
    private String partnerReferenceNo;

    //dana支付跳转url
    private String webRedirectUrl;
}
