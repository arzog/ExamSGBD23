package iramps.mvconstruction.controller;

import iramps.mvconstruction.Main;
import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnectionController extends MgmtController {

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
	@FXML
	private Button subscritpion;
	@FXML
	private Button connect;
	//endregion;

	public void setMainApp(Main main) {
		this.main = main;
	}

	@FXML
	private void onConnectClick() {
		Dao<User> userDao = DaoFactory.createUserDao();
		User user = userDao.readByName(username.getText());

		if (user != null) {
			if (user.getPswd().equals(pswd.getText()) || user.getPswd().equals(visiblePswd.getText())) {
				switchScreen("/iramps.mvconstruction/home.fxml", "Connection", connect);
			}
		}
	}

	@FXML
	private void onQuit() {
		Platform.exit();
	}

	@FXML
	private void onSubscribeClick() {
		switchScreen("/iramps.mvconstruction/inscription.fxml", "Inscription", subscritpion);
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
}