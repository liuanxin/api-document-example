package com.gihtub.liuanxin.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    Male(0, "男"), Female(1, "女");

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
            if (source.equalsIgnoreCase(String.valueOf(em.getCode()))) {
                return em;
            }
            if (source.equalsIgnoreCase(em.getValue())) {
                return em;
            }
        }
        return null;
    }
}
