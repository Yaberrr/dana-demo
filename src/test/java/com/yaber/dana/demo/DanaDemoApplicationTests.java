package com.yaber.dana.demo;

import com.yaber.dana.demo.common.enums.ServiceCodeEnum;
import com.yaber.dana.demo.common.model.DanaAdditionalInfo;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.common.model.DanaOrder;
import com.yaber.dana.demo.common.model.additionalInfo.DanaEnvInfo;
import com.yaber.dana.demo.common.resp.DanaPaymentResp;
import com.yaber.dana.demo.common.vo.DanaPaymentQueryVo;
import com.yaber.dana.demo.common.vo.DanaPaymentVo;
import com.yaber.dana.demo.feign.api.DanaApi;
import com.yaber.dana.demo.feign.config.DanaConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Slf4j
class DanaDemoApplicationTests {

    @Autowired
    private DanaApi danaApi;
    @Autowired
    private DanaConfig danaConfig;


    @Test
    void testDebitPayment() throws ParseException {
        DanaPaymentVo vo = new DanaPaymentVo();
        vo.setMerchantId(danaConfig.getMerchantId());
        vo.setAmount(new DanaMoney(new BigDecimal("30.00"), "IDR"));
        vo.setPartnerReferenceNo("8");
        vo.setValidUpTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-05-07 10:21:00"));
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
        queryVo.setOriginalPartnerReferenceNo("8");
        queryVo.setOriginalReferenceNo("20240506111212800110166239001601412");
        queryVo.setMerchantId(danaConfig.getMerchantId());
        queryVo.setServiceCode(ServiceCodeEnum.PAYMENT_GATEWAY);
        System.out.println(danaApi.queryPayment(queryVo));
    }




}
