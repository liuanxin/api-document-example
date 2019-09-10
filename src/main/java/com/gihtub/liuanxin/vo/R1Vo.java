package com.gihtub.liuanxin.vo;

import com.gihtub.liuanxin.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R1Vo {

    private int id;
    private String name;
    private RIVo r2;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class RIVo {

        private Gender gender;
        private String some;
        private Map<String, RIIVo> r3;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class RIIVo {

        private Date time;
        private Long rid;
        private List<R1Vo> r1;
    }


    public static R1Vo testData() {
        return null;
    }
}
