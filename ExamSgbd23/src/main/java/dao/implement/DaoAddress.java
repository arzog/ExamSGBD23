package main.java.dao.implement;

import main.java.dao.Dao;
import main.java.model.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoAddress extends Dao<Address> {
    @Override
    public boolean create(Address address) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into address(country,city,cp,road,number) " +
                            "values (?,?,?,?,?)");
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setInt(3, address.getCp());
            statement.setString(4, address.getRoad());
            statement.setString(5, address.getNumber());

            statement.executeUpdate();
            statement.close();

            System.out.println("Address successfully added");
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Address address) {
        return false;
    }

    @Override
    public boolean update(Address address) {
        return false;
    }

    @Override
    public Address find(int id) {
        return null;
    }

    @Override
    public Address find(Address address) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from address " +
                            "where country = ? and city = ? and cp = ? and road = ? and number = ?");
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setInt(3, address.getCp());
            statement.setString(4, address.getRoad());
            statement.setString(5, address.getNumber());

            ResultSet set = statement.executeQuery();

            if (set.first()) {
                return address;
            } else {
                //TODO create custom exception
                System.out.println("ERROR ENTRY ALREADY EXIST");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Address> all() {
        return null;
    }
}
