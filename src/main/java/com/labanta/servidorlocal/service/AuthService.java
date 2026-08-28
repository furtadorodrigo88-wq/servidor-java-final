package com.labanta.servidorlocal.service;

import com.labanta.servidorlocal.dto.LoginRequestDTO;
import com.labanta.servidorlocal.dto.RegistroRequestDTO;
import com.labanta.servidorlocal.exeption.UsersExistenteException;
import com.labanta.servidorlocal.model.UsersModel;
import com.labanta.servidorlocal.repository.UsersRepository;
import com.labanta.servidorlocal.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersRepository repositorio;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AuthService (UsersRepository repositorio,JwtService jwtService,EmailService emailService){
        this.repositorio = repositorio;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public UsersModel registerUser (RegistroRequestDTO dados){
        if (repositorio.findByUsername(dados.getUsername()).isPresent()){
            throw new UsersExistenteException("Este username já está em uso, por favor escolha outro.");
        }

        UsersModel user = new UsersModel(
                dados.getUsername(),
                dados.getPassword(),
                dados.getEmail()
        );
        return repositorio.save(user);
    }

    public String login (LoginRequestDTO dados) {
        UsersModel users = repositorio.findByUsername(dados.getUsername())
                .orElseThrow(() -> new RuntimeException("Username ou password inválidos."));

        if (!users.getPassword().equals(dados.getPassword()) || !users.getUsername().equals(dados.getUsername())){
            throw new RuntimeException("Username ou password inválidos.");
        }

        return jwtService.gerarToken(users.getUsername());
    }
}
