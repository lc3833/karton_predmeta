package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "obaveza")
@Inheritance(strategy = InheritanceType.JOINED) // KLJUČNO: Spaja tabele roditelja i dece
public abstract class Obaveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean obavezna;
    private Float poeni;
}