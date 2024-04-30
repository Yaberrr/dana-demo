package com.yaber.dana.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tangyabo
 * @date 2024/4/29
 */
@Service
public class DanaPayService implements PayService{
    @Override
    public Map<String, Object> pay(Map params) {




        return Map.of();
    }

    @Override
    public Map<String, Object> refund(Map params) {
        return Map.of();
    }

    @Override
    public Map<String, Object> payBack(Map params) {
        return Map.of();
    }

    @Override
    public Map<String, Object> getPayConfig(Map params) {
        return Map.of();
    }

    @Override
    public ResponseEntity<String> handleNotification(Map<String, Object> response) {
        return null;
    }
}
