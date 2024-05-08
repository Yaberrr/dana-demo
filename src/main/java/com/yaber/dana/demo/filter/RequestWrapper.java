package com.yaber.dana.demo.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 自定义包装类 解决请求流只能读取一次的问题
 * @author tangyabo
 * @date 2024/5/7
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private final byte[] requestBody;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody =  StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            public boolean isFinished() {
                return false;
            }
            public boolean isReady() {
                return false;
            }
            public void setReadListener(ReadListener readListener) {}
            public int read() {
                return byteArrayInputStream.read();
            }
        };

    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }


    public String getRequestBody() {
        return new String(this.requestBody);
    }
}