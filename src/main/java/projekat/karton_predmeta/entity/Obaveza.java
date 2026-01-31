package projekat.karton_predmeta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "obaveza")
@Inheritance(strategy = InheritanceType.JOINED)
// OVO JE NOVO: Govorimo Javi kako da cita JSON
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, 
        include = JsonTypeInfo.As.PROPERTY, 
        property = "tip" // Ovo je ono polje 'tip' koje saljemo iz logika.js
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PredispitnaObaveza.class, name = "PREDISPITNA"),
        @JsonSubTypes.Type(value = ZavrsniIspit.class, name = "ZAVRSNI")
})
public abstract class Obaveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean obavezna;
    private Float poeni;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    @JsonIgnore
    private Predmet predmet;
}