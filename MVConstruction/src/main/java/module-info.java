module iramps.mvconstruction {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.j;

	opens iramps.mvconstruction to javafx.fxml;
	exports iramps.mvconstruction;
	exports iramps.mvconstruction.controller;
	exports iramps.mvconstruction.controller.mgmt;
	exports iramps.mvconstruction.controller.mgmt.crud;
	opens iramps.mvconstruction.controller to javafx.fxml;
	opens iramps.mvconstruction.controller.mgmt to javafx.fxml;
	opens iramps.mvconstruction.controller.mgmt.crud to javafx.fxml;
}