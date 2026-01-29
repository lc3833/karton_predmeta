package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fond_casova")
public class FondCasova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int teorija;
    private int vezbe;
    private int don;
    private int ostalo;
    private int sir;
}