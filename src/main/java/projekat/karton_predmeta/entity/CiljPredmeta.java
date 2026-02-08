package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cilj_predmeta")
public class CiljPredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String opis;

    @OneToOne(mappedBy = "ciljPredmeta")
    @JsonIgnore
    private Predmet predmet;
}