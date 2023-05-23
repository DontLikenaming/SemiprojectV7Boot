package dontlikenaming.springboot.semiprojectv7.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "아이디는 필수 항목입니다!")
    @Length(min = 6, max = 16, message = "아이디는 최소 6자, 최대 16자여야 합니다!")
    private String userid;

    @NotBlank(message = "비밀번호를 입력해주세요!")
    @Length(min = 6, max = 16, message = "비밀번호는 최소 6자, 최대 16자여야 합니다!")
    private String passwd;

    private String zipcode;

    @NotBlank(message = "주소를 입력해주세요!")
    private String addr1;

    @NotBlank(message = "주소를 입력해주세요!")
    private String addr2;

    private String email;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;



    @Transient  // 필드 영속성 지원 x
    @NotBlank(message = "우편번호를 입력해주세요!")
    private String zip1;

    @Transient
    @NotBlank(message = "우편번호를 입력해주세요!")
    private String zip2;

    @Transient
    @NotBlank(message = "이메일을 입력해주세요!")
    private String email1;

    @Transient
    @NotBlank(message = "이메일을 입력해주세요!")
    private String email2;
}
