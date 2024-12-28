package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.DoswiadczeniaSportowe;
import com.example.demo.repository.DoswiadczeniaSportoweRepository;

import java.util.List;

@RestController
@RequestMapping("/api/doswiadczeniaSportowe")
public class DoswiadczeniaSportoweController {

    @Autowired
    private DoswiadczeniaSportoweRepository repository;

    // GET all experiences
    @GetMapping
    public List<DoswiadczeniaSportowe> getAllExperiences() {
        return repository.findAll();
    }

    // GET one experience by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoswiadczeniaSportowe> getExperienceById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new experience
    @PostMapping
    public DoswiadczeniaSportowe createExperience(@RequestBody DoswiadczeniaSportowe newExperience) {
        // Simple logic to check if the end date is after the start date
        if (newExperience.getDataZakonczenia() != null &&
                newExperience.getDataRozpoczecia().isAfter(newExperience.getDataZakonczenia())) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        return repository.save(newExperience);
    }

    // PUT update an experience
    @PutMapping("/{id}")
    public ResponseEntity<DoswiadczeniaSportowe> updateExperience(@PathVariable Integer id,
                                                                  @RequestBody DoswiadczeniaSportowe updatedExperience) {
        return repository.findById(id)
                .map(existingExperience -> {
                    existingExperience.setNazwa(updatedExperience.getNazwa());
                    existingExperience.setOpis(updatedExperience.getOpis());
                    existingExperience.setDataRozpoczecia(updatedExperience.getDataRozpoczecia());
                    existingExperience.setDataZakonczenia(updatedExperience.getDataZakonczenia());
                    existingExperience.setPracownik(updatedExperience.getPracownik());
                    repository.save(existingExperience);
                    return ResponseEntity.ok(existingExperience);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an experience
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable Integer id) {
        return repository.findById(id)
                .map(existingExperience -> {
                    repository.delete(existingExperience);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
