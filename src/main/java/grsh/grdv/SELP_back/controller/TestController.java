package grsh.grdv.SELP_back.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("api/testing")
public class TestController {

    @GetMapping("/getme")
    public ResponseEntity<?> getMe() {
        log.info("Okey, JWT working");
        return ResponseEntity.ok("Okey, This work");
    }
}
