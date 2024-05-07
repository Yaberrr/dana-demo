package com.yaber.dana.demo.common.resp;

import lombok.Data;

/**
 * dana响应通用类
 * @author tangyabo
 * @date 2024/4/29
 */
@Data
public abstract class DanaResp {

    //响应码 2005400为成功
    private String responseCode;

    private String responseMessage;

}
