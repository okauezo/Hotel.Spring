package br.com.fiap.hotel.service;

import br.com.fiap.hotel.domain.Room;
import br.com.fiap.hotel.domain.enums.RoomStatus;
import br.com.fiap.hotel.dto.*;
import br.com.fiap.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository repo;
    public RoomService(RoomRepository r){ this.repo = r; }

    public RoomResponse create(RoomRequest req){
        var room = new Room();
        room.setId(UUID.randomUUID().toString());
        room.setNumber(req.getNumber());
        room.setType(req.getType());
        room.setCapacity(req.getCapacity());
        room.setPricePerNight(req.getPricePerNight());
        room.setStatus(RoomStatus.valueOf(req.getStatus()));
        repo.save(room);
        return toResp(room);
    }

    public RoomResponse get(String id){ return repo.findById(id).map(this::toResp)
            .orElseThrow(() -> new IllegalArgumentException("Quarto não encontrado")); }

    public List<RoomResponse> list(){ return repo.findAll().stream().map(this::toResp).toList(); }

    public RoomResponse update(String id, RoomRequest req){
        var room = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Quarto não encontrado"));
        room.setNumber(req.getNumber());
        room.setType(req.getType());
        room.setCapacity(req.getCapacity());
        room.setPricePerNight(req.getPricePerNight());
        room.setStatus(RoomStatus.valueOf(req.getStatus()));
        repo.save(room);
        return toResp(room);
    }

    public void delete(String id){ repo.deleteById(id); }

    private RoomResponse toResp(Room r){
        var dto = new RoomResponse();
        dto.id = r.getId(); dto.number = r.getNumber();
        dto.type = r.getType(); dto.capacity = r.getCapacity();
        dto.pricePerNight = r.getPricePerNight();
        dto.status = r.getStatus().name();
        return dto;
    }
}
