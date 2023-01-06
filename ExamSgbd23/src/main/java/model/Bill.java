package main.java.model;

import java.sql.Date;

public class Bill {

    private int id;
    private Date bought;
    private int quantity;
    private Client client;
    private Article article;

    public Bill() {
    }

    public Bill(int id, Date bought, int quantity, Client client, Article article) {
        this.id = id;
        this.bought = bought;
        this.quantity = quantity;
        this.client = client;
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public Date getBought() {
        return bought;
    }

    public int getQuantity() {
        return quantity;
    }

    public Client getClient() {
        return client;
    }

    public Article getArticle() {
        return article;
    }

    public void setBought(Date bought) {
        this.bought = bought;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
