package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fond_casova")
public class FondCasova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer teorija;
    private Integer vezbe;
    private Integer don;
    private Integer sir;
    private Integer ostalo;

    @OneToOne(mappedBy = "fondCasova")
    @JsonIgnore
    private Predmet predmet;
}