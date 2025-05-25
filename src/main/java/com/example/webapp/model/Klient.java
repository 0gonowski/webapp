package com.example.webapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "klient") // tu możesz wpisać "Klienci_Firm" jeśli chcesz odwzorować oryginał
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imie;

    @Column(nullable = false)
    private String nazwisko;

    @Column(name = "numer_telefonu", nullable = false, length = 15)
    private String numerTelefonu;

    @Column(name = "data_urodzenia", nullable = false)
    private LocalDate dataUrodzenia;

    @Column(name = "data_dodania")
    private LocalDateTime dataDodania = LocalDateTime.now(); // ustawiane automatycznie

    @Column(nullable = false)
    private Boolean aktywny = true;

    @Column(nullable = false)
    private Character plec; // 'M', 'K', 'N'

    // Gettery i Settery

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }

    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public String getNumerTelefonu() { return numerTelefonu; }
    public void setNumerTelefonu(String numerTelefonu) { this.numerTelefonu = numerTelefonu; }

    public LocalDate getDataUrodzenia() { return dataUrodzenia; }
    public void setDataUrodzenia(LocalDate dataUrodzenia) { this.dataUrodzenia = dataUrodzenia; }

    public LocalDateTime getDataDodania() { return dataDodania; }
    public void setDataDodania(LocalDateTime dataDodania) { this.dataDodania = dataDodania; }

    public Boolean getAktywny() { return aktywny; }
    public void setAktywny(Boolean aktywny) { this.aktywny = aktywny; }

    public Character getPlec() { return plec; }
    public void setPlec(Character plec) { this.plec = plec; }
}
