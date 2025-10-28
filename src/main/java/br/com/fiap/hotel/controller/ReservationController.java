package br.com.fiap.hotel.controller;

import br.com.fiap.hotel.dto.*;
import br.com.fiap.hotel.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController @RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService service;
    public ReservationController(ReservationService s){ this.service = s; }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@Valid @RequestBody ReservationCreateRequest req){
        return ResponseEntity.status(201).body(service.create(req));
    }

    @GetMapping("/{id}") public ReservationResponse get(@PathVariable String id){ return service.get(id); }
    @GetMapping public List<ReservationResponse> list(){ return service.list(); }

    @PostMapping("/{id}/check-in")
    public ReservationResponse checkIn(@PathVariable String id){
        return service.checkIn(id, Instant.now());
    }

    @PostMapping("/{id}/check-out")
    public ReservationResponse checkOut(@PathVariable String id){
        return service.checkOut(id, Instant.now());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String id){
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
