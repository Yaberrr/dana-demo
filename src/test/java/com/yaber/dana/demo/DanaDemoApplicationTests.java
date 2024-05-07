package com.yaber.dana.demo;

import com.yaber.dana.demo.common.enums.ServiceCodeEnum;
import com.yaber.dana.demo.feign.api.DanaApi;
import com.yaber.dana.demo.feign.config.DanaConfig;
import com.yaber.dana.demo.common.model.DanaAdditionalInfo;
import com.yaber.dana.demo.common.model.additionalInfo.DanaEnvInfo;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.common.model.DanaOrder;
import com.yaber.dana.demo.common.resp.DanaPaymentResp;
import com.yaber.dana.demo.common.vo.DanaPaymentQueryVo;
import com.yaber.dana.demo.common.vo.DanaPaymentVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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
        vo.setAmount(new DanaMoney("30.00", "IDR"));
        vo.setPartnerReferenceNo("7");
        DanaAdditionalInfo additionalInfo = new DanaAdditionalInfo();
        additionalInfo.setOrder(new DanaOrder("testOrder"));
        additionalInfo.setProductCode("51051000100000000001");
        additionalInfo.setMcc("90006");
        additionalInfo.setEnvInfo(new DanaEnvInfo("APP","APP"));
        vo.setAdditionalInfo(additionalInfo);

        DanaPaymentResp resp = danaApi.debitPayment(vo);
        System.out.println(resp);
    }

    @Test
    void testQueryPayment(){
        DanaPaymentQueryVo queryVo = new DanaPaymentQueryVo();
        queryVo.setOriginalPartnerReferenceNo("7");
        queryVo.setOriginalReferenceNo("20240506111212800110166239001601412");
        queryVo.setMerchantId(danaConfig.getMerchantId());
        queryVo.setServiceCode(ServiceCodeEnum.PAYMENT_GATEWAY);

        System.out.println(danaApi.queryPayment(queryVo));
    }




}
