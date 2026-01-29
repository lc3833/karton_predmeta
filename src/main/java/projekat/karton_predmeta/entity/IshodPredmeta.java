package projekat.karton_predmeta.entity;

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

    @Column(name = "redni_broj")
    private Integer redniBroj;

    // Veza: Svaki ishod pripada jednom predmetu
    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;
}