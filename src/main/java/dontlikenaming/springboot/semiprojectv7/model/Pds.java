package dontlikenaming.springboot.semiprojectv7.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Entity
@Table(name = "pds")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    @NotBlank(message = "제목은 필수 입력 항목입니다!")
    private String title;

    @NotBlank(message = "작성자는 필수 입력 항목입니다!")
    private String userid;

    @Column(insertable = false, updatable = false)
    private Integer thumbs;

    @Column(insertable = false, updatable = false)
    private Integer views;

    @NotBlank(message = "본문은 필수 입력 항목입니다!")
    private String content;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;

    private String uuid;
}
