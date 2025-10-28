package br.com.fiap.hotel.dto;

import jakarta.validation.constraints.*;

public class GuestRequest {
    @NotBlank private String fullName;
    @NotBlank private String document;
    @NotBlank @Email private String email;
    private String phone;

    public String getFullName(){ return fullName; }
    public void setFullName(String v){ this.fullName = v; }
    public String getDocument(){ return document; }
    public void setDocument(String v){ this.document = v; }
    public String getEmail(){ return email; }
    public void setEmail(String v){ this.email = v; }
    public String getPhone(){ return phone; }
    public void setPhone(String v){ this.phone = v; }
}
