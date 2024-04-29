package com.yaber.dana.demo.feign.api;

import com.yaber.dana.demo.feign.config.DanaConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dana api
 * @author tangyabo
 * @date 2024/4/28
 */
@FeignClient(name="DanaApi", url = "${dana.url}",configuration = DanaConfiguration.class)
public interface DanaApi {

    @GetMapping("/")
    void getBaidu();

}
