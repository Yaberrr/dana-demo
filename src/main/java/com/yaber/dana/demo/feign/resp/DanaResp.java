package com.yaber.dana.demo.feign.resp;

import lombok.Data;

/**
 * dana响应通用类
 * @author tangyabo
 * @date 2024/4/29
 */
@Data
public class DanaResp {

    //响应码 2005400为成功
    private String responseCode;

    private String responseMessage;

    //dana系统中的唯一id
    private String referenceNo;

    //交易唯一id， 重试支付需用相同id
    private String partnerReferenceNo;

    //dana支付跳转url
    private String webRedirectUrl;
}
