package uet.oop.bomberman.Sound;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.util.Duration;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class SoundControl {
    private File directory;
    private File[] files;

    private Map<String, File> sounds;

    private String soundName = "xmas";

    private boolean running;

    private Media media;
    private MediaPlayer mediaPlayer;

    public SoundControl(String soundName) {
        sounds = new HashMap<>();

<<<<<<< HEAD
        directory = new File("D:\\boom_game\\res\\sound");
=======
        directory = new File("C:\\Users\\nhant\\Desktop\\boom_game\\res\\sound");
>>>>>>> f161253512cc2e76d906bbdbc748a8837d504935

        files = directory.listFiles();

        if (files != null) {
            for (File file: files) {
                sounds.put(file.getName().substring(0, file.getName().length() - 4), file);
            }
        }

        this.soundName = soundName;
        media = new Media(sounds.get(soundName).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        running = true;
    }

    public void playMedia() {
            mediaPlayer.play();
    }

    public void pauseMedia() {
            mediaPlayer.pause();
            mediaPlayer.seek(Duration.seconds(0));
    }

    public void resetMedia() {

    }

    public void setInfinite(boolean isInfinite) {
        if (isInfinite == true) {
            mediaPlayer.setCycleCount(INDEFINITE); // so luong chu ki  -vô hạn
        } else {
            mediaPlayer.setCycleCount(1);
        }
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }  /** gia tri tu 0.0 -> 1.0.*/

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
