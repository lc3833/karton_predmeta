package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "literatura")
public class Literatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naslov;

    @Column(nullable = false)
    private String autor;

    private String izdavac;
    private Integer godina;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    @JsonIgnore
    private Predmet predmet;
}