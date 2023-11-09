package controle.finaceiro.api.controller;

import controle.finaceiro.api.config.AuthService;
import controle.finaceiro.api.user.AuthRequest;
import controle.finaceiro.api.user.AuthResponse;
import controle.finaceiro.api.user.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
