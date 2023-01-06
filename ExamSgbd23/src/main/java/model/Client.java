package main.java.model;

public class Client {

    private int id;
    private String name;
    private String firstname;
    private String company;
    private Address address;

    public Client() {
    }

    public Client(int id, String name, String firstname, String company, Address address) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.company = company;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getCompany() {
        return company;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
