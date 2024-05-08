package com.yaber.dana.demo.common.model.additionalInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangyabo
 * @date 2024/5/8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class DanaUrlParam {

    public DanaUrlParam(String type, String url) {
        this.type = type;
        this.url = url;
    }

    //NOTIFICATION(回调通知) or PAY_RETURN(跳转页面)
    private String type;

    private String url;

    private String isDeeplink;



}
