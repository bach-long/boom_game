package uet.oop.bomberman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import uet.oop.bomberman.Sound.SoundControl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllMenu implements Initializable {
    public static SoundControl musicStart = new SoundControl("menu");
    public static boolean mute = true;
    @FXML
    private JFXDialog dialog;

    @FXML
    private StackPane root;
    @FXML
    private ImageView X;

    @FXML
    void howtoplayButton(MouseEvent event) {
        if(!dialog.isVisible()) dialog.show();
        else dialog.close();
    }
    @FXML
    void Mute(MouseEvent event) {
        if (mute) {
            mute = false;
            X.setVisible(!mute);
            musicStart.pauseMedia();
        } else {
            mute = true;
            X.setVisible(!mute);
            musicStart.playMedia(true);
        }
    }

    @FXML
    void quit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void start(MouseEvent event) throws IOException {
        Stage stage = (Stage) dialog.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("wait.fxml"));
        stage.setScene(new Scene(root));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(mute);
        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(root);
        musicStart.playMedia(true);
    }
}
