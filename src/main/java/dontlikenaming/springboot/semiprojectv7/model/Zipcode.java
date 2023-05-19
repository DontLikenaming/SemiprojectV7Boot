package dontlikenaming.springboot.semiprojectv7.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zipcode")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Zipcode {

    private String zipcode;

    private String sido;

    private String gugun;

    private String dong;

    private String ri;

    private String bunji;

    @Id
    private Long seq;
}
