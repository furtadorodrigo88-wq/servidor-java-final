package com.labanta.servidorlocal.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String descricao;
    private double preco;
    private double precoComDesconto;
    private boolean estado;
    private String imagenCapa;

    public ServiceModel(){

    }
    public ServiceModel (long id, String titulo, String descricao, double preco, boolean estado, double precoComDesconto, String imagenCapa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.precoComDesconto = precoComDesconto;
        this.estado = estado;
        this.imagenCapa = imagenCapa;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public double getPrecoComDesconto() {
        return precoComDesconto;
    }
    public void setPrecoComDesconto(double precoComDesconto) {
        this.precoComDesconto = precoComDesconto;
    }
    public String getImagenCapa() {
        return imagenCapa;
    }
    public void setImagenCapa(String imagenCapa) {
        this.imagenCapa = imagenCapa;
    }

    public void aplicarDesconto(double percentagem){
        double desconto = (this.preco * percentagem)/100;
        this.preco = this.preco - desconto;

        System.out.println("Desconto de: " + desconto + "% " + "no serviço: " + this.titulo);
        System.out.println("Novo preço = " + this.preco);
    }
}

