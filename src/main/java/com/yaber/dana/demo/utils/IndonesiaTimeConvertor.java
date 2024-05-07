package com.yaber.dana.demo.utils;

import com.yaber.dana.demo.feign.config.DanaConfig;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * 转换成印度尼西亚(雅加达)时区
 * @author tangyabo
 * @date 2024/4/29
 */
public class IndonesiaTimeConvertor {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =  DateTimeFormatter.ofPattern(DanaConfig.DATE_PATTERN);

    private static final ZoneId indonesiaZone = ZoneId.of("Asia/Jakarta");

    public static String convertZone(Date date){
        return date.toInstant().atZone(indonesiaZone).format(DATE_TIME_FORMATTER);
    }

}
