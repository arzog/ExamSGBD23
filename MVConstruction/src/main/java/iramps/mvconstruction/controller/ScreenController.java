package iramps.mvconstruction.controller;

import iramps.mvconstruction.Main;
import iramps.mvconstruction.controller.mgmt.ModalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController extends ModalController {
	protected void switchScreen(String urlFxmlFile, String title, Button anchorPoint) {
		try {
			Stage window;

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
