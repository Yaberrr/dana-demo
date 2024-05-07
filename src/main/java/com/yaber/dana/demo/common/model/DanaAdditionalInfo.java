package com.yaber.dana.demo.common.model;

import com.yaber.dana.demo.common.model.additionalInfo.DanaBuyer;
import com.yaber.dana.demo.common.model.additionalInfo.DanaEnvInfo;
import com.yaber.dana.demo.common.model.additionalInfo.DanaStatusDetail;
import com.yaber.dana.demo.common.model.additionalInfo.DanaTimeDetail;
import lombok.Data;

/**
 * Dana支付附加信息
 * @author tangyabo
 * @date 2024/4/30
 */
@Data
public class DanaAdditionalInfo {

    //产品代码
    private String productCode;

    //需填写订单标题
    private DanaOrder order;

    //商户所从事的产业代码
    private String mcc;

    //关闭原因
    private String closeReason;

    //环境信息
    private DanaEnvInfo envInfo;

    //时间详情
    private DanaTimeDetail timeDetail;

    //状态详情
    private DanaStatusDetail statusDetail;

    //用户信息
    private DanaBuyer buyer;
}
