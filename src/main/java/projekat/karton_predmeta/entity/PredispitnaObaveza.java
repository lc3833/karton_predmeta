package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "predispitna_obaveza")
public class PredispitnaObaveza extends Obaveza {

    @Column(name = "opis_aktivnosti")
    private String opisAktivnosti;
}