package com.yaber.dana.demo.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dana响应通用类
 * @author tangyabo
 * @date 2024/4/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanaResp {

    //响应码 2005400为成功
    private String responseCode;

    private String responseMessage;

}
