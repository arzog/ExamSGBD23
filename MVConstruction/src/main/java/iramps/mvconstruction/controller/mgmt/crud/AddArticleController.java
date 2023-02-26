package iramps.mvconstruction.controller.mgmt.crud;

import iramps.mvconstruction.controller.mgmt.ModalController;
import iramps.mvconstruction.dao.implement.ArticleDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.Article;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddArticleController extends ModalController {
	public static final String RED = "-fx-text-box-border: #B22222; -fx-focus-color: #B22222;";
	public static final String GREEN = "-fx-text-box-border: #22b224; -fx-focus-color: #22b224;";

	@FXML
	private TextField name, price, currentStock, minStock;
	@FXML
	private Button back;

	public void onAddClick() {
		Stage window = (Stage) back.getScene().getWindow();
		if (validateInput()) {
			ArticleDao dao = (ArticleDao) DaoFactory.createArticleDao();

			dao.create(new Article(
					name.getText(),
					Double.parseDouble(price.getText()),
					Integer.parseInt(currentStock.getText()),
					Integer.parseInt(minStock.getText()),
					true
			));
			window.close();
		}
	}

	public void onBackClick() {
		Stage window = (Stage) back.getScene().getWindow();
		window.close();
	}

	private boolean validateInput() {

		final AtomicBoolean textOK = new AtomicBoolean(false);

		Arrays.stream(this.getClass().getDeclaredFields())
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
								textOK.set(false);
							} else {
								if (field.getName().toLowerCase().contains("stock")) {
									try {
										int stock = Integer.parseInt(textField.getText());
										if (stock > 0) {
											textField.setStyle(GREEN);
											textOK.set(true);
										} else {
											textField.setStyle(RED);
											textOK.set(false);
										}
									} catch (NumberFormatException e) {
										textField.setStyle(RED);
										textOK.set(false);
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Entrée invalide");
										alert.setHeaderText("Echec");
										alert.setResizable(false);
										alert.setContentText("Chiffres uniquement pour les stocks");
										alert.showAndWait();
									}
								} else if (field.getName().equalsIgnoreCase("price")) {
									try {
										double priceToCheck = Double.parseDouble(textField.getText());
										if (priceToCheck > 0) {
											textField.setStyle(GREEN);
											textOK.set(true);
										} else {
											textField.setStyle(RED);
											textOK.set(false);
										}
									} catch (NumberFormatException e) {
										textField.setStyle(RED);
										textOK.set(false);
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Entrée invalide");
										alert.setHeaderText("Echec");
										alert.setResizable(false);
										alert.setContentText("Chiffres uniquement pour les stocks");
										alert.showAndWait();
									}
								}
							}
						}
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});
		return textOK.get();
	}
}
