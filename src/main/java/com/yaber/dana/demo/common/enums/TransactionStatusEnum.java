package com.yaber.dana.demo.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 交易状态
 * @author tangyabo
 * @date 2024/5/6
 */
@Getter
public enum TransactionStatusEnum {
    SUCCESS("00", "The order has been successfully in final state and paid"),
    INIT("01", "The order has been created, but has not been paid"),
    PAYING("02", "The order is in process, not in final state, payment is success"),
    CANCELLED("05", "The order has been closed"),
    NOT_FOUND("07", "The order is not found");

    private final String code;
    private final String description;

    TransactionStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonCreator
    public static TransactionStatusEnum fromCode(String code) {
        for (TransactionStatusEnum status : TransactionStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getCodeValue() {
        return code;
    }
}
