package br.com.fiap.hotel.domain;

import br.com.fiap.hotel.domain.enums.ReservationStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity @Table(name = "reservations")
public class Reservation {
    @Id
    private String id;

    @ManyToOne(optional=false) @JoinColumn(name="guest_id")
    private Guest guest;

    @ManyToOne(optional=false) @JoinColumn(name="room_id")
    private Room room;

    @Column(name="checkin_expected", nullable=false)
    private LocalDate checkinExpected;

    @Column(name="checkout_expected", nullable=false)
    private LocalDate checkoutExpected;

    @Column(name="checkin_at")
    private Instant checkinAt;

    @Column(name="checkout_at")
    private Instant checkoutAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private ReservationStatus status = ReservationStatus.CREATED;

    @Column(name="estimated_amount", precision=10, scale=2)
    private BigDecimal estimatedAmount;

    @Column(name="final_amount", precision=10, scale=2)
    private BigDecimal finalAmount;

    @Column(name="created_at") private Instant createdAt = Instant.now();
    @Column(name="updated_at") private Instant updatedAt;

    @PreUpdate void touch(){ this.updatedAt = Instant.now(); }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public LocalDate getCheckinExpected() { return checkinExpected; }
    public void setCheckinExpected(LocalDate checkinExpected) { this.checkinExpected = checkinExpected; }
    public LocalDate getCheckoutExpected() { return checkoutExpected; }
    public void setCheckoutExpected(LocalDate checkoutExpected) { this.checkoutExpected = checkoutExpected; }
    public Instant getCheckinAt() { return checkinAt; }
    public void setCheckinAt(Instant checkinAt) { this.checkinAt = checkinAt; }
    public Instant getCheckoutAt() { return checkoutAt; }
    public void setCheckoutAt(Instant checkoutAt) { this.checkoutAt = checkoutAt; }
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }
    public BigDecimal getEstimatedAmount() { return estimatedAmount; }
    public void setEstimatedAmount(BigDecimal estimatedAmount) { this.estimatedAmount = estimatedAmount; }
    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
}
