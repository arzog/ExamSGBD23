package iramps.mvconstruction;

import iramps.mvconstruction.controller.HomeController;
import iramps.mvconstruction.singleton.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	private Stage primary;
	private BorderPane root;

	@Override
	public void start(Stage stage) {
		this.primary = stage;
		this.primary.setTitle("Connection");
		DBConnection.getInstance();

		initRootLayout();
		showConnection();
	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/root.fxml"));
			root = (BorderPane) loader.load();

			Scene scene = new Scene(root);
			primary.setScene(scene);
			primary.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void showConnection() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/mgmt/connection.fxml"));
			AnchorPane connectionView = loader.load();

			root.setCenter(connectionView);

			HomeController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		launch();
	}
}