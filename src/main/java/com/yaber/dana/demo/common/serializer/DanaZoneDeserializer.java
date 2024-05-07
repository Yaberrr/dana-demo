package com.yaber.dana.demo.common.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.yaber.dana.demo.utils.DanaZoneConvertor;

import java.io.IOException;
import java.util.Date;

/**
 * 将dana日期字符串解析为date
 * @author tangyabo
 * @date 2024/5/7
 */
public class DanaZoneDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return DanaZoneConvertor.parseWithZone(jsonParser.getText());
    }
}
