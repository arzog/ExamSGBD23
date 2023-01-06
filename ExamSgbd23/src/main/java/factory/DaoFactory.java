package main.java.factory;

import main.java.singleton.ConnectionSingleton;

import java.sql.Connection;

public class DaoFactory {

    protected static final Connection connection = ConnectionSingleton.getConnection();


}
