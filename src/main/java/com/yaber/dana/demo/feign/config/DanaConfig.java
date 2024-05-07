package com.yaber.dana.demo.feign.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

/**
 * dana配置信息
 * @author tangyabo
 * @date 2024/4/29
 */
@Component
@Data
@ConfigurationProperties("dana")
public class DanaConfig {
    //全局日期格式
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssxxx";

    //dana时区
    public static final ZoneId ZONE_ID = ZoneId.of("Asia/Jakarta");

    private String clientId;

    private String merchantId;


    private String privateKey;

    private String publicKey;

    private String danaPublicKey;
}
