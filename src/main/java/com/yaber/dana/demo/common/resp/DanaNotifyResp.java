package com.yaber.dana.demo.common.resp;

/**
 * 通知响应
 * @author tangyabo
 * @date 2024/5/9
 */
public interface DanaNotifyResp {

    DanaResp SUCCESS = new DanaResp("2005600","Successful");
    DanaResp FAIL = new DanaResp("5005601","Internal Server Error");

}
