package br.com.fiap.hotel.exception;

public class CapacityExceededException extends RuntimeException {

    public CapacityExceededException(String msg){ super(msg); }
}
