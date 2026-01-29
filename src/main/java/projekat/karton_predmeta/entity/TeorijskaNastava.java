package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("TEORIJA")
public class TeorijskaNastava extends NedeljniPlan {

    @Column(columnDefinition = "TEXT")
    private String opis;

    @Column(name = "fond_casova")
    private Integer fondCasova;
}