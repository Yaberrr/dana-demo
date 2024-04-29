package com.yaber.dana.demo.feign.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;

/**
 * Dana feignClient配置类
 * @author tangyabo
 * @date 2024/4/28
 */
public class DanaConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new DanaAsymSignInterceptor();
    }

    //设置日志级别
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    //指定日志生成器
    @Bean
    public Slf4jLogger feignLogger() {
        return new Slf4jLogger(DanaConfiguration.class);
    }
}
