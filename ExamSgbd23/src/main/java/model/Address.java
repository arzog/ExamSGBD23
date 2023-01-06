package main.java.model;

public class Address {

    private int id;
    private String country;
    private String city;
    private int cp;
    private String road;
    private String number;

    public Address() {
    }

    public Address(int id, String country, String city, int cp, String road, String number) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.cp = cp;
        this.road = road;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getCp() {
        return cp;
    }

    public String getRoad() {
        return road;
    }

    public String getNumber() {
        return number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
