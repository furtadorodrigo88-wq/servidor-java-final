package com.labanta.servidorlocal.exeption;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExeptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExeptionHandler.class);

    @ExceptionHandler(ServiceNotFoundExeption.class)
    public ResponseEntity<Map<String,String>> handlerServiceNotFoundExepition (ServiceNotFoundExeption ex){
        //Emviar um aviso ao administrador da plataforma
        log.warn("Tentativa de acesso a um recurso inexistente: {}", ex.getMessage());

        //JSON hasmap
        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro","Recurso não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handIllegalArgumentExeption (IllegalArgumentException ex){

        log.warn("Desconto Invalido: {}",ex.getMessage());

        Map<String, String> resposta =new HashMap<>();
        resposta.put("erro","O desconto aplicado não e valido");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
    @ExceptionHandler(UsersExistenteException.class)
    public ResponseEntity<Map<String, String>> handlerUserExistenteExepition (UsersExistenteException ex) {

        log.warn("Tentativa de criação de utilizador com nome ja existente: {}", ex.getMessage());

        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro","Este username já está em uso, por favor escolha outro.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
