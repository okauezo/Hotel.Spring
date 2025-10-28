package br.com.fiap.hotel.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class ReservationResponse {
    public String id;
    public String guestId;
    public String roomId;
    public LocalDate checkinExpected;
    public LocalDate checkoutExpected;
    public Instant checkinAt;
    public Instant checkoutAt;
    public String status;
    public BigDecimal estimatedAmount;
    public BigDecimal finalAmount;
}
