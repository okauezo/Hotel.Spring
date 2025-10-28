package br.com.fiap.hotel.controller;

import br.com.fiap.hotel.dto.*;
import br.com.fiap.hotel.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/guests")
public class GuestController {
    private final GuestService service;
    public GuestController(GuestService s){ this.service = s; }

    @PostMapping
    public ResponseEntity<GuestResponse> create(@Valid @RequestBody GuestRequest req){
        return ResponseEntity.status(201).body(service.create(req));
    }

    @GetMapping("/{id}") public GuestResponse get(@PathVariable String id){ return service.get(id); }
    @GetMapping public List<GuestResponse> list(){ return service.list(); }

    @PutMapping("/{id}")
    public GuestResponse update(@PathVariable String id, @Valid @RequestBody GuestRequest req){
        return service.update(id, req);
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
