package com.yaber.dana.demo.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 交易类型
 * @author tangyabo
 * @date 2024/5/6
 */
@Getter
public enum ServiceCodeEnum {
    IPG_CASHIER_PAY_SNAP("54", "IPG Cashier Pay - SNAP"),
    QRIS_CPM_ACQUIRER_SNAP("60", "QRIS CPM (Acquirer) - SNAP"),
    QRIS_MPM_ACQUIRER_SNAP("47", "QRIS MPM (Acquirer) - SNAP"),
    PAYMENT_GATEWAY("00", "Payment Gateway");

    private final String code;
    private final String description;

    ServiceCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonCreator
    public static ServiceCodeEnum fromCode(String code) {
        for (ServiceCodeEnum type : ServiceCodeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    @JsonValue
    public String getCodeValue() {
        return code;
    }
}

