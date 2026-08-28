package com.labanta.servidorlocal.model;

public class ProdutoExternoModel {
    private String id;
    private String title;
    private String description;
    private String category;
    private double price;

    public ProdutoExternoModel (String id, String title, String description, String category, double price){
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo(){
        return title;
    }
    public void setTitulo(String titulo){
        this.title = titulo;
    }
    public String getDescricao(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
