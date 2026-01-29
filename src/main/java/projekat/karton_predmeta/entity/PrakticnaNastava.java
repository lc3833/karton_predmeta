package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("PRAKSA")
public class PrakticnaNastava extends NedeljniPlan {

    @Column(columnDefinition = "TEXT")
    private String opis;

    @Column(name = "fond_casova")
    private Integer fondCasova;

    // Ovo je onaj tip %(Vezbe, DON, SIR) sa dijagrama
    @Column(name = "tip_prakse")
    private String tip; 
}