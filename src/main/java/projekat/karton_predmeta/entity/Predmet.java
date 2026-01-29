package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "predmet")
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv") 
    private String naziv; 

    @Column(name = "status")
    private String status;

    @Column(name = "espb")
    private Integer espb;

    private String uslov;

    
    @ManyToOne
    @JoinColumn(name = "program_id")
    private StudijskiProgram studijskiProgram;


    @OneToOne
    @JoinColumn(name = "cilj_id")
    private CiljPredmeta ciljPredmeta;

    @OneToOne
    @JoinColumn(name = "fond_id")
    private FondCasova fondCasova;
    
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private java.util.List<NedeljniPlan> nedeljniPlan;
}