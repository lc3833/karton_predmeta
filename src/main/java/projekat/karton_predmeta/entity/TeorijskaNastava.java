package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teorijska_nastava")
public class TeorijskaNastava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String opis;

    @Column(name = "fond_casova")
    private Integer fondCasova;

    @OneToOne
    @JoinColumn(name = "nedeljni_plan_id")
    @JsonIgnore
    private NedeljniPlan nedeljniPlan;
}