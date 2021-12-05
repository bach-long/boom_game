package uet.oop.bomberman.Sound;

import java.io.File;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
        directory = new File("D:\\boom_game\\res\\sound");
        files = directory.listFiles();
        if (files != null) {
            for (File file: files) {
                sounds.put(file.getName().substring(0, file.getName().length() - 4), file);
            }
        }
        this.soundName = soundName;
        media = new Media(sounds.get(soundName).toURI().toString());
        running = true;
    }

    public void playMedia(boolean isInfinite) {
        mediaPlayer = new MediaPlayer(media);
        if (isInfinite) {
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(INDEFINITE); // so luong chu ki  - vô hạn
        } else {
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
        }
        mediaPlayer.play();
    }

    public void pauseMedia() {
        mediaPlayer.pause();
        mediaPlayer.seek(Duration.seconds(0));
    }

    public void setInfinite(boolean isInfinite) {
        if (isInfinite) {
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(INDEFINITE); // so luong chu ki  - vô hạn
        } else {
            mediaPlayer = new MediaPlayer(media);
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
