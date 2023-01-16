module iramps.mvconstruction {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.j;

	opens iramps.mvconstruction to javafx.fxml;
	exports iramps.mvconstruction;
	exports iramps.mvconstruction.controller;
	opens iramps.mvconstruction.controller to javafx.fxml;
}