package projekat.karton_predmeta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekat.karton_predmeta.entity.Obaveza;

@Repository
public interface ObavezaRepository extends JpaRepository<Obaveza, Long> {
}