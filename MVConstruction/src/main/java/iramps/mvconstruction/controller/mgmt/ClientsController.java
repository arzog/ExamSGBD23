package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.Main;
import iramps.mvconstruction.controller.MgmtController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientsController extends MgmtController {
	//region
	private Stage window;
	@FXML
	private Label name, price, stock, min_stock;
	@FXML
	private Button add, update, delete;
	//endregion

	public void onAddClick() {
		//TODO popup add
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/mgmt/crud/addUpdateClient.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) add.getScene().getWindow();
			window.setTitle("Ajouter utilisateur");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void onDeleteClick() {
		//TODO popup vérification suppression
	}

	public void onUpdateClick() {
		//TODO same popup add but pre filled
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/mgmt/crud/addUpdateClient.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) add.getScene().getWindow();
			window.setTitle("Update utilisateur");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
