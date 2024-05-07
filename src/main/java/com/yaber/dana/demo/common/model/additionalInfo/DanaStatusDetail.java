package com.yaber.dana.demo.common.model.additionalInfo;

import com.yaber.dana.demo.common.enums.StatusDetailEnum;
import lombok.Data;

/**
 * 状态详情
 * @author tangyabo
 * @date 2024/5/6
 */
@Data
public class DanaStatusDetail {

    //是否冻结
    private boolean frozen;

    //是否取消
    private boolean cancelled;

    //状态
    private StatusDetailEnum acquirementStatus;
}
