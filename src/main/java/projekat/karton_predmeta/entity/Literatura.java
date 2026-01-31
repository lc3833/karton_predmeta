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

    // --- OVO JE FALILO ---
    @ManyToOne
    @JoinColumn(name = "predmet_id")
    @JsonIgnore // Bitno: Da ne uđe u beskonačnu petlju kod čitanja JSON-a
    private Predmet predmet;
}