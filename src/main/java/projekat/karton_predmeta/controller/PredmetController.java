package projekat.karton_predmeta.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projekat.karton_predmeta.entity.Predmet;
import projekat.karton_predmeta.service.PredmetService;

@RestController
@RequestMapping("/api/predmeti")
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;

    // 1. Daj mi sve predmete
    @GetMapping
    public List<Predmet> dajSvePredmete() {
        return predmetService.sviPredmeti();
    }

    // 2. Daj mi predmet po ID-u
    @GetMapping("/{id}")
    public Predmet dajPredmet(@PathVariable Long id) {
        return predmetService.pronadjiPoIdu(id);
    }

    // 3. Sacuvaj novi predmet (sa ručnim povezivanjem dece)
    @PostMapping
    public Predmet sacuvajPredmet(@RequestBody Predmet predmet) {
        
        // --- POVEZIVANJE DECE SA RODITELJEM (PREDMETOM) ---
        // Ovo mora da se uradi jer JSON ne sadrzi povratnu vezu
        
        // 1. Povezi Nedeljni Plan
        if (predmet.getNedeljniPlan() != null) {
            for (var np : predmet.getNedeljniPlan()) {
                np.setPredmet(predmet);
            }
        }

        // 2. Povezi Literaturu
        if (predmet.getLiteratura() != null) {
            for (var lit : predmet.getLiteratura()) {
                lit.setPredmet(predmet);
            }
        }

        // 3. Povezi Obaveze (Predispitne i Završni)
        if (predmet.getObaveze() != null) {
            for (var ob : predmet.getObaveze()) {
                ob.setPredmet(predmet);
            }
        }
        
        // 4. Povezi Ishod (jer i on ima polje "private Predmet predmet")
        if (predmet.getIshodPredmeta() != null) {
             predmet.getIshodPredmeta().setPredmet(predmet);
        }

        // Pozivamo metodu koja STVARNO postoji u tvom servisu
        return predmetService.sacuvajPredmet(predmet);
    }

    // 4. Obrisi predmet
    @DeleteMapping("/{id}")
    public void obrisiPredmet(@PathVariable Long id) {
        predmetService.obrisiPredmet(id);
    }
}