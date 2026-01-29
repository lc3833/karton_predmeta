package projekat.karton_predmeta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import projekat.karton_predmeta.entity.StudijskiProgram;
import projekat.karton_predmeta.repository.StudijskiProgramRepository;

@SpringBootApplication
public class KartonPredmetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KartonPredmetaApplication.class, args);
    }

    // OVO JE ZAKOMENTARISANO DA NE BI PUNILO BAZU SVAKI PUT KAD POKRENES
    /*
    @Bean
    public CommandLineRunner testiranje(StudijskiProgramRepository repo) {
        return args -> {
            System.out.println("----------------------------------------");
            System.out.println("🚀 TESTIRANJE POVEZIVANJA SA BAZOM...");
            
            StudijskiProgram sp = new StudijskiProgram();
            sp.setNaziv("Softversko inženjerstvo i AI");
            
            repo.save(sp);
            
            System.out.println("✅ USPESNO UPISANO U BAZU!");
            System.out.println("Upisani program: " + sp.getNaziv() + " (ID: " + sp.getId() + ")");
            System.out.println("----------------------------------------");
        };
    }
    */
}