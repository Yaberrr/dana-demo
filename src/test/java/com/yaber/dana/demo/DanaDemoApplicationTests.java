package com.yaber.dana.demo;

import com.yaber.dana.demo.feign.api.DanaApi;
import com.yaber.dana.demo.sample.AsymSignUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@SpringBootTest
@Slf4j
class DanaDemoApplicationTests {

    @Autowired
    private DanaApi danaApi;

    @Test
    void contextLoads() {
//        System.out.println(AsymSignUtils.getTimestamp());
        danaApi.getBaidu();
    }

}
