package br.com.fiap.hotel.domain;

import br.com.fiap.hotel.domain.enums.RoomStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "rooms")
public class Room {
    @Id
    private String id;

    @Column(name="number", nullable=false, unique=true)
    private Integer number;

    @Column(name="type", nullable=false, length=20)
    private String type;

    @Column(nullable=false)
    private Integer capacity;

    @Column(name="price_per_night", nullable=false, precision=10, scale=2)
    private BigDecimal pricePerNight;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private RoomStatus status = RoomStatus.ATIVO;


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public BigDecimal getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(BigDecimal pricePerNight) { this.pricePerNight = pricePerNight; }
    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }
}
