package com.shinn.bookstore.auth;


import com.shinn.bookstore.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(authService.register(userDTO));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(authService.authenticate(userDTO));
    }
}
