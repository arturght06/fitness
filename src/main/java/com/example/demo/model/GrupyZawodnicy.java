package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "\"Grupy_Zawodnicy\"")
public class GrupyZawodnicy {
    @Column(name = "grupa_id", nullable = false)
    private Integer grupaId;

    @Column(name = "zawodnik_id", nullable = false)
    private Integer zawodnikId;

    @Column(name = "data_przypisania", nullable = false)
    private Instant dataPrzypisania;

    @Column(name = "data_wypisania")
    private Instant dataWypisania;

    public Integer getGrupaId() {
        return grupaId;
    }

    public void setGrupaId(Integer grupaId) {
        this.grupaId = grupaId;
    }

    public Integer getZawodnikId() {
        return zawodnikId;
    }

    public void setZawodnikId(Integer zawodnikId) {
        this.zawodnikId = zawodnikId;
    }

    public Instant getDataPrzypisania() {
        return dataPrzypisania;
    }

    public void setDataPrzypisania(Instant dataPrzypisania) {
        this.dataPrzypisania = dataPrzypisania;
    }

    public Instant getDataWypisania() {
        return dataWypisania;
    }

    public void setDataWypisania(Instant dataWypisania) {
        this.dataWypisania = dataWypisania;
    }

}