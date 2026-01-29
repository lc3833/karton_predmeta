package projekat.karton_predmeta.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projekat.karton_predmeta.entity.Nastavnik;
import projekat.karton_predmeta.service.NastavnikService;

@RestController
@RequestMapping("/api/nastavnici")
@RequiredArgsConstructor
public class NastavnikController {

    private final NastavnikService service;

    @GetMapping
    public List<Nastavnik> dajSve() {
        return service.sviNastavnici();
    }

    @PostMapping
    public Nastavnik napraviNovi(@RequestBody Nastavnik nastavnik) {
        return service.sacuvaj(nastavnik);
    }
}