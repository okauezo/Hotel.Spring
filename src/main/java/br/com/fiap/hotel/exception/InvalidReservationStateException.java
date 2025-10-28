package br.com.fiap.hotel.exception;

public class InvalidReservationStateException extends RuntimeException {

    public InvalidReservationStateException(String msg){ super(msg); }
}
