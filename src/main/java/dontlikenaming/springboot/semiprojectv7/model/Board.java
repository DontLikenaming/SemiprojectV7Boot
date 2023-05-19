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
@Table(name = "board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String userid;

    @Column(insertable = false, updatable = false)
    private Integer thumbs;

    @Column(insertable = false, updatable = false)
    private Integer views;

    private String content;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;
}
