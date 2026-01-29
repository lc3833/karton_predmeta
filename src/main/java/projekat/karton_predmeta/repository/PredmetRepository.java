package projekat.karton_predmeta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekat.karton_predmeta.entity.Predmet;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {
    // Ovde cemo kasnije dodavati pretrage, npr:
    // List<Predmet> findByEspbGreaterThan(int bodovi);
    // Ali za sad je prazno sasvim dovoljno!
}