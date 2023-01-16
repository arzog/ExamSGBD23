package exam.sgbd.construction;

import exam.sgbd.construction.singleton.DBConnectionSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Connection conn = null;
        conn = DBConnectionSingleton.getInstance();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement("Select * FROM address");
            ResultSet rs = state.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("country")+"\t"+rs.getString("city")+" "+rs.getString("city"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        launch();
    }
}