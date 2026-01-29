package projekat.karton_predmeta.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projekat.karton_predmeta.entity.StudijskiProgram;
import projekat.karton_predmeta.service.StudijskiProgramService;

@RestController
@RequestMapping("/api/programi")
@RequiredArgsConstructor
public class StudijskiProgramController {

    private final StudijskiProgramService service;

    @GetMapping
    public List<StudijskiProgram> dajSve() {
        return service.sviProgrami();
    }

    @PostMapping
    public StudijskiProgram napraviNovi(@RequestBody StudijskiProgram sp) {
        return service.sacuvaj(sp);
    }
}