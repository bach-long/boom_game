package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int x;
    protected int y;
    protected boolean move = false;
    //Toa do bot den
    private int frX = 0;
    private int frY = 0;

    private String frDirection;
    public String direction = "left";

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    //o dang dung
    protected int posX;
    protected int posY;

    // toc do
    protected int speed = 1;

    //ô cỏ trống có thể di chuyển
    protected Stack<String> spaceGrass = new Stack<>();

    // check va cham
    public boolean collisionOn = false;
    public boolean collision = false;

    // hộp va chạm
    public Rectangle soliArea = new Rectangle();
    public List<String> dirNew;

    //check die
    public boolean checkDie = false;

    protected double countDelay = 0;
    protected double DELTA = 0.7;
    Random random = new Random();

    public void setImg(Image img) {
        this.img = img;
    }

    protected Image img;

    public int sizeSpace() {
        return spaceGrass.size();
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = (int) (yUnit * Sprite.SCALED_SIZE - (img.getHeight() - Sprite.SCALED_SIZE));
        this.img = img;
        soliArea = new Rectangle();
        soliArea.x = 5;
        soliArea.y = (int) (img.getHeight() - 35);
        soliArea.width = 30;
        soliArea.height = 30;
        posX = xUnit;
        posY = yUnit;
    }

    public int getSpeed() {
        return speed;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * check cỏ của automove thử nghiệm.
     */
    public void checkGrass2(int x, int y) {
        if (BombermanGame.tile[y][x] instanceof Grass) {
            if (y < posY) {
                dirNew.add("up");
            }
            if (y > posY) {
                dirNew.add("down");
            }
            if (x < posX) {
                dirNew.add("left");
            }
            if (x > posX) {
                dirNew.add("right");
            }
        }
    }

    /**
     * ham auto move thử nghiệm.
     */
    public void directionReturn() {
        if (x == posX*Sprite.SCALED_SIZE && y == posY * Sprite.SCALED_SIZE - (img.getHeight() - Sprite.SCALED_SIZE)) {
            dirNew = new ArrayList<>();
            switch (direction) {
                case "up":
                    checkGrass2(posX, posY - 1);
                    checkGrass2(posX - 1, posY);
                    checkGrass2(posX + 1, posY);
                    if (dirNew.size() > 1) {
                        int k = (int) (Math.random() * 30) % dirNew.size();
                        direction = dirNew.get(k);
                        dirNew.clear();
                    }
                    break;
                case "down":
                    checkGrass2(posX, posY + 1);
                    checkGrass2(posX - 1, posY);
                    checkGrass2(posX + 1, posY);
                    if (dirNew.size() > 1) {
                        int k = (int) (Math.random() * 30) % dirNew.size();
                        direction = dirNew.get(k);
                        dirNew.clear();
                    }
                    break;
                case "right":
                    checkGrass2(posX - 1, posY);
                    checkGrass2(posX + 1, posY);
                    checkGrass2(posX, posY + 1);
                    if (dirNew.size() > 1) {
                        int k = (int) (Math.random() * 30) % dirNew.size();
                        direction = dirNew.get(k);
                        dirNew.clear();
                    }
                    break;
                case "left":
                    checkGrass2(posX + 1, posY);
                    checkGrass2(posX - 1, posY);
                    checkGrass2(posX, posY - 1);
                    if (dirNew.size() > 1) {
                        int k = (int) (Math.random() * 30) % dirNew.size();
                        direction = dirNew.get(k);
                        dirNew.clear();
                    }
                    break;
            }
        }
    }
}
