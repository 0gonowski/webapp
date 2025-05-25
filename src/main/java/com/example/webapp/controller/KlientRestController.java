package com.example.webapp.controller;

import com.example.webapp.model.Klient;
import com.example.webapp.repository.KlientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class KlientRestController {

    private final KlientRepository klientRepository;

    public KlientRestController(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    // GET: wszystkie rekordy + sortowanie
    @GetMapping
    public List<Klient> getAll(@RequestParam(required = false) String sort) {
        List<Klient> klienci = klientRepository.findAll();

        if ("imie".equalsIgnoreCase(sort)) {
            klienci.sort((a, b) -> a.getImie().compareToIgnoreCase(b.getImie()));
        } else if ("nazwisko".equalsIgnoreCase(sort)) {
            klienci.sort((a, b) -> a.getNazwisko().compareToIgnoreCase(b.getNazwisko()));
        } else if ("data".equalsIgnoreCase(sort)) {
            klienci.sort((a, b) -> a.getDataUrodzenia().compareTo(b.getDataUrodzenia()));
        } else if ("aktywny".equalsIgnoreCase(sort)) {
            klienci.sort((a, b) -> b.getAktywny().compareTo(a.getAktywny()));
        }

        return klienci;
    }

    // GET: pojedynczy klient
    @GetMapping("/{id}")
    public Klient getById(@PathVariable Long id) {
        return klientRepository.findById(id).orElse(null);
    }

    // POST: dodaj klienta
    @PostMapping
    public Klient create(@RequestBody Klient klient) {
        return klientRepository.save(klient);
    }

    // PUT: aktualizuj klienta
    @PutMapping("/{id}")
    public Klient update(@PathVariable Long id, @RequestBody Klient klient) {
        klient.setId(id);
        return klientRepository.save(klient);
    }

    // DELETE: usuń klienta
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        klientRepository.deleteById(id);
    }
}
