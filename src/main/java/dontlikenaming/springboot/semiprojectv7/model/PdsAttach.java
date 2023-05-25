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
import javax.persistence.Column;


@Entity
@Table(name = "pdsattach")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PdsAttach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pano;

    private String fname;

    private String ftype;

    private String fsize;

    @Column(insertable = false, updatable = false)
    private Integer fdown;

    private Integer pno;
}
