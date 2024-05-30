package com.yaber.dana.demo.common.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dana响应通用类
 * @author tangyabo
 * @date 2024/4/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DanaResp {

    //不同接口响应码不一样，定义在各个resp中
    private String responseCode;

    private String responseMessage;


}

