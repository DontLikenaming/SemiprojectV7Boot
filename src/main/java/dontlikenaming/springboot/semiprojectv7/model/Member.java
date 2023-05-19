package dontlikenaming.springboot.semiprojectv7.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbno;

    private String name;

    private Integer pnum1;

    private Integer pnum2;

    private Integer pnum3;

    private String userid;

    private String passwd;

    private String zipcode;

    private String addr1;

    private String addr2;

    private String email;

    private LocalDateTime regdate;
}
