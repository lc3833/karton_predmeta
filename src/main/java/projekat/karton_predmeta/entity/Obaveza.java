package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "obaveza")
@Inheritance(strategy = InheritanceType.JOINED)

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, 
        include = JsonTypeInfo.As.PROPERTY, 
        property = "tip"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PredispitnaObaveza.class, name = "PREDISPITNA"),
        @JsonSubTypes.Type(value = ZavrsniIspit.class, name = "ZAVRSNI")
})
public abstract class Obaveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "obavezna")
    private Boolean obavezna;

    @Column(name = "poeni")
    @Min(value = 0, message = "Poeni ne mogu biti negativni")
    @Max(value = 100, message = "Maksimalan broj poena je 100")
    private Double poeni;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    @JsonIgnore
    private Predmet predmet;
}