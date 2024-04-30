package com.yaber.dana.demo.sample;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 转换成印度尼西亚(雅加达)时区
 * @author tangyabo
 * @date 2024/4/29
 */
public class IndonesiaTimeConvertor {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");

    private static final ZoneId indonesiaZone = ZoneId.of("Asia/Jakarta");

    public static String convertZone(Date date){
        return date.toInstant().atZone(indonesiaZone).format(DATE_TIME_FORMATTER);
    }

}
