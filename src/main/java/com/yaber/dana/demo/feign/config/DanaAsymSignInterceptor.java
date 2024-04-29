package com.yaber.dana.demo.feign.config;

import com.yaber.dana.demo.sample.AsymSignUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Dana非对称加密拦截器
 * @author tangyabo
 * @date 2024/4/28
 */
public class DanaAsymSignInterceptor implements RequestInterceptor {

    private final DateTimeFormatter DATE_TIME_FORMATTER =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");

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
        String timestamp = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Asia/Jakarta")).format(DATE_TIME_FORMATTER);
        String xSignature = AsymSignUtils.generateAsymmetricSignature(payload,httpMethod,url,timestamp,danaConfig.getPrivateKey());

    }



}
