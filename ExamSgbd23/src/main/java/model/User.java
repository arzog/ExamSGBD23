package main.java.model;

public class User {

    private int id;
    private String username;
    private String passwd; //TODO need to hash
    private String role;
    private String name;
    private String firstname;

    public User() {
    }

    public User(int id, String username, String passwd, String role, String name, String firstname) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
        this.role = role;
        this.name = name;
        this.firstname = firstname;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
