package com.labanta.servidorlocal.model;

public class DigitalServiceModel extends ServiceModel {
    private String linkDownload;

    public DigitalServiceModel (long id, String titulo, String descricao, double preco, boolean estado, double precoComDesconto, String linkDownload, String imagenCapa) {
        super(id,titulo,descricao,preco, estado, precoComDesconto, imagenCapa);
        this.linkDownload = linkDownload;
    }
    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }
}
