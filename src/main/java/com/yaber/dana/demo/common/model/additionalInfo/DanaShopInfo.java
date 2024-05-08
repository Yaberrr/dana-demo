package com.yaber.dana.demo.common.model.additionalInfo;

import lombok.Data;

/**
 * 商铺信息
 * @author tangyabo
 * @date 2024/5/7
 */
@Data
public class DanaShopInfo {

    //商铺id
    private String shopId;

    //商铺名称
    private String shopName;

    private String externalShopId;

    private String operatorId;

    private String shopAddress;

    private String divisionId;

    private String externalDivisionId;

    private String divisionType;


}
