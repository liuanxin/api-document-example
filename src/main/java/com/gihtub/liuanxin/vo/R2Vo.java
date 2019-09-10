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
public class R2Vo {

    private int id;
    private RIIIVo r2;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class RIIIVo {

        private int id;
        private R2Vo r2;
    }


    public static R2Vo testData() {
        return new R2Vo(111, new RIIIVo(333, null));
    }
}
