package com.yaber.dana.demo.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaber.dana.demo.common.exception.DanaException;
import com.yaber.dana.demo.common.resp.DanaResp;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import io.micrometer.common.util.StringUtils;

import java.util.Objects;

/**
 * dana api调用 自定义异常处理
 * @author tangyabo
 * @date 2024/5/7
 */
public class DanaErrorDecoder implements ErrorDecoder {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if(Objects.nonNull(response.body())){
                //dana api异常则只打印dana返回的异常信息
                String responseBody = new String(Util.toByteArray(response.body().asInputStream()));
                if(StringUtils.isNotBlank(responseBody)) {
                    return new DanaException(objectMapper.readValue(responseBody, DanaResp.class));
                }
            }
        } catch (Exception e) {
            return new Exception("Error reading response body", e);
        }

        return new Exception(response.reason());
    }
}
