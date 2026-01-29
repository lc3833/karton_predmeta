package projekat.karton_predmeta.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projekat.karton_predmeta.entity.Zvanje;
import projekat.karton_predmeta.service.ZvanjeService;

@RestController
@RequestMapping("/api/zvanja")
@RequiredArgsConstructor
public class ZvanjeController {

    private final ZvanjeService service;

    @GetMapping
    public List<Zvanje> dajSve() {
        return service.svaZvanja();
    }
    
    @PostMapping
    public Zvanje napraviNovo(@RequestBody Zvanje z) {
        return service.sacuvaj(z);
    }
}