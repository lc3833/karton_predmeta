package projekat.karton_predmeta.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projekat.karton_predmeta.entity.Zvanje;
import projekat.karton_predmeta.repository.ZvanjeRepository;

@Service
@RequiredArgsConstructor
public class ZvanjeService {
    private final ZvanjeRepository repository;

    public List<Zvanje> svaZvanja() {
        return repository.findAll();
    }
    
    public Zvanje sacuvaj(Zvanje z) {
        return repository.save(z);
    }
}