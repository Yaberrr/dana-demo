package com.yaber.dana.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * dana 生成externalId 每日不可重复
 * @author tangyabo
 * @date 2024/4/29
 */
public class DanaExternalIdGenerator {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final AtomicInteger SEQUENCE = new AtomicInteger(0);

    public static String generateId() {
        long timestamp = System.currentTimeMillis();
        int sequenceValue = SEQUENCE.getAndIncrement();
        if (sequenceValue > 9999) {
            SEQUENCE.compareAndSet(sequenceValue, 0);
        }
        return DATE_FORMAT.format(new Date(timestamp)) + String.format("%03d", sequenceValue);
    }

}
