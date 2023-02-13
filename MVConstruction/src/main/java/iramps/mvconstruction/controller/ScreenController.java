package iramps.mvconstruction.controller;

import iramps.mvconstruction.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController {
	private Stage window;

	protected void switchScreen(String urlFxmlFile, String title, Button anchorPoint) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(urlFxmlFile));
			AnchorPane subView = loader.load();

			window = (Stage) anchorPoint.getScene().getWindow();
			window.setTitle(title);

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
