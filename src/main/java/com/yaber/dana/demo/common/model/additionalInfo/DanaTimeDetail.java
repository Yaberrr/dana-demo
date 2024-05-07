package com.yaber.dana.demo.common.model.additionalInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaber.dana.demo.feign.config.DanaConfig;
import lombok.Data;

import java.util.Date;

/**
 * 时间详情
 * @author tangyabo
 * @date 2024/5/6
 */
@Data
public class DanaTimeDetail {

    //过期时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DanaConfig.RECEIVE_PATTERN, timezone = "GMT+7")
    private Date expiryTime;

    //创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DanaConfig.RECEIVE_PATTERN, timezone = "GMT+7")
    private Date createdTime;

}
