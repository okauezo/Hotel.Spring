package br.com.fiap.hotel.service;

import br.com.fiap.hotel.domain.*;
import br.com.fiap.hotel.domain.enums.ReservationStatus;
import br.com.fiap.hotel.dto.*;
import br.com.fiap.hotel.exception.*;
import br.com.fiap.hotel.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository resRepo;
    private final RoomRepository roomRepo;
    private final GuestRepository guestRepo;

    public ReservationService(ReservationRepository r, RoomRepository rm, GuestRepository g){
        this.resRepo = r; this.roomRepo = rm; this.guestRepo = g;
    }

    private static long daysBetween(LocalDate a, LocalDate b){
        return Math.max(1, Duration.between(a.atStartOfDay(), b.atStartOfDay()).toDays());
    }

    @Transactional
    public ReservationResponse create(ReservationCreateRequest req){
        if(!req.getCheckinExpected().isBefore(req.getCheckoutExpected()))
            throw new InvalidDateRangeException("checkoutExpected deve ser posterior a checkinExpected");

        var room = roomRepo.findById(req.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Quarto não encontrado"));
        var guest = guestRepo.findById(req.getGuestId())
                .orElseThrow(() -> new IllegalArgumentException("Hóspede não encontrado"));

        if (room.getCapacity() < 1)
            throw new CapacityExceededException("Capacidade do quarto inválida");

        var overlaps = resRepo.findOverlaps(room.getId(), req.getCheckinExpected(), req.getCheckoutExpected());
        if(!overlaps.isEmpty()) throw new RoomUnavailableException("Quarto indisponível no período");

        var r = new Reservation();
        r.setId(UUID.randomUUID().toString());
        r.setRoom(room);
        r.setGuest(guest);
        r.setCheckinExpected(req.getCheckinExpected());
        r.setCheckoutExpected(req.getCheckoutExpected());
        r.setStatus(ReservationStatus.CREATED);

        var diarias = daysBetween(req.getCheckinExpected(), req.getCheckoutExpected());
        r.setEstimatedAmount(room.getPricePerNight().multiply(BigDecimal.valueOf(diarias)));

        resRepo.save(r);
        return toResp(r);
    }

    @Transactional(readOnly = true)
    public ReservationResponse get(String id){
        return resRepo.findById(id).map(this::toResp)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> list(){
        return resRepo.findAll().stream().map(this::toResp).toList();
    }

    @Transactional
    public ReservationResponse checkIn(String id, Instant now){
        var r = resRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        if(r.getStatus() != ReservationStatus.CREATED)
            throw new InvalidReservationStateException("Check-in só é permitido com status CREATED");

        var hoje = LocalDateTime.ofInstant(now, ZoneId.systemDefault()).toLocalDate();
        if(!hoje.equals(r.getCheckinExpected()))
            throw new CheckInWindowException("Check-in permitido somente no dia do checkinExpected");

        r.setStatus(ReservationStatus.CHECKED_IN);
        r.setCheckinAt(now);
        return toResp(r);
    }

    @Transactional
    public ReservationResponse checkOut(String id, Instant now){
        var r = resRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        if(r.getStatus() != ReservationStatus.CHECKED_IN)
            throw new InvalidReservationStateException("Check-out só é permitido após CHECKED_IN");

        r.setStatus(ReservationStatus.CHECKED_OUT);
        r.setCheckoutAt(now);

        var diarias = Math.max(1, Duration.between(
                r.getCheckinAt().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                r.getCheckoutAt().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay()
        ).toDays());
        var valor = r.getRoom().getPricePerNight().multiply(BigDecimal.valueOf(diarias));
        r.setFinalAmount(valor);

        return toResp(r);
    }

    @Transactional
    public void cancel(String id){
        var r = resRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        if(r.getStatus() != ReservationStatus.CREATED)
            throw new InvalidReservationStateException("Só é possível cancelar enquanto CREATED");
        r.setStatus(ReservationStatus.CANCELED);
    }

    private ReservationResponse toResp(Reservation r){
        var dto = new ReservationResponse();
        dto.id = r.getId();
        dto.guestId = r.getGuest().getId();
        dto.roomId = r.getRoom().getId();
        dto.checkinExpected = r.getCheckinExpected();
        dto.checkoutExpected = r.getCheckoutExpected();
        dto.checkinAt = r.getCheckinAt();
        dto.checkoutAt = r.getCheckoutAt();
        dto.status = r.getStatus().name();
        dto.estimatedAmount = r.getEstimatedAmount();
        dto.finalAmount = r.getFinalAmount();
        return dto;
    }
}
