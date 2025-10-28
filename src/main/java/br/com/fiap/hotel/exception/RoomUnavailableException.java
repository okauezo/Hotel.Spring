package br.com.fiap.hotel.exception;

public class RoomUnavailableException extends RuntimeException {

    public RoomUnavailableException(String msg){ super(msg); }
}
