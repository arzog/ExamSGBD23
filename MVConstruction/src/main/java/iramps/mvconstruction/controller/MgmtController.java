package iramps.mvconstruction.controller;

import iramps.mvconstruction.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MgmtController {

	//region
	private Stage window;
	@FXML
	private Button back;
	//endregion

	public void onBackClick() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/home.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) back.getScene().getWindow();
			window.setTitle("Connection");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
