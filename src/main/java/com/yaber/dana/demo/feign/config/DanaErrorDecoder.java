package com.yaber.dana.demo.feign.config;

import com.yaber.dana.demo.common.exception.DanaException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import io.micrometer.common.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * dana api调用 自定义异常处理
 * @author tangyabo
 * @date 2024/5/7
 */
public class DanaErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if(Objects.nonNull(response.body())){
                //dana api异常则只打印dana返回的异常信息
                String responseBody = new String(Util.toByteArray(response.body().asInputStream()));
                if(StringUtils.isNotBlank(responseBody)) {
                    return new DanaException(responseBody);
                }
            }
        } catch (IOException e) {
            return new Exception("Error reading response body", e);
        }

        return new Exception(response.reason());
    }
}
