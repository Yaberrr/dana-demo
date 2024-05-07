package com.yaber.dana.demo.common.vo;

import com.yaber.dana.demo.common.model.DanaMoney;
import lombok.Data;

/**
 * 取消支付vo
 * @author tangyabo
 * @date 2024/5/7
 */
@Data
public class DanaPaymentCancelVo {

    //交易唯一id
    private String originalPartnerReferenceNo;

    //商户id
    private String merchantId;

    /**
     * ------------------可选-----------------
     */

    //dana唯一id
    private String originalReferenceNo;

    //取消原因
    private String reason;

    //提交支付时请求头中的externalId
    private String originalExternalId;

    //金额
    private DanaMoney amount;

}
