package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.controller.MgmtController;
import iramps.mvconstruction.dao.implement.UserDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Optional;

public class UsersController extends MgmtController {
	//region
	private String memPswd = "";
	private boolean updating = false;
	private User selectedUser;

	private UserDao dao;
	@FXML
	private Label name, firstname, username, password;
	@FXML
	private Button add, update, delete, refresh;
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> nameColumn, firstNameColumn;
	@FXML
	private CheckBox showPswd;
	@FXML
	private TextField tfName, tfFirstname, tfUsername, tfPswd;

	//endregion
	@FXML
	public void initialize() {

		tfName.setVisible(false);
		tfFirstname.setVisible(false);
		tfUsername.setVisible(false);
		tfPswd.setVisible(false);

		dao = (UserDao) DaoFactory.createUserDao();

		userTable.setItems(FXCollections.observableList(dao.readAll()));
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnamePropertyProperty());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnamePropertyProperty());

		if (!updating) {
			showDetail(null);
		}

		userTable.getSelectionModel().selectedItemProperty().addListener(
				((observableValue, oldValue, newValue) -> {
					showDetail(newValue);
					showPswd.setSelected(false);
					selectedUser = newValue;
				})
		);
	}

	public void onAddClick() {
		if (!updating) {
			callModal("/iramps.mvconstruction/mgmt/crud/addUser.fxml", "Ajouter un utilisateur", add);
		} else {
			User user = extractUser();
			if (user != null) {
				selectedUser = dao.updateById(user);
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
	}

	public void onDeleteClick() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de suppression");
		alert.setHeaderText("Confirmation de suppression");
		alert.setResizable(false);
		alert.setContentText("Etes vous certain de vouloir désactiver cet utilisateur ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			dao.deleteByObject(selectedUser);
			refresh();
		}
	}

	public void onUpdateClick() {
		if (!updating) {
			switchDisplayUpdate(false, true);
		} else {
			switchDisplayUpdate(true, false);
		}
	}

	private void switchDisplayUpdate(final boolean b, final boolean b1) {
		name.setVisible(b);
		firstname.setVisible(b);
		username.setVisible(b);
		password.setVisible(b);

		tfName.setVisible(b1);
		tfFirstname.setVisible(b1);
		tfUsername.setVisible(b1);
		tfPswd.setVisible(b1);

		updating = b1;
	}

	public void refresh() {
		initialize();
		showDetail(selectedUser);
	}

	public void showPswd() {
		if (showPswd.isSelected()) {
			password.setText(memPswd);
			tfPswd.setText(memPswd);
		} else {
			password.setText("*******");
			tfPswd.setText("*******");
		}
	}

	private User extractUser() {

		User user = dao.readByName(selectedUser.getUsername());

		if (user != null) {
			updating = false;
			return new User(
					user.getId(),
					tfFirstname.getText(),
					tfName.getText(),
					tfUsername.getText(),
					memPswd,
					user.getIsActiveProperty()
			);
		} else {
			updating = false;
			return null;
		}
	}

	private void showDetail(User user) {
		if (user != null) {
			name.setText(user.getLastname());
			firstname.setText(user.getFirstname());
			username.setText(user.getUsername());
			password.setText("*******");
			memPswd = user.getPswd();

			tfName.setText(user.getLastname());
			tfFirstname.setText(user.getFirstname());
			tfUsername.setText(user.getUsername());
			tfPswd.setText("*******");
			memPswd = user.getPswd();
		} else {
			name.setText("");
			firstname.setText("");
			username.setText("");
			password.setText("");
		}
	}
}
