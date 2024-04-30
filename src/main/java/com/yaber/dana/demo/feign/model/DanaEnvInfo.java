package com.yaber.dana.demo.feign.model;

import lombok.Data;

/**
 * 环境信息
 * @author tangyabo
 * @date 2024/4/30
 */
@Data
public class DanaEnvInfo {

    //源平台  IPG
    private String sourcePlatForm = "IPG";

    public DanaEnvInfo(String orderTerminalType, String terminalType) {
        this.orderTerminalType = orderTerminalType;
        this.terminalType = terminalType;
    }

    //订单终端类型  APP/WEB/WAP/SYSTEM
    private String orderTerminalType;

    //终端类型   APP/WEB/WAP/SYSTEM
    private String terminalType;
}
