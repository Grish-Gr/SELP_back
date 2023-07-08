package grsh.grdv.SELP_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/testing")
public class TestController {

    @GetMapping("/getme")
    public ResponseEntity<?> getMe() {
        return ResponseEntity.ok("Okey, This work");
    }
}
