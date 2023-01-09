package exam.sgbd.construction;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private Stage primary;
    private BorderPane root;

    @Override
    public void start(Stage stage) throws IOException {
        this.primary = stage;

        initRoot();
        showConnection();
    }

    public void initRoot() {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/Root.fxml"));
            root = (BorderPane) loader.load();

            Scene scene = new Scene(root);
            primary.setScene(scene);
            primary.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showConnection() {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/Connection.fxml"));
            root.setCenter((AnchorPane) loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}