package iramps.mvconstruction.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MgmtController extends ScreenController {

	//region
	private Stage window;
	@FXML
	private Button back;
	//endregion

	public void onBackClick() {
		switchScreen("/iramps.mvconstruction/home.fxml", "Accueil", back);
	}
}
