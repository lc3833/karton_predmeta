package projekat.karton_predmeta.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projekat.karton_predmeta.entity.StudijskiProgram;
import projekat.karton_predmeta.repository.StudijskiProgramRepository;

@Service
@RequiredArgsConstructor
public class StudijskiProgramService {

    private final StudijskiProgramRepository repository;

    public List<StudijskiProgram> sviProgrami() {
        return repository.findAll();
    }

    public StudijskiProgram sacuvaj(StudijskiProgram sp) {
        return repository.save(sp);
    }
    
    // Trebace nam da nadjemo program po ID-u kad ga povezujemo sa predmetom
    public StudijskiProgram nadjiPoIdu(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Program nije pronadjen"));
    }
}