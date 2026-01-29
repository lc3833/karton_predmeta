package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "zavrsni_ispit")
public class ZavrsniIspit extends Obaveza {

    @Column(name = "format_ispita")
    private String formatIspita;
}