package grsh.grdv.SELP_back.dto.request;


import grsh.grdv.SELP_back.entities.Role;

import java.util.Date;

public record RegistrationFormDto(
    String username,
    String email,
    String password,
    Long birthdate,
    String sex
) {}