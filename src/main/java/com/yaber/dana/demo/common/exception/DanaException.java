package com.yaber.dana.demo.common.exception;

import com.yaber.dana.demo.common.resp.DanaResp;
import lombok.Getter;

/**
 * @author tangyabo
 * @date 2024/5/7
 */
@Getter
public class DanaException extends RuntimeException {

    private final DanaResp resp;

    public DanaException(DanaResp resp) {
        super(resp.getResponseMessage());
        this.resp = resp;
    }
}
