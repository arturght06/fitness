package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Pracownicy_biura\"")
public class PracownicyBiura {
    @Id
    @Column(name = "pracownik_id", nullable = false)
    private Integer id;

    @Column(name = "biuro_id", nullable = false)
    private Integer biuroId;

    @Column(name = "poziom_dostepu", nullable = false, length = 30)
    private String poziomDostepu;

    @Column(name = "numer_biurka", nullable = false)
    private Integer numerBiurka;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBiuroId() {
        return biuroId;
    }

    public void setBiuroId(Integer biuroId) {
        this.biuroId = biuroId;
    }

    public String getPoziomDostepu() {
        return poziomDostepu;
    }

    public void setPoziomDostepu(String poziomDostepu) {
        this.poziomDostepu = poziomDostepu;
    }

    public Integer getNumerBiurka() {
        return numerBiurka;
    }

    public void setNumerBiurka(Integer numerBiurka) {
        this.numerBiurka = numerBiurka;
    }

}