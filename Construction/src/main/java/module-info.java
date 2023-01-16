module exam.sgbd.construction {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens exam.sgbd.construction to javafx.fxml;
    exports exam.sgbd.construction;
    exports exam.sgbd.construction.controller;
    opens exam.sgbd.construction.controller to javafx.fxml;
}