package com.yaber.dana.demo.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yaber.dana.demo.utils.DanaZoneConvertor;

import java.io.IOException;
import java.util.Date;

/**
 * date转换为dana日期字符串
 * @author tangyabo
 * @date 2024/5/7
 */
public class DanaZoneSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DanaZoneConvertor.formatWithZone(date));
    }
}
