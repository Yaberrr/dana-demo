package com.yaber.dana.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * 过滤器中将request转为自定义包装类
 * @author tangyabo
 * @date 2024/5/7
 */
public class JsonFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //请求参数为JSON类型，则使用自定义包装
        if(request instanceof HttpServletRequest && "application/json".equals(((HttpServletRequest)request).getHeader("Content-Type"))
                && request.getContentLength() > 0) {
            chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
