package grsh.grdv.SELP_back.controller;

import grsh.grdv.SELP_back.dto.response.UserDto;
import grsh.grdv.SELP_back.entities.User;
import grsh.grdv.SELP_back.security.JwtAuthentication;
import grsh.grdv.SELP_back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getInfoUser(JwtAuthentication authentication) {
        log.info("Try get user info");
        User user = userService.getUserById(authentication.getUserID());
        log.info("Get user info " + user.getId());
        return ResponseEntity.ok(UserDto.from(user));
    }
}
