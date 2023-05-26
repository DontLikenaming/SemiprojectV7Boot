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
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Entity
@Table(name = "pdsreply")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PdsReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rpno;

    @NotBlank(message = "댓글 본문은 필수 입력 항목입니다!")
    private String reply;

    @NotBlank(message = "작성자는 필수 입력 항목입니다!")
    private String userid;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;

    private Integer pno;

    private Integer refno;

}
