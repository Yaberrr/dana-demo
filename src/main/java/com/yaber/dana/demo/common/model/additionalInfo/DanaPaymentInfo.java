package com.yaber.dana.demo.common.model.additionalInfo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yaber.dana.demo.common.serializer.DanaZoneDeserializer;
import com.yaber.dana.demo.common.serializer.DanaZoneSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 支付详情信息
 * @author tangyabo
 * @date 2024/5/7
 */
@Data
public class DanaPaymentInfo {

    private String cashierRequestId;

    //支付时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    @JsonSerialize(using = DanaZoneSerializer.class)
    private Date paidTime;

    //支付类型
    private List<DanaPayOption> payOptionInfos;

    private String payRequestExtendInfo;

    private String extendInfo;

}
