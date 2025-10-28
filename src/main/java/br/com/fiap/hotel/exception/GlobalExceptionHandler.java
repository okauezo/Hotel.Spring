package br.com.fiap.hotel.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> build(HttpStatus status, String msg, String path){
        return ResponseEntity.status(status)
                .body(new ApiError(status.value(), status.getReasonPhrase(), msg, path));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex, HttpServletRequest req){
        var msg = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField()+": "+e.getDefaultMessage())
                .findFirst().orElse("Dados inválidos.");
        return build(HttpStatus.BAD_REQUEST, msg, req.getRequestURI());
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<ApiError> dateRange(InvalidDateRangeException ex, HttpServletRequest req){
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(CapacityExceededException.class)
    public ResponseEntity<ApiError> capacity(CapacityExceededException ex, HttpServletRequest req){
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(RoomUnavailableException.class)
    public ResponseEntity<ApiError> unavailable(RoomUnavailableException ex, HttpServletRequest req){
        return build(HttpStatus.CONFLICT, ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(CheckInWindowException.class)
    public ResponseEntity<ApiError> checkinWindow(CheckInWindowException ex, HttpServletRequest req){
        return build(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(InvalidReservationStateException.class)
    public ResponseEntity<ApiError> invalidState(InvalidReservationStateException ex, HttpServletRequest req){
        return build(HttpStatus.CONFLICT, ex.getMessage(), req.getRequestURI());
    }
}
