package main.java.model;

public class Article {

    private int id;
    private String name;
    private double price;
    private String type;
    private int stock;

    public Article() {
    }

    public Article(int id, String name, double price, String type, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
