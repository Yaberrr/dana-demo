package com.yaber.dana.demo;

import com.yaber.dana.demo.feign.api.DanaApi;
import com.yaber.dana.demo.feign.config.DanaConfig;
import com.yaber.dana.demo.feign.model.DanaAdditionalInfo;
import com.yaber.dana.demo.feign.model.DanaEnvInfo;
import com.yaber.dana.demo.feign.model.DanaMoney;
import com.yaber.dana.demo.feign.model.DanaOrder;
import com.yaber.dana.demo.feign.vo.DanaPaymentVo;
import com.yaber.dana.demo.feign.resp.DanaResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DanaDemoApplicationTests {

    @Autowired
    private DanaApi danaApi;
    @Autowired
    private DanaConfig danaConfig;


    @Test
    void testDebitPayment() {
        DanaPaymentVo vo = new DanaPaymentVo();
        vo.setMerchantId(danaConfig.getMerchantId());
        vo.setAmount(new DanaMoney("5.00", "IDR"));
        vo.setPartnerReferenceNo("1");

        DanaAdditionalInfo additionalInfo = new DanaAdditionalInfo();
        additionalInfo.setOrder(new DanaOrder("testOrder"));
        additionalInfo.setProductCode("51051000100000000001");
        additionalInfo.setMcc("90006");
        additionalInfo.setEnvInfo(new DanaEnvInfo("APP","APP"));
        vo.setAdditionalInfo(additionalInfo);

        DanaResp danaResp = danaApi.debitPayment(vo);
        System.out.println(danaResp);
    }






}
