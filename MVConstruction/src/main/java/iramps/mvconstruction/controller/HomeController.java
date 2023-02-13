package iramps.mvconstruction.controller;

import iramps.mvconstruction.Main;
import iramps.mvconstruction.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

	//region
	private User user;
	private Stage window;
	@FXML
	private Label welcome;
	@FXML
	private Button back, clients, bills, users, articles;
	//endregion

	public void onArticlesClick() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/mgmt/articles.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) back.getScene().getWindow();
			window.setTitle("Articles");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void onBackClick() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/connection.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) back.getScene().getWindow();
			window.setTitle("Connection");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void onBillsClick() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/mgmt/bills.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) back.getScene().getWindow();
			window.setTitle("Connection");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void onClientsClick() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/mgmt/clients.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) back.getScene().getWindow();
			window.setTitle("Connection");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void onUsersClick() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/mgmt/users.fxml"));
			AnchorPane subView = loader.load();

			window = (Stage) back.getScene().getWindow();
			window.setTitle("Connection");

			window.setScene(new Scene(subView));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setUser(User user) {
		StringBuilder stringBuilder = new StringBuilder();

		this.user = user;
		stringBuilder.append("Bonjour ").append(this.user.getFirstname()).append(" et bienvenue");

		welcome.setText(stringBuilder.toString());
	}
}
