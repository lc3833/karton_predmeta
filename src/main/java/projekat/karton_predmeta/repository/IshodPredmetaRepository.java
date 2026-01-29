package projekat.karton_predmeta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekat.karton_predmeta.entity.IshodPredmeta;

@Repository
public interface IshodPredmetaRepository extends JpaRepository<IshodPredmeta, Long> {
}