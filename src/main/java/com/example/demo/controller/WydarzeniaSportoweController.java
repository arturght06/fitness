package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.WydarzeniaSportowe;
import com.example.demo.repository.WydarzeniaSportoweRepository;

import java.util.List;

@RestController
@RequestMapping("/api/wydarzeniaSportowe")
public class WydarzeniaSportoweController {

    @Autowired
    private WydarzeniaSportoweRepository wydarzeniaSportoweRepository;

    // GET all sports events
    @GetMapping
    public List<WydarzeniaSportowe> getAllEvents() {
        return wydarzeniaSportoweRepository.findAll();
    }

    // GET one sports event by ID
    @GetMapping("/{id}")
    public ResponseEntity<WydarzeniaSportowe> getEventById(@PathVariable Integer id) {
        return wydarzeniaSportoweRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new sports event
    @PostMapping
    public WydarzeniaSportowe createEvent(@RequestBody WydarzeniaSportowe newEvent) {
        return wydarzeniaSportoweRepository.save(newEvent);
    }

    // PUT to update an existing sports event
    @PutMapping("/{id}")
    public ResponseEntity<WydarzeniaSportowe> updateEvent(@PathVariable Integer id, @RequestBody WydarzeniaSportowe updatedEvent) {
        return wydarzeniaSportoweRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setData(updatedEvent.getData());
                    existingEvent.setNazwa(updatedEvent.getNazwa());
                    existingEvent.setTypSportu(updatedEvent.getTypSportu());
                    existingEvent.setKlub(updatedEvent.getKlub());
                    return ResponseEntity.ok(wydarzeniaSportoweRepository.save(existingEvent));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a sports event
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
        return wydarzeniaSportoweRepository.findById(id)
                .map(event -> {
                    wydarzeniaSportoweRepository.delete(event);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
