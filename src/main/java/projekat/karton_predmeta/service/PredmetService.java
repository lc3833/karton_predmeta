package projekat.karton_predmeta.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projekat.karton_predmeta.entity.Predmet;
import projekat.karton_predmeta.repository.PredmetRepository;

@Service
@RequiredArgsConstructor // Ovo nam automatski ubacuje repozitorijum (Dependency Injection)
public class PredmetService {

    private final PredmetRepository predmetRepository;

    // 1. Daj mi sve predmete (READ)
    public List<Predmet> sviPredmeti() {
        return predmetRepository.findAll();
    }

    // 2. Sacuvaj novi predmet ili azuriraj postojeci (CREATE / UPDATE)
    public Predmet sacuvajPredmet(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    // 3. Pronadji predmet po ID-u (READ by ID)
    public Predmet pronadjiPoIdu(Long id) {
        return predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID-jem " + id + " nije pronadjen!"));
    }

    // 4. Obrisi predmet (DELETE)
    public void obrisiPredmet(Long id) {
        predmetRepository.deleteById(id);
    }
}