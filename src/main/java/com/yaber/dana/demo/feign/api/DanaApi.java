package com.yaber.dana.demo.feign.api;

import com.yaber.dana.demo.feign.config.DanaConfiguration;
import com.yaber.dana.demo.feign.vo.DanaPaymentVo;
import com.yaber.dana.demo.feign.resp.DanaResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Dana api
 * @author tangyabo
 * @date 2024/4/28
 */
@FeignClient(name="DanaApi", url = "${dana.url}",configuration = DanaConfiguration.class)
public interface DanaApi {

    /**
     * 发起支付
     * @param paymentVo
     * @return
     */
    @PostMapping(value = "/v1.0/debit/payment.htm")
    DanaResp debitPayment(DanaPaymentVo paymentVo);


}
