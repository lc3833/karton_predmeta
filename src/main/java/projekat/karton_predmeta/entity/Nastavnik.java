package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nastavnik") // Gadja tabelu 'nastavnik'
public class Nastavnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kolega je imao 'imePrezime', ali mi u bazi imamo odvojeno!
    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    // OVDE JE RAZLIKA: Nije String, nego veza ka tabeli Zvanje!
    @ManyToOne
    @JoinColumn(name = "zvanje_id", nullable = false)
    private Zvanje zvanje;
}