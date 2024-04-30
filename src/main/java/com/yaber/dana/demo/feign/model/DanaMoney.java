package com.yaber.dana.demo.feign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String value;

    //货币
    private String currency;
}
