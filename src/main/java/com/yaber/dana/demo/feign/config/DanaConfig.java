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

    private String clientId;

    private String merchantId;

    private String privateKey;

    private String publicKey;

    private String danaPublicKey;
}
