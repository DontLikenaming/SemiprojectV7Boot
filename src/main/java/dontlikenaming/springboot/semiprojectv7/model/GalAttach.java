package dontlikenaming.springboot.semiprojectv7.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "galattach")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GalAttach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gano;

    private String fname;

    private String fsize;

    private Integer gno;
}
