package br.com.fiap.hotel.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ReservationCreateRequest {
    @NotBlank private String guestId;
    @NotBlank private String roomId;
    @NotNull  private LocalDate checkinExpected;
    @NotNull  private LocalDate checkoutExpected;

    public String getGuestId(){ return guestId; }
    public void setGuestId(String v){ this.guestId = v; }
    public String getRoomId(){ return roomId; }
    public void setRoomId(String v){ this.roomId = v; }
    public LocalDate getCheckinExpected(){ return checkinExpected; }
    public void setCheckinExpected(LocalDate v){ this.checkinExpected = v; }
    public LocalDate getCheckoutExpected(){ return checkoutExpected; }
    public void setCheckoutExpected(LocalDate v){ this.checkoutExpected = v; }
}
