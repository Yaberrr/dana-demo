package com.yaber.dana.demo.feign.api;

import com.yaber.dana.demo.common.resp.DanaPaymentQueryResp;
import com.yaber.dana.demo.feign.config.DanaConfiguration;
import com.yaber.dana.demo.common.resp.DanaPaymentResp;
import com.yaber.dana.demo.common.vo.DanaPaymentQueryVo;
import com.yaber.dana.demo.common.vo.DanaPaymentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * Dana api
 * @author tangyabo
 * @date 2024/4/28
 */
@FeignClient(name="danaApi", url = "${dana.url}",configuration = DanaConfiguration.class)
public interface DanaApi {

    /**
     * 发起支付
     */
    @PostMapping(value = "/v1.0/debit/payment.htm")
    DanaPaymentResp debitPayment(DanaPaymentVo paymentVo);

    /**
     * 查询支付状态
     */
    @PostMapping(value = "/v1.0/debit/status.htm")
    DanaPaymentQueryResp queryPayment(DanaPaymentQueryVo queryVo);
}
