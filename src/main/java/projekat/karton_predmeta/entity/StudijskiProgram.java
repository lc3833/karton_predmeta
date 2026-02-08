package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "studijski_program")
public class StudijskiProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv; 
}