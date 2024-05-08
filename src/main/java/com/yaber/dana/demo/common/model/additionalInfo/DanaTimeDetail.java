package com.yaber.dana.demo.common.model.additionalInfo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yaber.dana.demo.common.serializer.DanaZoneDeserializer;
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
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date expiryTime;

    //创建时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date createdTime;

}
