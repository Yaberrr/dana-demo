package com.yaber.dana.demo.feign.config;

import com.yaber.dana.demo.sample.AsymSignUtils;
import com.yaber.dana.demo.sample.DanaExternalIdGenerator;
import com.yaber.dana.demo.sample.IndonesiaTimeConvertor;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

/**
 * Dana非对称加密拦截器
 * @author tangyabo
 * @date 2024/4/28
 */
public class DanaAsymSignInterceptor implements RequestInterceptor {


    @Autowired
    private DanaConfig danaConfig;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String httpMethod = requestTemplate.method();
        String url= requestTemplate.url();
        String payload = "";
//        System.out.println(danaConfig.getPrivateKey());
        if(Objects.nonNull(requestTemplate.body())) {
            payload = new String(requestTemplate.body());
        }
        String timestamp = IndonesiaTimeConvertor.convertZone(new Date());
        String xSignature = AsymSignUtils.generateAsymmetricSignature(payload,httpMethod,url,timestamp,danaConfig.getPrivateKey());
        requestTemplate.header("X-SIGNATURE", xSignature);
        requestTemplate.header("X-TIMESTAMP", timestamp);
        requestTemplate.header("X-PARTNER-ID", danaConfig.getClientId());
        requestTemplate.header("X-EXTERNAL-ID", DanaExternalIdGenerator.generateId());
        requestTemplate.header("CHANNEL-ID", "1");
        requestTemplate.header("Content-Type", "application/json");
    }



}
