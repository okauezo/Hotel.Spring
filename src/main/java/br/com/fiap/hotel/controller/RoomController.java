package br.com.fiap.hotel.controller;

import br.com.fiap.hotel.dto.*;
import br.com.fiap.hotel.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService service;
    public RoomController(RoomService s){ this.service = s; }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomRequest req){
        return ResponseEntity.status(201).body(service.create(req));
    }

    @GetMapping("/{id}") public RoomResponse get(@PathVariable String id){ return service.get(id); }
    @GetMapping public List<RoomResponse> list(){ return service.list(); }

    @PutMapping("/{id}")
    public RoomResponse update(@PathVariable String id, @Valid @RequestBody RoomRequest req){
        return service.update(id, req);
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
