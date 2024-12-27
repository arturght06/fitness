package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Grupy\"")
public class Grupy {
    @Id
    @Column(name = "grupa_id", nullable = false)
    private Integer id;

    @Column(name = "nazwa", nullable = false, length = 30)
    private String nazwa;

    @Column(name = "typ_sportu", nullable = false, length = 15)
    private String typSportu;

    @Column(name = "opis", nullable = false, length = 400)
    private String opis;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klub_id", nullable = false)
    private KlubyLekkoatletyczne klub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pracownik_id")
    private Trenerzy pracownik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTypSportu() {
        return typSportu;
    }

    public void setTypSportu(String typSportu) {
        this.typSportu = typSportu;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public KlubyLekkoatletyczne getKlub() {
        return klub;
    }

    public void setKlub(KlubyLekkoatletyczne klub) {
        this.klub = klub;
    }

    public Trenerzy getPracownik() {
        return pracownik;
    }

    public void setPracownik(Trenerzy pracownik) {
        this.pracownik = pracownik;
    }

}