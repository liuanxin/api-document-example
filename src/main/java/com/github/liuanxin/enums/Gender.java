package com.github.liuanxin.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    Nil(0, "未知"), Male(1, "男"), Female(2, "女");

    int code;
    String value;
    Gender(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    @JsonValue
    public int getCode() {
        return code;
    }
    @JsonCreator
    public static Gender deserializer(Object obj) {
        if (obj == null) {
            return null;
        }

        String source = obj.toString().trim();
        for (Gender em : values()) {
            if (source.equalsIgnoreCase(em.name())) {
                return em;
            }
            if (source.equalsIgnoreCase(String.valueOf(em.code))) {
                return em;
            }
            if (source.equalsIgnoreCase(em.value)) {
                return em;
            }
        }
        return null;
    }
}
