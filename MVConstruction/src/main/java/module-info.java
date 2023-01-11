module iramps.mvconstruction {
	requires javafx.controls;
	requires javafx.fxml;

	opens iramps.mvconstruction to javafx.fxml;
	exports iramps.mvconstruction;
}