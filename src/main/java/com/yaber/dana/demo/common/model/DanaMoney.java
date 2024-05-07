package com.yaber.dana.demo.common.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yaber.dana.demo.common.serializer.DanaDecimalSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 金额
 * @author tangyabo
 * @date 2024/4/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanaMoney {

    //金额  ISO-4217 IDR货币值应两位小数
    @JsonSerialize(using = DanaDecimalSerializer.class)
    private BigDecimal value;

    //货币
    private String currency;
}
