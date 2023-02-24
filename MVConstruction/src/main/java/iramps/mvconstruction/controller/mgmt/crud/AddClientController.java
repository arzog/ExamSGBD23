package iramps.mvconstruction.controller.mgmt.crud;

import iramps.mvconstruction.controller.mgmt.ModalController;
import iramps.mvconstruction.dao.implement.ClientDao;
import iramps.mvconstruction.dao.implement.CompanyDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.Address;
import iramps.mvconstruction.model.Client;
import iramps.mvconstruction.model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddClientController extends ModalController {

	public static final String RED = "-fx-text-box-border: #B22222; -fx-focus-color: #B22222;";
	public static final String GREEN = "-fx-text-box-border: #22b224; -fx-focus-color: #22b224;";

	private ToggleGroup tg = new ToggleGroup();

	@FXML
	private TextField clientName, clientFirstname, clientMail, clientPhone;
	@FXML
	private TextField companyName, companyVat, companyMail, companyPhone;
	@FXML
	private TextField country, city, zipCode, street, number;
	@FXML
	private GridPane client, company, address;
	@FXML
	private RadioButton radioClient, radioCompany;
	@FXML
	private Button back;

	@FXML
	public void initialize() {
		radioCompany.setToggleGroup(tg);
		radioClient.setToggleGroup(tg);

		client.setVisible(false);
		company.setVisible(false);
		address.setVisible(false);
	}

	public void onAddClick() {
		Stage window = (Stage) back.getScene().getWindow();
		if (validateUser()) {
			if (radioClient.isSelected()) {
				ClientDao dao = (ClientDao) DaoFactory.createClientDao();
				dao.create(new Client(
						clientFirstname.getText(),
						clientName.getText(),
						clientMail.getText(),
						clientPhone.getText(),
						new Address(
								country.getText(),
								city.getText(),
								Integer.parseInt(zipCode.getText()),
								street.getText(),
								number.getText()
						),
						true
				));
				window.close();
			} else {
				CompanyDao dao = (CompanyDao) DaoFactory.createCompanyDao();
				dao.create(new Company(
						companyName.getText(),
						companyVat.getText(),
						companyMail.getText(),
						companyPhone.getText(),
						true,
						new Address(
								country.getText(),
								city.getText(),
								Integer.parseInt(zipCode.getText()),
								street.getText(),
								number.getText()
						)
				));
				window.close();
			}
		}
	}

	public void onBackClick() {
		Stage window = (Stage) back.getScene().getWindow();
		window.close();
	}

	public void toggleClientCompany() {
		if (radioClient.isSelected()) {
			company.setVisible(false);
			client.setVisible(true);
		} else if (radioCompany.isSelected()) {
			client.setVisible(false);
			company.setVisible(true);
		}
		address.setVisible(true);
	}

	private boolean validateUser() {

		final AtomicBoolean textOK = new AtomicBoolean(false);

		Arrays.stream(this.getClass().getDeclaredFields())
				.filter(field -> field.getType().getName().contains("TextField"))
				.forEach(field -> {
					try {
						TextField textField = null;

						//determine if is TextField or PasswordField
						if (field.getType().getName().contains("TextField")) {
							textField = (TextField) field.get(this);
							if (textField.getText().equals("")) {
								textField.setStyle(RED);
								textOK.set(false);
							} else {
								if (field.getName().contains("Mail")) {
									if (textField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
										textField.setStyle(GREEN);
										textOK.set(true);
									} else {
										textField.setStyle(RED);
										textOK.set(false);
									}
								} else if (field.getName().contains("zipCode")) {
									if (textField.getText().matches("\\d{4}")) {
										textField.setStyle(GREEN);
										textOK.set(true);
									} else {
										textField.setStyle(RED);
										textOK.set(false);
									}
								} else {
									textField.setStyle(GREEN);
									textOK.set(true);
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
