package grsh.grdv.SELP_back.dto.request;


import grsh.grdv.SELP_back.entities.Role;

public record RegistrationFormDto(
    String name,
    String lastname,
    String email,
    String password
) {}