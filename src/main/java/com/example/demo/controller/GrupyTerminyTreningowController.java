package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.GrupyTerminyTreningow;
import com.example.demo.repository.GrupyTerminyTreningowRepository;

import java.util.List;

@RestController
@RequestMapping("/api/grupyTerminyTreningow")
public class GrupyTerminyTreningowController {

    @Autowired
    private GrupyTerminyTreningowRepository repository;

    // GET all group training sessions
    @GetMapping
    public List<GrupyTerminyTreningow> getAllGroupTrainingSessions() {
        return repository.findAll();
    }

    // GET one group training session by ID
    @GetMapping("/{grupaId}/{terminId}")
    public ResponseEntity<GrupyTerminyTreningow> getGroupTrainingSessionById(@PathVariable Long grupaId, @PathVariable Long terminId) {
        return repository.findById(new GrupyTerminyTreningowId(grupaId, terminId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new group training session
    @PostMapping
    public GrupyTerminyTreningow createGroupTrainingSession(@RequestBody GrupyTerminyTreningow newGroupTrainingSession) {
        return repository.save(newGroupTrainingSession);
    }

    // PUT update a group training session
    @PutMapping("/{grupaId}/{terminId}")
    public ResponseEntity<GrupyTerminyTreningow> updateGroupTrainingSession(@PathVariable Long grupaId, @PathVariable Long terminId, @RequestBody GrupyTerminyTreningow updatedGroupTrainingSession) {
        return repository.findById(new GrupyTerminyTreningowId(grupaId, terminId))
                .map(existingGroupTrainingSession -> {
                    existingGroupTrainingSession.setGrupa(updatedGroupTrainingSession.getGrupa());
                    existingGroupTrainingSession.setTermin(updatedGroupTrainingSession.getTermin());
                    return ResponseEntity.ok(repository.save(existingGroupTrainingSession));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a group training session
    @DeleteMapping("/{grupaId}/{terminId}")
    public ResponseEntity<?> deleteGroupTrainingSession(@PathVariable Long grupaId, @PathVariable Long terminId) {
        return repository.findById(new GrupyTerminyTreningowId(grupaId, terminId))
                .map(existingGroupTrainingSession -> {
                    repository.delete(existingGroupTrainingSession);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
