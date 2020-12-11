package com.cloud.demo.web.framework.encoder;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.support.SpringEncoder;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FeignModelAttributeEncoder
 * @Author JackZhou
 * @Date 2020/2/25  13:11
 **/
@Slf4j
public class FeignModelAttributeEncoder implements Encoder {

    private SpringEncoder springEncoder;

    public FeignModelAttributeEncoder(SpringEncoder springEncoder) {
        this.springEncoder = springEncoder;
    }

    @Override
    public void encode(Object requestBody, Type bodyType, RequestTemplate requestTemplate) throws EncodeException {
        if (bodyType instanceof Class) {
            Class clazz = (Class) bodyType;
            if (null != clazz.getAnnotation(FeignModelAttribute.class)) {
                log.trace("Find a QueryMapper : " + bodyType);
                modelAttributeParamEncoder(clazz, requestBody, requestTemplate);
                return;
            }
            springEncoder.encode(requestBody, bodyType, requestTemplate);
        }
    }


    public void modelAttributeParamEncoder(Class clazz, Object object, RequestTemplate template) {
        Field[] Fields = getAllField(clazz);
        for (Field field : Fields) {
            field.setAccessible(true);
            try {
                Object data = field.get(object);
                if (data == null) continue;
                if (field.getType() == List.class) {
                    List<String> s = new ArrayList<>();
                    for (Object o : (List<?>) data) {
                        s.add(o.toString());
                    }
                    template.query(field.getName(), s.toArray(new String[]{}));
                } else {
                    template.query(field.getName(), data.toString());
                }
            } catch (IllegalAccessException e) {
                continue;
            }
        }
    }

    private Field[] getAllField(Class clazz) {
        Field[] childField = null;
        if (!clazz.getSuperclass().equals(Object.class)) {
            childField = getAllField(clazz.getSuperclass());
        }
        Field[] fields = clazz.getDeclaredFields();
        if (childField != null) {
            Field[] superFields = new Field[fields.length + childField.length];
            System.arraycopy(fields, 0, superFields, 0, fields.length);
            System.arraycopy(childField, 0, superFields, fields.length, childField.length);
            return superFields;
        }
        return fields;
    }


}
