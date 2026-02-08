package projekat.karton_predmeta.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projekat.karton_predmeta.entity.Predmet;
import projekat.karton_predmeta.repository.PredmetRepository;

@Service
@RequiredArgsConstructor
public class PredmetService {

    private final PredmetRepository predmetRepository;

    public List<Predmet> sviPredmeti() {
        return predmetRepository.findAll();
    }

    public Predmet sacuvajPredmet(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    public Predmet pronadjiPoIdu(Long id) {
        return predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID-jem " + id + " nije pronadjen!"));
    }

    public void obrisiPredmet(Long id) {
        predmetRepository.deleteById(id);
    }
}