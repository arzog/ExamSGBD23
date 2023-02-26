package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.controller.MgmtController;
import iramps.mvconstruction.dao.implement.ClientDao;
import iramps.mvconstruction.dao.implement.CompanyDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.Address;
import iramps.mvconstruction.model.Client;
import iramps.mvconstruction.model.Company;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Optional;

public class ClientsController extends MgmtController {
	//region
	private Stage window;
	private boolean updating = false;
	private Client selectedClient;
	private Company selectedCompany;
	private ClientDao clientDao;
	private CompanyDao companyDao;

	private ToggleGroup tg = new ToggleGroup();
	@FXML
	private Label clientLastname, firstname;
	@FXML
	private Label companyLabel, vat;
	@FXML
	private Label phoneClient, mailClient, countryClient, cityClient, streetClient, numberClient, zipCodeClient;
	@FXML
	private TextField tfClientName, tfClientFirstname, tfClientPhone, tfClientMail, tfClientCountry, tfClientCity, tfClientZipCode, tfClientStreet, tfClientNumber;
	@FXML
	private Label phoneCompany, mailCompany, countryCompany, cityCompany, streetCompany, numberCompany, zipCodeCompany;
	@FXML
	private TextField tfComName, tfComVat, tfComPhone, tfComMail, tfComCountry, tfComCity, tfComZipCode, tfComStreet, tfComNumber;
	@FXML
	private Button add, update, delete, refresh;
	@FXML
	private TableView<Client> clientTable;
	@FXML
	private TableView<Company> companyTable;
	@FXML
	private TableColumn<Client, String> clientName, clientFirstname;
	@FXML
	private TableColumn<Company, String> companyName;
	@FXML
	private RadioButton radioClient, radioCompany;
	@FXML
	private TitledPane client, company, type;
	//endregion

	@FXML
	public void initialize() {

		hideTfClient();
		hideTfCompany();

		radioCompany.setToggleGroup(tg);
		radioClient.setToggleGroup(tg);

		clientDao = (ClientDao) DaoFactory.createClientDao();
		companyDao = (CompanyDao) DaoFactory.createCompanyDao();

		clientTable.setItems(FXCollections.observableList(clientDao.readAll()));
		companyTable.setItems(FXCollections.observableList(companyDao.readAll()));
		clientName.setCellValueFactory(cellData -> cellData.getValue().lastnamePropertyProperty());
		clientFirstname.setCellValueFactory(cellData -> cellData.getValue().firstnamePropertyProperty());
		companyName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());

		showClientDetail(null);
		showCompanyDetail(null);

