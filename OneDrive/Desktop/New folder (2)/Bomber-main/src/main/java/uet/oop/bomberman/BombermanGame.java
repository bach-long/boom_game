package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javafx.util.Duration;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.Sound.SoundControl;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BombermanGame implements Initializable {
    public static Button muteSoundButton;
    public static SoundControl backgroundMusicControl = new SoundControl("underwater");
    public static SoundControl bombSound = new SoundControl("explosion");
    public static SoundControl confirm = new SoundControl("confirm");
    public static SoundControl dead = new SoundControl("char_dead");
    public static SoundControl run = new SoundControl("run");
    public static SoundControl buttonPress = new SoundControl("button_press");
    public static SoundControl botDead = new SoundControl("bot_dead");
    public static SoundControl win = new SoundControl("levelcomplete");
    public static SoundControl loss = new SoundControl("levelfail");
    public static String path = "D:\\CODE\\boom_game\\src\\main\\resources\\levels\\level1.txt";
    //public static String path = "D:\\CODE\\boom_game\\src\\main\\resources\\levels\\level2.txt";
    //public static String path = "D:\\CODE\\boom_game\\src\\main\\resources\\levels\\map1.txt";
    public static int le = 1;
    public static final int WIDTH = 17;
    public static final int HEIGHT = 15;
    public static final int SCREEN_FPS = 120;
    public static final int SCREEN_TICKS_PER_FRAME = 1000000000 / SCREEN_FPS;

    public static boolean startGame = true;
    public static boolean winCheck = false;

    private GraphicsContext gc;
    public CollisionChecker cChecker = new CollisionChecker(this);
    //private Canvas canvas;
    public static List<Entity> grass = new ArrayList<>();
    public static Entity[][] tile = new Entity[HEIGHT][WIDTH];
    public static Entity[][] bom = new Entity[HEIGHT][WIDTH];
    public static Entity[][] bot = new Entity[HEIGHT][WIDTH];
    public static Entity[][] Arrayboss = new Entity[HEIGHT][WIDTH];
    public static Boss boss = null;
    public static Bomber character = null;
    boolean creatMap = true;
    public static int countBot = 0;
    public static boolean setPane = true;
    @FXML
    private Canvas canvas;

    @FXML
    private Label labelBoom;

    @FXML
    private Label labelHP;

    @FXML
    private Label labelFlame;

    @FXML
    private ImageView speed;

    @FXML
    private ImageView kickBom;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonPrev;

    @FXML
    private AnchorPane paneWin;

    @FXML
    void prev(MouseEvent event) {
    }
    public boolean checkTrans = true;
    public static int bot_restrict = 0;

    @FXML
    void ReturnLevel(MouseEvent event) throws IOException {
        path = "D:\\CODE\\boom_game\\src\\main\\resources\\levels\\Level2.txt";
        countBot = 0;
        startGame = true;
        winCheck = false;
        clear();
        translateAnimation(0.1, anchorPane, -1100);
        loss.pauseMedia();
        setPane = true;
        backgroundMusicControl.playMedia(true);
        backgroundMusicControl.setVolume(0.2);
        createMap();
    }

    @FXML
    void nextLevel(MouseEvent event) throws FileNotFoundException {
        le++;
        if (le > 3) le = 0;
        clear();
        if (le ==2 ) {
            path = "D:\\CODE\\boom_game\\src\\main\\resources\\levels\\Level2.txt";
        }
        if (le == 3) {
            path = "D:\\CODE\\boom_game\\src\\main\\resources\\levels\\map1.txt";
        }
        countBot = 0;
        startGame = true;
        checkTrans = true;
        winCheck = false;
        translateAnimation(0.5, paneWin, -1100);
        win.pauseMedia();
        backgroundMusicControl.playMedia(true);
        backgroundMusicControl.setVolume(0.2);
        createMap();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!backgroundMusicControl.isRunning()) {
            backgroundMusicControl.playMedia(true);
            backgroundMusicControl.setVolume(0.2);
        }
        gc = canvas.getGraphicsContext2D();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (winCheck) {
                    if (checkTrans) {
                        backgroundMusicControl.pauseMedia();
                        translateAnimation(0.5, paneWin, 1100);
                        win.playMedia(false);
                        checkTrans = false;
                    }
                }
                else if (startGame) {
                    long nextDrawTime = l + SCREEN_TICKS_PER_FRAME;
                    if (le == 3) {
                        if(bot_restrict == 0) {
                            bot[BombermanGame.HEIGHT/2][BombermanGame.WIDTH/2 + 1] = new Balloon(BombermanGame.WIDTH/2 + 1, BombermanGame.HEIGHT/2, Sprite.crep2[0][1].getFxImage());
                            bot[BombermanGame.HEIGHT/2][BombermanGame.WIDTH/2 - 1] = new Balloon(BombermanGame.WIDTH/2 - 1, BombermanGame.HEIGHT/2, Sprite.crep2[0][1].getFxImage());
                            bot[BombermanGame.HEIGHT/2 + 1][BombermanGame.WIDTH/2] = new Balloon(BombermanGame.WIDTH/2, BombermanGame.HEIGHT/2 + 1, Sprite.crep2[0][1].getFxImage());
                            bot[BombermanGame.HEIGHT/2 - 1][BombermanGame.WIDTH/2] = new Balloon(BombermanGame.WIDTH/2, BombermanGame.HEIGHT/2 - 1, Sprite.crep2[0][1].getFxImage());
                        } if(bot_restrict == 1000) {
                            bom[BombermanGame.HEIGHT/2][BombermanGame.WIDTH/2 + 1] = new BomSao(BombermanGame.WIDTH/2 + 1, BombermanGame.HEIGHT/2, Sprite.bomSao[0][0].getFxImage());
                            bom[BombermanGame.HEIGHT/2][BombermanGame.WIDTH/2 - 1] = new BomSao(BombermanGame.WIDTH/2 - 1, BombermanGame.HEIGHT/2, Sprite.bomSao[0][0].getFxImage());
                            bom[BombermanGame.HEIGHT/2 + 1][BombermanGame.WIDTH/2] = new BomSao(BombermanGame.WIDTH/2, BombermanGame.HEIGHT/2 + 1, Sprite.bomSao[0][0].getFxImage());
                            bom[BombermanGame.HEIGHT/2 - 1][BombermanGame.WIDTH/2] = new BomSao(BombermanGame.WIDTH/2, BombermanGame.HEIGHT/2 - 1, Sprite.bomSao[0][0].getFxImage());
                            bom[BombermanGame.HEIGHT/2][BombermanGame.WIDTH/2] = new BomSao(BombermanGame.WIDTH/2, BombermanGame.HEIGHT/2, Sprite.bomSao[0][0].getFxImage());
                        }
                    }
                    bot_restrict = (bot_restrict + 1) % 3000;
                    character.event(canvas);
                    update();
                    render();
                    labelBoom.setText("X" + character.getBOOM());
                    labelFlame.setText("X" + character.getFlame());
                    labelHP.setText("X" + character.getMe());
                    speed.setVisible(character.DELTA != 0);
                    kickBom.setVisible(character.isKickBom());
                    double remainingTime = nextDrawTime - l;
                    remainingTime = remainingTime / 1000000;

                    if (remainingTime < 0) {
                        remainingTime = 0;
                    }
                    try {
                        Thread.sleep((long) remainingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    nextDrawTime += SCREEN_TICKS_PER_FRAME;
                } else {
                    if (setPane) {
                        if (ControllMenu.mute) backgroundMusicControl.pauseMedia();
                        loss.playMedia(false);
                        translateAnimation(0.5, anchorPane, 1100);
                        setPane = false;
                    }
                }
            }
        };
        timer.start();

        if (creatMap) {
            try {
                createMap();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createMap() throws FileNotFoundException {
        //InputStream level = new FileInputStream("D:\\boom_game\\res\\levels\\Level1.txt");
        //InputStream level = new FileInputStream("D:\\boom_game\\res\\levels\\Boss_fight.txt");
        InputStream level = new FileInputStream(path);
        Scanner sc = new Scanner(level).useDelimiter("\\A");
        sc.nextLine();
        int i = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (i >= HEIGHT) {
                continue;
            }
            for (int j = 0; j < s.length(); j++) {
                if (j >= WIDTH) {
                    continue;
                }
                Entity object;
                Entity object2;
                object2 = new Grass(j, i, Sprite.grass[0][0].getFxImage());
                grass.add(object2);
                if (s.charAt(j) == '#') {
                    if (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) {
                        object = new Wall(j, i, Sprite.wall[0][0].getFxImage());
                        tile[i][j] = object;
                    } else if ((i + j) % 5 == 1) {
                        object = new Wall(j, i, Sprite.wall[0][7].getFxImage());
                        tile[i][j] = object;
                    } else {
                        object = new Wall(j, i, Sprite.wall[0][3].getFxImage());
                        tile[i][j] = object;
                    }
                    object.collision = true;
                } else if (s.charAt(j) == 'p') {
                    object = new Bomber(j, i, Sprite.player_1[0][2].getFxImage());
                    tile[i][j] = object;
                    character = (Bomber) object;
                    object.collision = true;
                    character.DELTA = 0;
                } else if (s.charAt(j) == '1') {
                    object = new Balloon(j, i, Sprite.crep2[0][1].getFxImage());
                    bot[i][j] = object;
                    tile[i][j] = grass.get(0);
                    if (le < 3) countBot++;
                } else if (s.charAt(j) == '2') {
                    object = new Oneal(j, i, Sprite.crep1[0][2].getFxImage());
                    bot[i][j] = object;
                    tile[i][j] = grass.get(0);
                    if (le < 3) countBot++;
                } else if (s.charAt(j) == 'B') {
                    object = new Boss(j, i + 2, Sprite.turtle_down[0][2].getFxImage());
                    Arrayboss[i + 2][j] = object;
                    boss = (Boss) object;
                    if (le == 3) countBot++;
                } else if (s.charAt(j) == '*') {
                    if ((i + j) % 2 == 0) {
                        object = new Brick(j, i, Sprite.wall[0][2].getFxImage());
                        tile[i][j] = object;
                    } else {
                        object = new Brick(j, i, Sprite.wall[0][6].getFxImage());
                        tile[i][j] = object;
                    }
                    object.collision = true;
                } else if (s.charAt(j) == 'x') {
                    object = new Portal(j, i, Sprite.wall[0][4].getFxImage());
                    tile[i][j] = object;
                } else if (s.charAt(j) == 'f') {
                    object = new FlameItem(j, i, Sprite.items[2][2].getFxImage());
                    tile[i][j] = object;
                } else if (s.charAt(j) == 'h') {
                    object = new HP(j, i, Sprite.hp[0][0].getFxImage());
                    tile[i][j] = object;
                } else if (s.charAt(j) == 'b') {
                    object = new BombItem(j, i, Sprite.items[1][2].getFxImage());
                    tile[i][j] = object;
                } else if (s.charAt(j) == 'c') {
                    object = new BomSao(j, i, Sprite.bomSao[0][2].getFxImage());
                    bom[i][j] = object;
                    object.collision = true;
                } else if (s.charAt(j) == 's') {
                    object = new SpeedItem(j, i, Sprite.items[0][2].getFxImage());
                    tile[i][j] = object;
                } else if (s.charAt(j) == 'k') {
                    object = new KickItem(j, i, Sprite.items[2][2].getFxImage());
                    tile[i][j] = object;
                } else {
                    object = new Grass(j, i, Sprite.grass[0][1].getFxImage());
                    tile[i][j] = object;
                }
            }
            i++;
        }
    }

    public void update() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (bom[i][j] != null) {
                    bom[i][j].update();
                }
                if (bot[i][j] != null) {
                    bot[i][j].update();
                }
                if (!(/*tile[i][j] instanceof Bomber || */tile[i][j] == null))
                    tile[i][j].update();
                if (Arrayboss[i][j] != null) {
                    Arrayboss[i][j].update();
                    boss.optimize(character);
                }
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grass.forEach(g -> g.render(gc));
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (!(tile[i][j] instanceof Grass || tile[i][j] == null)) {
                    tile[i][j].render(gc);
                }
                if (bom[i][j] != null) {
                    bom[i][j].render(gc);
                }
                if (bot[i][j] != null) {
                    bot[i][j].render(gc);
                }
                if (Arrayboss[i][j] != null) {
                    Arrayboss[i][j].render(gc);
                }
            }
        }
    }
    public void translateAnimation(double duration, Node node, double width) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    public void clear() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (!(tile[i][j] instanceof Grass || tile[i][j] == null)) {
                    tile[i][j]= null;
                }
                if (bom[i][j] != null) {
                    bom[i][j]=null;
                }
                if (bot[i][j] != null) {
                    bot[i][j]= null;
                }
                if (Arrayboss[i][j] != null) {
                    Arrayboss[i][j] = null;
                }
            }
        }
    }

}