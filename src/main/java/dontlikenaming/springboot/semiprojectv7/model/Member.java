package dontlikenaming.springboot.semiprojectv7.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
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

    private String pnum1;

    private String pnum2;

    private String pnum3;

    private String userid;

    private String passwd;

    private String zipcode;

    private String addr1;

    private String addr2;

    private String email;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;
}
