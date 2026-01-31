package projekat.karton_predmeta.entity;

import jakarta.persistence.*;
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

    // --- VEZE SA DRUGIM TABELAMA ---

    // 1. STUDIJSKI PROGRAM (Biramo postojeći)
    @ManyToOne
    @JoinColumn(name = "program_id")
    private StudijskiProgram studijskiProgram;

    // 2. NASTAVNIK (Biramo postojećeg - OVO TI JE FALILO)
    @ManyToOne
    @JoinColumn(name = "nastavnik_id")
    private Nastavnik nastavnik;

    // 3. CILJ PREDMETA (Čuvamo novi zajedno sa predmetom - OBAVEZNO CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cilj_id")
    private CiljPredmeta ciljPredmeta;

    // 4. ISHOD PREDMETA (Na frontendu je jedno polje, pa ga vezujemo kao OneToOne radi lakseg rada)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ishod_id")
    private IshodPredmeta ishodPredmeta;

    // 5. FOND ČASOVA (Čuvamo novi - OBAVEZNO CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fond_id")
    private FondCasova fondCasova;
    
    // 6. NEDELJNI PLAN (Lista od 13 nedelja - OVO JE DOBRO, SAMO PROVERI MAPPED BY)
    // "mappedBy" znači da u klasi NedeljniPlan moraš imati polje "private Predmet predmet;"
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private List<NedeljniPlan> nedeljniPlan;

    // 7. LITERATURA (OVO TI JE FALILO)
    // U klasi Literatura mora postojati polje "private Predmet predmet;"
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private List<Literatura> literatura;

    // 8. OBAVEZE (Predispitne i Završni - OVO TI JE FALILO)
    // U klasi Obaveza mora postojati polje "private Predmet predmet;"
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private List<Obaveza> obaveze;
}   