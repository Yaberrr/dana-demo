package com.yaber.dana.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaber.dana.demo.common.resp.DanaResp;
import com.yaber.dana.demo.config.DanaConfig;
import com.yaber.dana.demo.feign.config.DanaConfiguration;
import com.yaber.dana.demo.utils.DanaAsymSignUtils;
import com.yaber.dana.demo.utils.DanaZoneConvertor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * dana非对称加密拦截器(验证签名)
 * @author tangyabo
 * @date 2024/5/6
 */
public class DanaVerifyInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(DanaConfiguration.class);

    private final DanaConfig danaConfig;

    private final ObjectMapper objectMapper;


    public DanaVerifyInterceptor(DanaConfig danaConfig) {
        this.danaConfig = danaConfig;
        this.objectMapper = new ObjectMapper();

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request instanceof RequestWrapper){
            //通过自定义包装类 获取请求体内容
            String payload = ((RequestWrapper)request).getRequestBody();
            String signature = request.getHeader("X-SIGNATURE");
            String timestamp = request.getHeader("X-TIMESTAMP");
            String httpMethod = request.getMethod();
            String url = request.getRequestURI();

           /* Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                log.info("{}: {}", headerName, headerValue);
            }*/
            log.info("\n--------------------------------------------receive request from dana--------------------------------------------");
            log.info("url:{}", url);
            log.info("timestamp:{}", timestamp);
            log.info("payload:{}", DanaAsymSignUtils.minifyJsonString(payload));

            //验证时间戳防止重放，和当前时间相差不可超过三分钟
            Date date = DanaZoneConvertor.parseWithZone(timestamp);
            if(Math.abs(Math.abs(new Date().getTime() - date.getTime())) > 3*60*1000){
                writeResponse(response, new DanaResp("5005601", "timestamp is invalid"));
                return false;
            }

            //通过DANA公钥验证签名
            boolean isVerified = DanaAsymSignUtils.verifyAsymmetricSignature(signature, payload, httpMethod, url, timestamp,
                    danaConfig.getDanaPublicKey());
            //签名验证不通过
            if(!isVerified){
                log.error("Failed to verify signature: {}", signature);
                writeResponse(response,  new DanaResp("5005601", "Failed to verify signature"));
                return false;
            }else{
                log.info("successfully verify signature");
            }
        }
        return true;
    }

    private void writeResponse(HttpServletResponse response, DanaResp resp) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            writer.write(objectMapper.writeValueAsString(resp));
            log.info("intercepted request, response message: {}",resp);
        } catch (IOException e) {
            log.error("failed to write response :{}", response);
            throw new RuntimeException(e);
        }
    }

}
