package com.yaber.dana.demo.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author tangyabo
 * @date 2024/5/6
 */
@Getter
public enum StatusDetailEnum {
    INIT("Order is created but not paid yet"),
    SUCCESS( "Order is succeeded"),
    CLOSED( "Order is closed"),
    PAYING( "Order is paid but not finished"),
    MERCHANT_ACCEPT("Order is accepted by merchant after order is paid for PAY-CONFIRM"),
    CANCELLED("Order is cancelled");

    private final String description;

    StatusDetailEnum(String description) {
        this.description = description;
    }

}
