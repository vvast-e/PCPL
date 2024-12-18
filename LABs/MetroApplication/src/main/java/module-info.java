module team.astakhov.metromapapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens team.astakhov.metromapapp to javafx.fxml;
    exports team.astakhov.metromapapp;
}