package projekat.karton_predmeta.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projekat.karton_predmeta.entity.Nastavnik;
import projekat.karton_predmeta.repository.NastavnikRepository;

@Service
@RequiredArgsConstructor
public class NastavnikService {

    private final NastavnikRepository repository;

    public List<Nastavnik> sviNastavnici() {
        return repository.findAll();
    }

    public Nastavnik sacuvaj(Nastavnik nastavnik) {
        return repository.save(nastavnik);
    }
    
    public Nastavnik nadjiPoIdu(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Nastavnik nije pronadjen"));
    }
}