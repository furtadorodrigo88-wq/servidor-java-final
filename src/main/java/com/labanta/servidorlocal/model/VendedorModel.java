package com.labanta.servidorlocal.model;

import java.util.ArrayList;
import java.util.List;

public class VendedorModel extends PessoaModel {
    private double taxaComissao;
    private List<ServiceModel> servicosAVenda;

    public VendedorModel(String nome, String morada, int idade, double saldo, double taxaComissao) {
        super(nome, morada, idade, saldo);
        this.taxaComissao = taxaComissao;
        this.servicosAVenda = new ArrayList<>();
    }

    public double getTaxaComissao() {
        return taxaComissao;
    }

    public void setTaxaComissao(double taxaComissao) {
        if (taxaComissao <= 0){
            System.out.println("A Taxa de comissão não pode ser menor ou igual a zero!");
            return;
        }
        this.taxaComissao = taxaComissao;
    }

    public void publicarServico(ServiceModel servico) throws  Exception{
        if(servico.getPreco() <= 0){
            throw new Exception("O preço tem de ser superior a zero!");
        }
        this.servicosAVenda.add(servico);
    }
}
