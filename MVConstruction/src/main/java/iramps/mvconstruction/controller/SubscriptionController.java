package iramps.mvconstruction.controller;

import iramps.mvconstruction.dao.implement.UserDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class SubscriptionController extends ScreenController {

	//region
	public static final String RED = "-fx-text-box-border: #B22222; -fx-focus-color: #B22222;";
	public static final String GREEN = "-fx-text-box-border: #22b224; -fx-focus-color: #22b224;";

	private Stage window;

	@FXML
	private TextField name;
	@FXML
	private TextField firstname;
	@FXML
	private TextField username;
	@FXML
	private PasswordField pswd;
	@FXML
	private PasswordField pswdConfirm;
	@FXML
	private Button validate, cancel;

	private UserDao userDao;
	//endregion

	public SubscriptionController() {
		userDao = (UserDao) DaoFactory.createUserDao();
	}

	public void onCancelClick() {
		switchScreen("/iramps.mvconstruction/connection.fxml", "Connection", cancel);
	}

	public void onValidateClick() {
		if (checkFields()) {
			userDao.create(new User(
					firstname.getText(),
					name.getText(),
					username.getText(),
					pswd.getText(),
					true));

			switchScreen("/iramps.mvconstruction/connection.fxml", "Connection", cancel);
		}
	}

	private boolean isPswdSecured(String pswd) {
		return pswd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}:;<>,.?~_+-=|]).{8,18}$");
	}

	private boolean checkFields() {
		final AtomicBoolean textOK = new AtomicBoolean(false);
		final AtomicBoolean pswdOK = new AtomicBoolean(false);

		Arrays.stream(SubscriptionController.class.getDeclaredFields())
				.filter(field -> field.getType().getName().contains("TextField") || field.getType().getName().contains("PasswordField"))
				.forEach(field -> {
					try {
						TextField textField = null;
						PasswordField passwordField = null;

						//determine if is TextField or PasswordField
						if (field.getType().getName().contains("TextField")) {
							textField = (TextField) field.get(this);
							if (textField.getText().equals("")) {
								textField.setStyle(RED);
							} else {
								textField.setStyle(GREEN);
								textOK.set(true);
							}
						} else if (field.getType().getName().contains("PasswordField")) {
							passwordField = (PasswordField) field.get(this);
							if (passwordField.getText().equals("")) {
								passwordField.setStyle(RED);
							} else {
								if (passwordField.getId().equals("pswd") && isPswdSecured(passwordField.getText())) {
									passwordField.setStyle(GREEN);
									pswdOK.set(true);
								} else if (passwordField.getId().equals("pswdConfirm") && passwordField.getText().equals(pswd.getText())) {
									passwordField.setStyle(GREEN);
									pswdOK.set(true);
								} else {
									passwordField.setStyle(RED);
								}
							}
						}
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});
		return pswdOK.get() & textOK.get();
	}
}
