package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nedeljni_plan")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Sve cuvamo u jednoj tabeli radi lakseg rada
@DiscriminatorColumn(name = "tip_nastave", discriminatorType = DiscriminatorType.STRING)
public abstract class NedeljniPlan { // MORA BITI ABSTRACT PO DIJAGRAMU

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "broj_nedelje")
    private Integer brojNedelje;

    @Column(columnDefinition = "TEXT")
    private String tema;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    @JsonIgnore
    private Predmet predmet;
}