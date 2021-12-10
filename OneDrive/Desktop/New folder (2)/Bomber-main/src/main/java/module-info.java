module uet.oop.bomberman {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    requires java.desktop;
    requires com.jfoenix;

    opens uet.oop.bomberman to javafx.fxml;
    exports uet.oop.bomberman;
}