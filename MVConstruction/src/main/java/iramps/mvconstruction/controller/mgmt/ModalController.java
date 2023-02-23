package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ModalController {

	protected void callModal(String urlFxmlFile, String title, Button anchorPoint) {
		try {
			Stage window;
			window = (Stage) anchorPoint.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader(Main.class.getResource(urlFxmlFile));
			AnchorPane subView = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle(title);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(window);
			dialogStage.setScene(new Scene(subView));

			dialogStage.showAndWait();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
