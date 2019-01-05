package com.gihtub.liuanxin.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {

    Normal(1), Vip(10);

    int code;
    UserType(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
    @JsonCreator
    public static UserType deserializer(Object obj) {
        if (obj == null) {
            return null;
        }

        String source = obj.toString().trim();
        for (UserType em : values()) {
            if (source.equalsIgnoreCase(em.name())) {
                return em;
            }
            if (source.equalsIgnoreCase(String.valueOf(em.code))) {
                return em;
            }
        }
        return null;
    }
}
