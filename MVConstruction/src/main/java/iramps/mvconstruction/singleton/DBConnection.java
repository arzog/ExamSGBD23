package iramps.mvconstruction.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;
	private volatile static DBConnection single;
	//region properties
	private final String url = "jdbc:mysql://localhost:3306/construction?autoReconnect=true&useSSL=false";
	private final String user = "brice";
	private final String pswd = "brice";
	//endregion

	//region constructor
	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pswd);
			System.out.println("Connection initialized");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	//endregion

	public static Connection getInstance() {
		if (single == null) {
			synchronized (Connection.class) {
				if (single == null) {
					single = new DBConnection();
				}
			}
		}
		return connection;
	}
}
