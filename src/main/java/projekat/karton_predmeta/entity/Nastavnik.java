package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nastavnik")
public class Nastavnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @ManyToOne
    @JoinColumn(name = "zvanje_id", nullable = false)
    private Zvanje zvanje;
}