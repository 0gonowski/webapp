package com.example.webapp.controller;

import com.example.webapp.model.Klient;
import com.example.webapp.repository.KlientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/klienci")
public class KlientController {

    private final KlientRepository klientRepository;

    public KlientController(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    @GetMapping("/lista")
    public String lista(Model model, @RequestParam(required = false) String sort) {
        List<Klient> klienci = klientRepository.findAll();

        if ("imie".equalsIgnoreCase(sort)) {
            klienci.sort(Comparator.comparing(Klient::getImie));
        } else if ("nazwisko".equalsIgnoreCase(sort)) {
            klienci.sort(Comparator.comparing(Klient::getNazwisko));
        } else if ("data".equalsIgnoreCase(sort)) {
            klienci.sort(Comparator.comparing(Klient::getDataUrodzenia));
        } else if ("aktywny".equalsIgnoreCase(sort)) {
            klienci.sort(Comparator.comparing(Klient::getAktywny).reversed()); // true on top
        }

        model.addAttribute("klienci", klienci);
        return "clients";
    }



    @GetMapping("/nowy")
    public String nowyFormularz(Model model) {
        model.addAttribute("klient", new Klient());
        return "klient-form";
    }

    @PostMapping("/dodaj")
    public String dodaj(@ModelAttribute Klient klient) {
        klientRepository.save(klient);
        return "redirect:/klienci/lista";
    }

    @GetMapping("/edytuj/{id}")
    public String edytujFormularz(@PathVariable Long id, Model model) {
        Optional<Klient> klient = klientRepository.findById(id);
        if (klient.isPresent()) {
            model.addAttribute("klient", klient.get());
            return "klient-form";
        }
        return "redirect:/klienci/lista";
    }

    @PostMapping("/edytuj/{id}")
    public String zapiszEdycje(@PathVariable Long id, @ModelAttribute Klient klient) {
        klient.setId(id);
        klientRepository.save(klient);
        return "redirect:/klienci/lista";
    }

    @GetMapping("/usun/{id}")
    public String usun(@PathVariable Long id) {
        klientRepository.deleteById(id);
        return "redirect:/klienci/lista";
    }

    @GetMapping("/szukaj")
    public String szukaj(@RequestParam String q, Model model) {
        List<Klient> wyniki = klientRepository.findAll().stream()
                .filter(k -> k.getImie().toLowerCase().contains(q.toLowerCase())
                        || k.getNazwisko().toLowerCase().contains(q.toLowerCase())
                        || k.getNumerTelefonu().contains(q))
                .toList();

        model.addAttribute("klienci", wyniki);
        return "clients";
    }
}
