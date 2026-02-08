package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ishod_predmeta")
public class IshodPredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String opis;

    // OBRISALI SMO redniBroj JER GA NEMA VIŠE U BAZI

    @OneToOne(mappedBy = "ishodPredmeta")
    @JsonIgnore
    private Predmet predmet;
}