package com.yaber.dana.demo.common.enums;

import lombok.Getter;

/**
 * 支付方式
 * @author tangyabo
 * @date 2024/5/7
 */
@Getter
public enum PaymentMethodEnum {
    BALANCE("Payment method with balance"),
    COUPON("Payment method with coupon"),
    NET_BANKING("Payment method with internet banking"),
    CREDIT_CARD("Payment method with credit card"),
    DEBIT_CARD("Payment method with debit card"),
    VIRTUAL_ACCOUNT("Payment method with virtual account"),
    OTC("Payment method with OTC"),
    DIRECT_DEBIT_CREDIT_CARD("Payment method with direct debit of credit card"),
    DIRECT_DEBIT_DEBIT_CARD("Payment method with direct debit of debit card"),
    ONLINE_CREDIT("Payment method with online credit"),
    LOAN_CREDIT("Payment method with DANA Cicil");

    private final String remark;

    PaymentMethodEnum(String remark) {
        this.remark = remark;
    }
}