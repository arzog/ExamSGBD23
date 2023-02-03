package iramps.mvconstruction.controller;
import iramps.mvconstruction.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class HomeController {

	//region
	private User user;
	@FXML
	private Label welcome;
	//endregion


	public void setUser(User user) {
		StringBuilder stringBuilder = new StringBuilder();

		this.user = user;
		stringBuilder
				.append("Bonjour ")
				.append(user.getFirstname())
				.append(" et bienvenue");

		welcome.setText(stringBuilder.toString());
	}
}
