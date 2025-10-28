package br.com.fiap.hotel.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class RoomRequest {
    @NotNull private Integer number;
    @NotBlank private String type;
    @NotNull @Min(1) private Integer capacity;
    @NotNull private BigDecimal pricePerNight;
    @NotBlank private String status; // ATIVO | INATIVO

    public Integer getNumber(){ return number; }
    public void setNumber(Integer v){ this.number = v; }
    public String getType(){ return type; }
    public void setType(String v){ this.type = v; }
    public Integer getCapacity(){ return capacity; }
    public void setCapacity(Integer v){ this.capacity = v; }
    public BigDecimal getPricePerNight(){ return pricePerNight; }
    public void setPricePerNight(BigDecimal v){ this.pricePerNight = v; }
    public String getStatus(){ return status; }
    public void setStatus(String v){ this.status = v; }
}
