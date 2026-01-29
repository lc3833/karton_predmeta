package projekat.karton_predmeta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekat.karton_predmeta.entity.NedeljniPlan;

@Repository
public interface NedeljniPlanRepository extends JpaRepository<NedeljniPlan, Long> {
}