package cn.wilmar.gg;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 创建 by 殷国伟 于 2017/9/3.
 */
@Data
@ToString
@NoArgsConstructor
public class GGResult {
    //{"name":"殷国伟","country":"CN","gender":"unknown","samples":0,"accuracy":0,"duration":"58ms"}
    String name;
    String country;
    String gender;
    int samples;
    String accuracy;
    String duration;
}
