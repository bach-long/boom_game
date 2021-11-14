package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int SCREEN_FPS = 60;
    public static final int SCREEN_TICKS_PER_FRAME = 1000000000 / SCREEN_FPS;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        createMap();

        //Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        //entities.add(bomberman);
    }

    public void createMap() throws FileNotFoundException {
        InputStream level = new FileInputStream("D:/BTL2/bomberman-starter/res/levels/Level1.txt");
        Scanner sc = new Scanner(level).useDelimiter("\\A");
        sc.nextLine();
        int i = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                Entity object;
                Entity object2;
                object2 = new Grass(j, i, Sprite.grass[0][1].getFxImage());
                stillObjects.add(object2);
                if (s.charAt(j) == '#') {
                    if (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) {
                        object = new Wall(j, i, Sprite.wall[0][1].getFxImage());
                    } else if ((i + j) % 5 == 1) {
                        object = new Wall(j, i, Sprite.wall[0][6].getFxImage());
                    } else {
                        object = new Wall(j, i, Sprite.wall[0][3].getFxImage());
                    }
                } else if (s.charAt(j) == 'p') {
                    object = new Bomber(j, i, Sprite.player_1[0][2].getFxImage());
                } else if (s.charAt(j) == '1') {
                    object = new Balloon(j, i, Sprite.crep2[0][1].getFxImage());
                } else if (s.charAt(j) == '2') {
                    object = new Oneal(j, i, Sprite.crep1[0][2].getFxImage());
                } else if (s.charAt(j) == '*') {
                    object = new Brick(j, i, Sprite.wall[0][2].getFxImage());
                } else if (s.charAt(j) == 'x') {
                    object = new Portal(j, i, Sprite.wall[0][4].getFxImage());
                } else if (s.charAt(j) == 'f') {
                    object = new FlameItem(j, i, Sprite.items[2][2].getFxImage());
                } else if (s.charAt(j) == 'b') {
                    object = new BombItem(j, i, Sprite.items[1][2].getFxImage());
                } else if (s.charAt(j) == 's') {
                    object = new BombItem(j, i, Sprite.items[0][2].getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass[0][1].getFxImage());
                }
                object2 = new Grass(j, i, Sprite.grass[0][1].getFxImage());
                stillObjects.add(object);
            }
            i++;
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
/**
 * Mô tả cấu trúc tệp cấu hình màn chơi:
 * 1/ Dòng đầu tiên bao gồm 3 số nguyên L, R, C:
 * L - số thứ tự màn chơi
 * R - số hàng của bản đồ
 * C - số cột của bản đồ
 * <p>
 * 2/ R dòng tiếp theo, mỗi dòng có C kí tự. Mỗi kí tự đại diện cho một đối tượng trên bản đồ:
 * Tiles:
 * # - Wall
 * - Brick
 * x - Portal
 * <p>
 * Character:
 * p - Bomber
 * 1 - Balloon
 * 2 - Oneal
 * <p>
 * Items:
 * b - Bomb Item
 * f - Flame Item
 * s - Speed Item
 * <p>
 * Kí tự khác các kí tự trên - Grass
 * <p>
 * .
 */