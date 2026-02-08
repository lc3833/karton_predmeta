package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @ManyToMany
    @JoinTable(
        name = "predmet_nastavnik",
        joinColumns = @JoinColumn(name = "predmet_id"),
        inverseJoinColumns = @JoinColumn(name = "nastavnik_id")
    )
    private List<Nastavnik> nastavnici = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cilj_id")
    private CiljPredmeta ciljPredmeta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ishod_id")
    private IshodPredmeta ishodPredmeta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fond_id")
    private FondCasova fondCasova;
    
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private List<NedeljniPlan> nedeljniPlan;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private List<Literatura> literatura;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private List<Obaveza> obaveze;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metod_id")
    private MetodIzvodjenja metodIzvodjenja;
}