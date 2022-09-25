package com.github.liuanxin.res;

import com.github.liuanxin.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R1Res {

    private int id;
    private String name;
    private R2Vo r2;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class R2Vo {

        private Gender gender;
        private String some;
        private Map<String, R3Vo> r3;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class R3Vo {

        private Date time;
        private Long rid;
        private List<R1Res> r1;
    }


    public static R1Res testData() {
        Map<String, R3Vo> r3Map = new HashMap<>();
        r3Map.put("ccc", new R3Vo(new Date(), 321123L, Collections.singletonList(new R1Res().setId(222).setName("ddd"))));
        return new R1Res(111, "aaa", new R2Vo(Gender.Female, "bbb", r3Map));
    }
}
