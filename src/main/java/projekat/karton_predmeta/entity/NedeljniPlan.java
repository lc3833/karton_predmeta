package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nedeljni_plan")
public class NedeljniPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "broj_nedelje")
    private Integer brojNedelje;

    @Column(name = "tema", columnDefinition = "TEXT")
    private String tema;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    @JsonIgnore
    private Predmet predmet;

    @OneToOne(mappedBy = "nedeljniPlan", cascade = CascadeType.ALL)
    private TeorijskaNastava teorijskaNastava;

    @OneToOne(mappedBy = "nedeljniPlan", cascade = CascadeType.ALL)
    private PrakticnaNastava prakticnaNastava;
}