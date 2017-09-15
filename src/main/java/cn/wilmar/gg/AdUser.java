package cn.wilmar.gg;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 创建 by 殷国伟 于 2017/9/3.
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class AdUser {
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "uuid")
    String id;
    @NotNull
    @NonNull
    @Column(unique = true)
    String sAMAccountName;
    @NotNull
    @NonNull
    String displayName;
    String company;
    String mail;

    int age;
    @Enumerated(EnumType.STRING)
    Gender gender;

}
