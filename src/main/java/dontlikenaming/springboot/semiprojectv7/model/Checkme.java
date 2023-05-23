package dontlikenaming.springboot.semiprojectv7.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Checkme {

    private String name;

    @Pattern(regexp="^[가-힣]*$", message = "잘못된 이름입니다.")
    private String name2;

    @Pattern(regexp="^[0-9]*$", message = "국번을 선택해주세요.")
    private String pnum1;

    @Pattern(regexp="^[0-9]*$", message = "잘못 입력하셨습니다.")
    private String pnum2;

    @Pattern(regexp="^[0-9]*$", message = "잘못 입력하셨습니다.")
    private String pnum3;
}
