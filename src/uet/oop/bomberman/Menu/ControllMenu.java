package uet.oop.bomberman.Menu;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;


public class ControllMenu implements Initializable {
    @FXML
    private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setOnMouseClicked(KeyEvent -> {
            BombermanGame.isStartGame = true;
        });
    }
}
