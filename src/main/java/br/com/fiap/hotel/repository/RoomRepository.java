package br.com.fiap.hotel.repository;

import br.com.fiap.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {}
