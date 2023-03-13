package exam.sgbd.construction.singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConn {
    private String url = "jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false";
    private String user = "root";
    private String passwd = "";
    private static Connection conn;
    private volatile static SqlConn single = new SqlConn();
    private SqlConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Driver ou mdp incorrect");
        }
    }
    public static Connection getInstance(){
        if (single==null){
            synchronized (Connection.class) {
                if (single==null){
                    single = new SqlConn();
                }
            }
        }
        return conn;
    }
}
