package iramps.mvconstruction.controller;

import iramps.mvconstruction.Main;
import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnectionController {

	//region
	private Main main;
	@FXML
	private TextField username;
	@FXML
	private TextField visiblePswd;
	@FXML
	private PasswordField pswd;
	@FXML
	private CheckBox pswdBox;
	//endregion;

	@FXML
	private void onConnectClick() {
		Dao<User> userDao = DaoFactory.createUserDao();
		User user = userDao.readByName(username.getText());

		if (user != null) {
			if (user.getPswd().equals(pswd.getText())) {
				System.out.println("Connection");
				System.out.println(user);
			}
		}
	}
	@FXML
	private void onSubscribeClick() {
		System.out.println("call inscription.fxml");

		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/iramps.mvconstruction/inscription.fxml"));
			AnchorPane subView = loader.load();

			Stage subStage = new Stage();
			subStage.setTitle("Inscription");

			Scene scene = new Scene(subView);
			subStage.setScene(scene);

			SubscriptionController controller = loader.getController();
			controller.setStage(subStage);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@FXML
	private void onQuit(){
		Platform.exit();
	}
	@FXML
	private void showPasswd() {
		if (pswdBox.isSelected()) {
			System.out.println("check");
			visiblePswd.setText(pswd.getText());
			pswd.setVisible(false);
			visiblePswd.setVisible(true);
		} else {
			System.out.println("uncheck");
			pswd.setText(visiblePswd.getText());
			pswd.setVisible(true);
			visiblePswd.setVisible(false);
			visiblePswd.setText("");
		}
	}
	public void setMainApp(Main main) {
		this.main = main;
	}
}