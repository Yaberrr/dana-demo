package com.yaber.dana.demo.utils;

import com.yaber.dana.demo.config.DanaConfig;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * dana时区转换类
 * @author tangyabo
 * @date 2024/4/29
 */
public class DanaZoneConvertor {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =  DateTimeFormatter.ofPattern(DanaConfig.DATE_TIME_PATTERN);

    public static String formatWithZone(Date date){
        return date.toInstant().atZone(DanaConfig.ZONE_ID).format(DATE_TIME_FORMATTER);
    }

    public static Date parseWithZone(String dateStr){
        return Date.from(ZonedDateTime.parse(dateStr, DATE_TIME_FORMATTER).toInstant());
    }

}
