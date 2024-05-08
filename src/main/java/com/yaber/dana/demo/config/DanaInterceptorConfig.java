package com.yaber.dana.demo.config;


import com.yaber.dana.demo.filter.DanaVerifyInterceptor;
import com.yaber.dana.demo.filter.JsonFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 指定dana url配置过滤器及拦截器
 * @author tangyabo
 * @date 2024/5/7
 */
@Configuration
public class DanaInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private DanaConfig danaConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截的 URL 地址
        registry.addInterceptor(new DanaVerifyInterceptor(danaConfig))
                .addPathPatterns("/danaNotification/**")
                .excludePathPatterns("/danaNotification/redirect");
    }

    @Bean
    public FilterRegistrationBean<Filter> addFilters() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        // 配置过滤的 URL 地址
        registrationBean.setFilter(new JsonFilter());
        registrationBean.addUrlPatterns("/danaNotification/*");
        registrationBean.addInitParameter("exclusions", "/danaNotification/redirect");
        return registrationBean;
    }
}
