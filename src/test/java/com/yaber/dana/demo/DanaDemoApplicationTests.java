package com.yaber.dana.demo;

import com.yaber.dana.demo.common.enums.ServiceCodeEnum;
import com.yaber.dana.demo.common.model.DanaAdditionalInfo;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.common.model.DanaOrder;
import com.yaber.dana.demo.common.model.additionalInfo.DanaEnvInfo;
import com.yaber.dana.demo.common.model.additionalInfo.DanaUrlParam;
import com.yaber.dana.demo.common.resp.DanaPaymentResp;
import com.yaber.dana.demo.common.vo.DanaPaymentCancelVo;
import com.yaber.dana.demo.common.vo.DanaPaymentQueryVo;
import com.yaber.dana.demo.common.vo.DanaPaymentVo;
import com.yaber.dana.demo.config.DanaConfig;
import com.yaber.dana.demo.feign.api.DanaApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        vo.setAmount(new DanaMoney(new BigDecimal("458000.00"), "IDR"));
        vo.setPartnerReferenceNo("123");
        vo.setValidUpTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-05-14 10:13:02"));
        DanaAdditionalInfo additionalInfo = new DanaAdditionalInfo();
        additionalInfo.setOrder(new DanaOrder("GM240513140332634771"));
        additionalInfo.setProductCode("51051000100000000001");
        additionalInfo.setMcc("90006");
        additionalInfo.setEnvInfo(new DanaEnvInfo("APP","APP"));
        vo.setAdditionalInfo(additionalInfo);

        List<DanaUrlParam> urlParams = new ArrayList<>();
        urlParams.add(new DanaUrlParam("PAY_RETURN",danaConfig.getPayReturn() + "/" +  vo.getPartnerReferenceNo() + "/" +"222", "N"));
        urlParams.add(new DanaUrlParam("NOTIFICATION",danaConfig.getNotification()));
        vo.setUrlParams(urlParams);

        DanaPaymentResp resp = danaApi.debitPayment(vo);


        System.out.println(resp);
    }

    @Test
    void testQueryPayment(){
        DanaPaymentQueryVo queryVo = new DanaPaymentQueryVo();
        queryVo.setOriginalPartnerReferenceNo("GM240511102506535634");
//        queryVo.setOriginalReferenceNo("GM240511091402875312");
        queryVo.setMerchantId(danaConfig.getMerchantId());
        queryVo.setServiceCode(ServiceCodeEnum.PAYMENT_GATEWAY);
       /* DanaPaymentQueryResp resp = null;
        try {
            resp = danaApi.queryPayment(queryVo);
            System.out.println(resp.isSuccessful());
        }catch(DanaException e){
            System.out.println(e.getResp());
        }*/

        System.out.println(danaApi.queryPayment(queryVo));
    }

    @Test
    void testCancelPayment(){
        DanaPaymentCancelVo cancelVo = new DanaPaymentCancelVo();
        cancelVo.setOriginalPartnerReferenceNo("GM240511102506535634");
        cancelVo.setMerchantId(danaConfig.getMerchantId());
        System.out.println(danaApi.cancelPayment(cancelVo));
    }


}
