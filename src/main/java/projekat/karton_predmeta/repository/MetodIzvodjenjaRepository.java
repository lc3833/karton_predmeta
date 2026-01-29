package projekat.karton_predmeta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekat.karton_predmeta.entity.MetodIzvodjenja;

@Repository
public interface MetodIzvodjenjaRepository extends JpaRepository<MetodIzvodjenja, Long> {
}