package com.labanta.servidorlocal.model;

import java.util.ArrayList;
import java.util.List;

public class PessoaModel {
    private String nome;
    private String morada;
    private int idade;
    private double saldo;
    private List<ServiceModel> servicosAdquiridos;

    public PessoaModel (String nome, String morada, int idade, double saldo){
        this.nome = nome;
        this.morada = morada;
        this.idade = idade;
        this.saldo = saldo;
        this.servicosAdquiridos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void comprarServico(ServiceModel servico) throws Exception{
        if(this.saldo < servico.getPreco() || !servico.isEstado()){
            throw new Exception("Erro: Saldo insuficiente ou serviço inativo.");
        }
        this.saldo -= servico.getPreco();
        this.servicosAdquiridos.add(servico);
        System.out.print("Compra realizada com sucesso!");
    }

    public void mostrarHistorico(){
        System.out.println("--- Histórico de Compras ---");
        for (ServiceModel s : this.servicosAdquiridos) {
            System.out.println("- " + s.getTitulo());
        }
    }

    public ArrayList<ServiceModel> getServicosComprados() {
        return (ArrayList<ServiceModel>) servicosAdquiridos;
    }
}
