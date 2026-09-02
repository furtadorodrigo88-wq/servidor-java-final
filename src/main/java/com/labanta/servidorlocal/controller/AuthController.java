package com.labanta.servidorlocal.controller;

import com.labanta.servidorlocal.dto.GeoLocationResponse;
import com.labanta.servidorlocal.dto.LoginRequestDTO;
import com.labanta.servidorlocal.dto.RegistroRequestDTO;
import com.labanta.servidorlocal.model.UsersModel;
import com.labanta.servidorlocal.security.JwtService;
import com.labanta.servidorlocal.service.AuthService;
import com.labanta.servidorlocal.service.EmailService;
import com.labanta.servidorlocal.service.GeoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final GeoService geoService;
    private final AuthService authService;
    private final EmailService emailService;

    public AuthController(GeoService geoService, AuthService authService, EmailService emailService) {
        this.geoService = geoService;
        this.authService = authService;
        this.emailService = emailService;
    }

    @Operation(
            summary = "Login na sua conta",
            description = "Fassa o login numa conta existente"
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dados) {
        String token =authService.login(dados);
        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "registre uma conta nova",
            description = "Crie uma conta na plataforma"
    )
    @PostMapping("/register")
    public ResponseEntity<UsersModel> regitrar(@RequestBody RegistroRequestDTO dados) {

        UsersModel user = authService.registerUser(dados);
        /*emailService.enviarEmailBoasVindas(user.getEmail(),user.getUsername());*/
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Alerta de login",
            description = "Emvia um alerta de login por email com a localizacao do despositivo usado no login"
    )
    @PostMapping("/alerta-login")
    public ResponseEntity<String> alertaLogin(@RequestParam String email, @RequestParam String ip) {
        GeoLocationResponse geo = geoService.localizarIp(ip);
        emailService.enviarAlertaSeguranca(email, geo.getCity(), geo.getCountry_name());
        return ResponseEntity.ok("Alerta de segurança processado!");
    }
}