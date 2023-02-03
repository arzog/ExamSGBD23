package iramps.mvconstruction.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
public class SubscriptionController {

	public static final String RED = "-fx-text-box-border: #B22222; -fx-focus-color: #B22222;";
	//region
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
	//endregion


	public void onValidateClick() {
		if (name.getText().equals("")){
			name.setStyle(RED);
		}
		if (firstname.getText().equals("")){
			firstname.setStyle(RED);
		}
		if (username.getText().equals("")){
			username.setStyle(RED);
		}
		if (pswd.getText().equals("")){
			pswd.setStyle(RED);
		}
		if (pswdConfirm.getText().equals("")){
			pswdConfirm.setStyle(RED);
		}
	}

}
