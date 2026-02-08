package projekat.karton_predmeta.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projekat.karton_predmeta.entity.*;
import projekat.karton_predmeta.service.PredmetService;

@RestController
@RequestMapping("/api/predmeti")
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;

    @GetMapping
    public List<Predmet> dajSvePredmete() {
        return predmetService.sviPredmeti();
    }

    @GetMapping("/{id}")
    public Predmet dajPredmet(@PathVariable Long id) {
        return predmetService.pronadjiPoIdu(id);
    }

    @PostMapping
    public Predmet sacuvajPredmet(@RequestBody Predmet predmet) {
        
        if (predmet.getMetodIzvodjenja() != null) {
            predmet.getMetodIzvodjenja().setPredmet(predmet);
        }
        
        int satiTeorije = 2;
        int satiVezbi = 2;

        if (predmet.getFondCasova() != null) {
            if (predmet.getFondCasova().getTeorija() != null) {
                satiTeorije = predmet.getFondCasova().getTeorija();
            }
            if (predmet.getFondCasova().getVezbe() != null) {
                satiVezbi = predmet.getFondCasova().getVezbe();
            }
        }

        if (predmet.getNedeljniPlan() != null) {
            for (NedeljniPlan np : predmet.getNedeljniPlan()) {
                np.setPredmet(predmet);

                TeorijskaNastava tn = new TeorijskaNastava();
                tn.setOpis("Теорија: " + np.getTema());
                tn.setFondCasova(satiTeorije);
                tn.setNedeljniPlan(np); 
                np.setTeorijskaNastava(tn); 

                PrakticnaNastava pn = new PrakticnaNastava();
                pn.setOpis("Вежбе: " + np.getTema());
                pn.setFondCasova(satiVezbi);
                pn.setTip("Вежбе/ДОН");
                pn.setNedeljniPlan(np); 
                np.setPrakticnaNastava(pn); 
            }
        }

        if (predmet.getLiteratura() != null) {
            for (var lit : predmet.getLiteratura()) {
                lit.setPredmet(predmet);
            }
        }

        if (predmet.getObaveze() != null) {
            for (var ob : predmet.getObaveze()) {
                ob.setPredmet(predmet);
            }
        }
        
        if (predmet.getIshodPredmeta() != null) predmet.getIshodPredmeta().setPredmet(predmet);
        if (predmet.getCiljPredmeta() != null) predmet.getCiljPredmeta().setPredmet(predmet);
        if (predmet.getFondCasova() != null) predmet.getFondCasova().setPredmet(predmet);

        return predmetService.sacuvajPredmet(predmet);
    }
    
    @DeleteMapping("/{id}")
    public void obrisiPredmet(@PathVariable Long id) {
        predmetService.obrisiPredmet(id);
    }
}