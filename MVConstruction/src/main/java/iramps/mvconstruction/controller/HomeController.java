package iramps.mvconstruction.controller;

import iramps.mvconstruction.Main;
import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class HomeController {

	//region
	private Main main;
	@FXML
	private TextField username;
	@FXML
	private PasswordField pswd;
	//endregion;

	@FXML
	protected void onConnectClick() {
		Dao<User> userDao = DaoFactory.createUserDao();
		List<User> users = userDao.readAll();
	}

	public void setMainApp(Main main) {
		this.main = main;
	}
}