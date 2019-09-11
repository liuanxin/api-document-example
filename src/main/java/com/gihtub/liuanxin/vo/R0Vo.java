package com.gihtub.liuanxin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R0Vo {

    private int id;
    private R10Vo r10;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class R10Vo {

        private int id;
        private R0Vo r0;
    }


    public static R0Vo testData() {
        return new R0Vo(111, new R10Vo(333, null));
    }
}