		clientTable.getSelectionModel().selectedItemProperty().addListener(
				((observableValue, oldValue, newValue) -> {
					showCompanyDetail(null);
					showClientDetail(newValue);
					selectedClient = newValue;
				})
		);
		companyTable.getSelectionModel().selectedItemProperty().addListener(
				((observableValue, oldValue, newValue) -> {
					showClientDetail(null);
					showCompanyDetail(newValue);
					selectedCompany = newValue;
				})
		);
	}

	public void onAddClick() {
		if (!updating) {
			callModal("/iramps.mvconstruction/mgmt/crud/addClient.fxml", "Ajouter un client ou une société", add);
		} else {
			if (radioClient.isSelected()) {
				Client toUpdate = extractClient();
				selectedClient = clientDao.updateById(toUpdate);
			} else {
				Company toUpdate = extractCompany();
				selectedCompany = companyDao.updateById(toUpdate);
			}
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("Mise à jour validée");
			success.setHeaderText("Mise à jour validée");
			success.setResizable(false);
			success.setContentText("Entrée mise à jour");
			Optional<ButtonType> result = success.showAndWait();
			if (!result.isPresent() || result.get() == ButtonType.OK) {
				refresh();
			}
		}
	}

	public void onDeleteClick() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de suppression");
		alert.setHeaderText("Confirmation de suppression");
		alert.setResizable(false);
		alert.setContentText("Etes vous certain de vouloir désactiver ce client ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if (radioClient.isSelected()) {
				clientDao.deleteByObject(selectedClient);
			} else {
				companyDao.deleteByObject(selectedCompany);
			}
			refresh();
		}
	}

	public void onUpdateClick() {
		if (!updating) {
			if (radioClient.isSelected()) {
				emptyCompany();
			} else {
				emptyClient();
			}
			tfClientName.setVisible(true);
			tfClientFirstname.setVisible(true);
			tfClientPhone.setVisible(true);
			tfClientMail.setVisible(true);
			tfClientCountry.setVisible(true);
			tfClientCity.setVisible(true);
			tfClientZipCode.setVisible(true);
			tfClientStreet.setVisible(true);
			tfClientNumber.setVisible(true);

			tfComName.setVisible(true);
			tfComVat.setVisible(true);
			tfComPhone.setVisible(true);
			tfComMail.setVisible(true);
			tfComCountry.setVisible(true);
			tfComCity.setVisible(true);
			tfComZipCode.setVisible(true);
			tfComStreet.setVisible(true);
			tfComNumber.setVisible(true);

			clientLastname.setVisible(false);
			firstname.setVisible(false);
			phoneClient.setVisible(false);
			mailClient.setVisible(false);
			countryClient.setVisible(false);
			cityClient.setVisible(false);
			zipCodeClient.setVisible(false);
			streetClient.setVisible(false);
			numberClient.setVisible(false);

			companyLabel.setVisible(false);
			vat.setVisible(false);
			phoneCompany.setVisible(false);
			mailCompany.setVisible(false);
			countryCompany.setVisible(false);
			cityCompany.setVisible(false);
			zipCodeCompany.setVisible(false);
			streetCompany.setVisible(false);
			numberCompany.setVisible(false);

			updating = true;
		} else {
			tfClientName.setVisible(false);
			tfClientFirstname.setVisible(false);
			tfClientPhone.setVisible(false);
			tfClientMail.setVisible(false);
			tfClientCountry.setVisible(false);
			tfClientCity.setVisible(false);
			tfClientZipCode.setVisible(false);
			tfClientStreet.setVisible(false);
			tfClientNumber.setVisible(false);

			tfComName.setVisible(false);
			tfComVat.setVisible(false);
			tfComPhone.setVisible(false);
			tfComMail.setVisible(false);
			tfComCountry.setVisible(false);
			tfComCity.setVisible(false);
			tfComZipCode.setVisible(false);
			tfComStreet.setVisible(false);
			tfComNumber.setVisible(false);

			clientLastname.setVisible(true);
			firstname.setVisible(true);
			phoneClient.setVisible(true);
			mailClient.setVisible(true);
			countryClient.setVisible(true);
			cityClient.setVisible(true);
			zipCodeClient.setVisible(true);
			streetClient.setVisible(true);
			numberClient.setVisible(true);

			companyLabel.setVisible(true);
			vat.setVisible(true);
			phoneCompany.setVisible(true);
			mailCompany.setVisible(true);
			countryCompany.setVisible(true);
			cityCompany.setVisible(true);
			zipCodeCompany.setVisible(true);
			streetCompany.setVisible(true);

			updating = false;
		}
	}

	public void refresh() {
		initialize();
	}

	private void showClientDetail(Client client) {
		if (client != null) {
			clientLastname.setText(client.getLastname());
			firstname.setText(client.getFirstname());
			phoneClient.setText(client.getPhone());
			mailClient.setText(client.getMail());
			countryClient.setText(client.getAddress().getCountry());
			cityClient.setText(client.getAddress().getCity());
			zipCodeClient.setText(String.valueOf(client.getAddress().getZipCode()));
			streetClient.setText(client.getAddress().getStreet());
			numberClient.setText(client.getAddress().getNumber());

			tfClientName.setText(client.getLastname());
			tfClientFirstname.setText(client.getFirstname());
			tfClientPhone.setText(client.getPhone());
			tfClientMail.setText(client.getMail());
			tfClientCountry.setText(client.getAddress().getCountry());
			tfClientCity.setText(client.getAddress().getCity());
			tfClientZipCode.setText(String.valueOf(client.getAddress().getZipCode()));
			tfClientStreet.setText(client.getAddress().getStreet());
			tfClientNumber.setText(client.getAddress().getNumber());

			radioClient.setSelected(true);
		} else {
			clientLastname.setText("");
			firstname.setText("");
			phoneClient.setText("");
			mailClient.setText("");
			countryClient.setText("");
			cityClient.setText("");
			zipCodeClient.setText("");
			streetClient.setText("");
			numberClient.setText("");
			radioClient.setSelected(false);
		}
	}

	private void showCompanyDetail(Company company) {
		if (company != null) {
			companyLabel.setText(company.getName());
			vat.setText(company.getVat());
			phoneCompany.setText(company.getPhone());
			mailCompany.setText(company.getMail());
			countryCompany.setText(company.getAddress().getCountry());
			cityCompany.setText(company.getAddress().getCity());
			zipCodeCompany.setText(String.valueOf(company.getAddress().getZipCode()));
			streetCompany.setText(company.getAddress().getStreet());
			numberCompany.setText(company.getAddress().getNumber());

			tfComName.setText(company.getName());
			tfComVat.setText(company.getVat());
			tfComPhone.setText(company.getPhone());
			tfComMail.setText(company.getMail());
			tfComCountry.setText(company.getAddress().getCountry());
			tfComCity.setText(company.getAddress().getCity());
			tfComZipCode.setText(String.valueOf(company.getAddress().getZipCode()));
			tfComStreet.setText(company.getAddress().getStreet());
			tfComNumber.setText(company.getAddress().getNumber());

			radioCompany.setSelected(true);
		} else {
			companyLabel.setText("");
			vat.setText("");
			phoneCompany.setText("");
			mailCompany.setText("");
			countryCompany.setText("");
			cityCompany.setText("");
			zipCodeCompany.setText("");
			streetCompany.setText("");
			numberCompany.setText("");
			radioCompany.setSelected(false);
		}
	}

	private void hideTfClient() {
		tfClientName.setVisible(false);
		tfClientFirstname.setVisible(false);
		tfClientPhone.setVisible(false);
		tfClientMail.setVisible(false);
		tfClientCountry.setVisible(false);
		tfClientCity.setVisible(false);
		tfClientZipCode.setVisible(false);
		tfClientStreet.setVisible(false);
		tfClientNumber.setVisible(false);
	}

	private void hideTfCompany() {
		tfComName.setVisible(false);
		tfComVat.setVisible(false);
		tfComPhone.setVisible(false);
		tfComMail.setVisible(false);
		tfComCountry.setVisible(false);
		tfComCity.setVisible(false);
		tfComZipCode.setVisible(false);
		tfComStreet.setVisible(false);
		tfComNumber.setVisible(false);
	}

	private void emptyClient() {
		Arrays.stream(this.getClass().getDeclaredFields())
				.filter(field -> field.getType().getName().contains("TextField"))
				.forEach(field -> {
					try {
						if (field.getName().equalsIgnoreCase("tfclient")) {
							((TextField) field.get(this)).setText("");
						}
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});
	}

	private void emptyCompany() {
		Arrays.stream(this.getClass().getDeclaredFields())
				.filter(field -> field.getType().getName().contains("TextField"))
				.forEach(field -> {
					try {
						if (field.getName().equalsIgnoreCase("tfcom")) {
							((TextField) field.get(this)).setText("");
						}
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});
	}

	private Client extractClient() {
		Client existingClient = clientDao.readByName(selectedClient.getLastname(), selectedClient.getFirstname());

		if (existingClient != null) {
			updating = false;
			return new Client(
					existingClient.getId(),
					tfClientFirstname.getText(),
					tfClientName.getText(),
					tfClientMail.getText(),
					tfClientPhone.getText(),
					new Address(
							existingClient.getAddress().getId(),
							tfClientCountry.getText(),
							tfClientCity.getText(),
							Integer.parseInt(tfClientZipCode.getText()),
							tfClientStreet.getText(),
							tfClientNumber.getText()
					),
					true
			);
		} else {
			updating = false;
			return null;
		}
	}

	private Company extractCompany() {
		Company existingCompany = companyDao.readByName(selectedCompany.getName());

		if (existingCompany != null) {
			updating = false;
			return new Company(
					existingCompany.getId(),
					tfComName.getText(),
					tfComVat.getText(),
					tfComMail.getText(),
					tfComPhone.getText(),
					true,
					new Address(
							existingCompany.getAddress().getId(),
							tfComCountry.getText(),
							tfComCity.getText(),
							Integer.parseInt(tfComZipCode.getText()),
							tfComStreet.getText(),
							tfComNumber.getText()
					)
			);
		} else {
			updating = false;
			return null;
		}
	}
}
