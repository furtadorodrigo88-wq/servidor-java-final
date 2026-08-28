package com.labanta.servidorlocal.dto;

public class RegistroRequestDTO {
    private String username;
    private String password;
    private String email;

    public RegistroRequestDTO(){

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
