package com.yaber.dana.demo.feign.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssxxx";

    public static final String RECEIVE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    private String clientId;

    private String merchantId;

    private String privateKey;

    private String publicKey;

    private String danaPublicKey;
}
