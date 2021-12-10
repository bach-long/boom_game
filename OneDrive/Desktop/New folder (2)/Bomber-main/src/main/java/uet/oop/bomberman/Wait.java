package uet.oop.bomberman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Wait implements Initializable {
    static double progress = 0;

    @FXML
    private ProgressBar pbar;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button next;

    @FXML
    void NExt(MouseEvent event) throws IOException {
        ControllMenu.musicStart.pauseMedia();
        Stage stage = (Stage) next.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pbar.setStyle("-fx-accent: #00FF00;");
        pbar.setProgress(-1);
    }
}
