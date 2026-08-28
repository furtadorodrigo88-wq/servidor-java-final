package com.labanta.servidorlocal.dto;

public class ServiceResponseDTO {

    private String titulo;
    private Double precoFinal;

    public ServiceResponseDTO(String titulo, Double precoFinal) {
        this.titulo = titulo;
        this.precoFinal = precoFinal;
    }


    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Double getPrecoFinal() {
        return precoFinal;
    }
    public void setPrecoFinal(Double precoFinal) {
        this.precoFinal = precoFinal;
    }
}