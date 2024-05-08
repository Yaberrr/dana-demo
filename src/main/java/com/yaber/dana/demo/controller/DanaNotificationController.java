package com.yaber.dana.demo.controller;


import com.yaber.dana.demo.common.resp.DanaResp;
import com.yaber.dana.demo.common.vo.DanaPaymentNotifyVo;
import com.yaber.dana.demo.feign.config.DanaConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * dana回调处理
 * @author tangyabo
 * @date 2024/4/29
 */
@Controller
@RequestMapping("/danaNotification")
public class DanaNotificationController {
    private static final Logger log = LoggerFactory.getLogger(DanaConfiguration.class);

    @PostMapping("/finishPayment")
    @ResponseBody
    public DanaResp finishPayment(@RequestBody DanaPaymentNotifyVo notifyVo){
        log.info("receive finishPayment notification:{}", notifyVo);

        return new DanaResp("2005600","receive finishPayment notification");
    }

    @PostMapping("/finishRefund")
    @ResponseBody
    public DanaResp finishRefund(@RequestBody Map<String,Object> map){
        log.info("receive finishRefund notification:{}", map);
        return new DanaResp("2005600","receive finishRefund notification");
    }

    @RequestMapping("/redirect/{orderId}")
    public String redirect(@PathVariable String orderId){
        log.info("ready to redirect:{}", orderId);

        return "redirect:http://117.50.182.170/payment-result.html";
    }

}
