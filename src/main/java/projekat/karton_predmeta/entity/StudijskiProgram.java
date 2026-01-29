package projekat.karton_predmeta.entity; // 1. Tvoj paket

import jakarta.persistence.*;
import lombok.Data;

@Data // 2. Menja sve konstruktore, getere, setere i toString
@Entity
@Table(name = "studijski_program") // 3. Povezuje sa tabelom u bazi
public class StudijskiProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // U bazi je kolona 'naziv', pa cemo i ovde koristiti krace ime
    // Ako bas zelis dugacko ime u javi, morao bi da dodas @Column(name="naziv")
    @Column(nullable = false)
    private String naziv; 
}