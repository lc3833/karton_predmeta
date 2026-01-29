package projekat.karton_predmeta.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projekat.karton_predmeta.entity.Predmet;
import projekat.karton_predmeta.service.PredmetService;

@RestController
@RequestMapping("/api/predmeti") // Ovo je adresa na internetu (npr. localhost:8080/api/predmeti)
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;

    // 1. Daj mi sve predmete (GET zahtev)
    @GetMapping
    public List<Predmet> dajSvePredmete() {
        return predmetService.sviPredmeti();
    }

    // 2. Daj mi predmet po ID-u (GET zahtev sa brojem)
    @GetMapping("/{id}")
    public Predmet dajPredmet(@PathVariable Long id) {
        return predmetService.pronadjiPoIdu(id);
    }

    // 3. Sacuvaj novi predmet (POST zahtev sa podacima)
    @PostMapping
    public Predmet sacuvajPredmet(@RequestBody Predmet predmet) {
        return predmetService.sacuvajPredmet(predmet);
    }

    // 4. Obrisi predmet (DELETE zahtev sa brojem)
    @DeleteMapping("/{id}")
    public void obrisiPredmet(@PathVariable Long id) {
        predmetService.obrisiPredmet(id);
    }
}