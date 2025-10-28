package br.com.fiap.hotel.repository;

import br.com.fiap.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, String> {}
