package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "metod_izvodjenja")
public class MetodIzvodjenja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String opis;

    @OneToOne(mappedBy = "metodIzvodjenja")
    @JsonIgnore
    private Predmet predmet;
}