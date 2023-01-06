module exam.sgbd.construction {
    requires javafx.controls;
    requires javafx.fxml;


    opens exam.sgbd.construction to javafx.fxml;
    exports exam.sgbd.construction;
}