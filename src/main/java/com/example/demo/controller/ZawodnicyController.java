package com.example.demo.controller;

import com.example.demo.model.Zawodnicy;
import com.example.demo.repository.ZawodnicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zawodnicy")
public class ZawodnicyController {

    @Autowired
    private ZawodnicyRepository zawodnicyRepository;

    // GET all athletes
    @GetMapping
    public List<Zawodnicy> getAllAthletes() {
        return zawodnicyRepository.findAll();
    }

    // GET one athlete by ID
    @GetMapping("/{id}")
    public ResponseEntity<Zawodnicy> getAthleteById(@PathVariable Integer id) {
        return zawodnicyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new athlete
    @PostMapping
    public Zawodnicy createAthlete(@RequestBody Zawodnicy newAthlete) {
        return zawodnicyRepository.save(newAthlete);
    }

    // PUT to update an existing athlete
    @PutMapping("/{id}")
    public ResponseEntity<Zawodnicy> updateAthlete(@PathVariable Integer id, @RequestBody Zawodnicy updatedAthlete) {
        return zawodnicyRepository.findById(id)
                .map(existingAthlete -> {
                    existingAthlete.setImie(updatedAthlete.getImie());
                    existingAthlete.setNazwisko(updatedAthlete.getNazwisko());
                    existingAthlete.setNumerTelefonu(updatedAthlete.getNumerTelefonu());
                    existingAthlete.setDataUrodzenia(updatedAthlete.getDataUrodzenia());
                    existingAthlete.setEmail(updatedAthlete.getEmail());
                    existingAthlete.setOplataSubskrypcyjna(updatedAthlete.getOplataSubskrypcyjna());
                    return ResponseEntity.ok(zawodnicyRepository.save(existingAthlete));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an athlete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAthlete(@PathVariable Integer id) {
        return zawodnicyRepository.findById(id)
                .map(athlete -> {
                    zawodnicyRepository.delete(athlete);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
