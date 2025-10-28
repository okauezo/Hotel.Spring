package br.com.fiap.hotel.repository;

import br.com.fiap.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query("""
        select r from Reservation r
        where r.room.id = :roomId
          and r.status <> br.com.fiap.hotel.domain.enums.ReservationStatus.CANCELED
          and r.checkinExpected < :checkout
          and r.checkoutExpected > :checkin
    """)
    List<Reservation> findOverlaps(@Param("roomId") String roomId,
                                   @Param("checkin") LocalDate checkin,
                                   @Param("checkout") LocalDate checkout);
}
