package com.yaber.dana.demo.common.model.additionalInfo;


import com.yaber.dana.demo.common.enums.PaymentMethodEnum;
import com.yaber.dana.demo.common.model.DanaMoney;
import lombok.Data;

/**
 * 支付类型
 * @author tangyabo
 * @date 2024/5/7
 */
@Data
public class DanaPayOption {

    //支付方式
    private PaymentMethodEnum payMethod;

    //支付金额
    private DanaMoney payAmount;

    private DanaMoney transAmount;

    private DanaMoney chargeAmount;

    private String payOptionBillExtendInfo;

    private String extendInfo;
}
