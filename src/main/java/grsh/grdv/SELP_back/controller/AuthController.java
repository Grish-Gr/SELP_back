package grsh.grdv.SELP_back.controller;

import grsh.grdv.SELP_back.dto.request.LoginFormDto;
import grsh.grdv.SELP_back.dto.request.RefreshTokenFormDto;
import grsh.grdv.SELP_back.dto.request.RegistrationFormDto;
import grsh.grdv.SELP_back.dto.response.JwtResponseDto;
import grsh.grdv.SELP_back.dto.response.UserDto;
import grsh.grdv.SELP_back.entities.User;
import grsh.grdv.SELP_back.service.AuthenticationService;
import grsh.grdv.SELP_back.service.RegistrationService;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginFormDto loginFormDto){
        log.info("Try login {}", loginFormDto.toString());
        JwtResponseDto response = authenticationService.loginInSystem(loginFormDto);
        log.info("Success login user, put tokens: {}", response.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshTokens(@RequestParam String token){
        JwtResponseDto response = authenticationService.refreshJwtTokens(token);
        log.info("Refresh tokens: {}", response.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationFormDto registrationForm){
        User user = registrationService.register(registrationForm);
        log.info("Success registration user in system: {}", user.toString());
        return ResponseEntity.ok(UserDto.from(user));
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmAccount(@RequestParam String token) {
        User user = registrationService.confirmUserInSystem(token);
        log.info("User confirm in system by email: {}", user.toString());
        return ResponseEntity.ok(UserDto.from(user));
    }
}