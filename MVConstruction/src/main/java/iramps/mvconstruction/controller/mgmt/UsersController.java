package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.controller.MgmtController;
import iramps.mvconstruction.dao.implement.UserDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UsersController extends MgmtController {
	//region
	private String memPswd = "";

	private UserDao dao;
	@FXML
	private Label name, firstname, username, password;
	@FXML
	private Button add, update, delete;
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> nameColumn, firstNameColumn;
	@FXML
	private CheckBox showPswd;

	//endregion
	@FXML
	public void initialize() {
		dao = (UserDao) DaoFactory.createUserDao();

		userTable.setItems(FXCollections.observableList(dao.readAll()));
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnamePropertyProperty());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnamePropertyProperty());

		showDetail(null);

		userTable.getSelectionModel().selectedItemProperty().addListener(
				((observableValue, oldValue, newValue) -> {
					showDetail(newValue);
					showPswd.setSelected(false
					);
				})
		);
	}

	public void onAddClick() {
		//TODO popup add
		switchScreen("/iramps.mvconstruction/mgmt/crud/addUpdateUser.fxml", "Ajouter un utilisateur", add);
	}

	public void onDeleteClick() {
		//TODO popup vérification suppression
	}

	public void onUpdateClick() {
		//TODO same popup add but pre filled
		switchScreen("/iramps.mvconstruction/mgmt/crud/addUpdateUser.fxml", "Mettre un utilisateur à jour", update);
	}

	private void showDetail(User user) {
		if (user != null) {
			name.setText(user.getLastname());
			firstname.setText(user.getFirstnameProperty());
			username.setText(user.getUsername());
			password.setText("*******");
			memPswd = user.getPswd();
		} else {
			name.setText("");
			firstname.setText("");
			username.setText("");
			password.setText("");
		}
	}

	public void showPswd() {
		if (showPswd.isSelected()) {
			password.setText(memPswd);
		} else {
			password.setText("*******");
		}
	}
}
