package br.com.fiap.hotel.service;

import br.com.fiap.hotel.domain.Guest;
import br.com.fiap.hotel.dto.*;
import br.com.fiap.hotel.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GuestService {
    private final GuestRepository repo;
    public GuestService(GuestRepository repo){ this.repo = repo; }

    public GuestResponse create(GuestRequest req){
        var g = new Guest();
        g.setId(UUID.randomUUID().toString());
        g.setFullName(req.getFullName());
        g.setDocument(req.getDocument());
        g.setEmail(req.getEmail());
        g.setPhone(req.getPhone());
        repo.save(g);
        return toResp(g);
    }

    public GuestResponse get(String id){
        return repo.findById(id).map(this::toResp)
                .orElseThrow(() -> new IllegalArgumentException("Hóspede não encontrado"));
    }

    public List<GuestResponse> list(){
        return repo.findAll().stream().map(this::toResp).toList();
    }

    public GuestResponse update(String id, GuestRequest req){
        var g = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Hóspede não encontrado"));
        g.setFullName(req.getFullName());
        g.setDocument(req.getDocument());
        g.setEmail(req.getEmail());
        g.setPhone(req.getPhone());
        repo.save(g);
        return toResp(g);
    }

    public void delete(String id){ repo.deleteById(id); }

    private GuestResponse toResp(Guest g){
        var r = new GuestResponse();
        r.id = g.getId(); r.fullName = g.getFullName();
        r.document = g.getDocument(); r.email = g.getEmail(); r.phone = g.getPhone();
        return r;
    }
}
