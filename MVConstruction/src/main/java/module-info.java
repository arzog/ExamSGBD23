module iramps.mvconstruction {
	requires javafx.controls;
	requires javafx.fxml;

	opens iramps.mvconstruction to javafx.fxml;
	exports iramps.mvconstruction;
	exports iramps.mvconstruction.controller;
	opens iramps.mvconstruction.controller to javafx.fxml;
}