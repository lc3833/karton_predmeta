package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zvanje")
public class Zvanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;
}