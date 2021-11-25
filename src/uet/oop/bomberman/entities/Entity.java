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
    //Toa do bot den
    private int frX = 0;
    private int frY = 0;

    private String frDirection;
    public String direction = "left";

    //o dang dung
    protected int posX;
    protected int posY;

    // toc do
    private int speed = 1;

    //ô cỏ trống có thể di chuyển
    protected Stack<String> spaceGrass = new Stack<>();

    // check va cham
    public boolean collisionOn = false;
    public boolean collision = false;

    // hộp va chạm
    public Rectangle soliArea = new Rectangle();
    public List<String> dirNew;

    //check die
    boolean checkDie = false;
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
        posX = (this.x + soliArea.x) / 40;
        posY = (this.y + soliArea.y) / 40;
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
     * Cap nhat vi tri nhan vat.
     */

    /**kiểm tra các ô xung quanh tìm các vị trí trống như cỏ.*/
    public void checkGrass(int x, int y) {
        if (BombermanGame.tile[y][x] instanceof Grass || BombermanGame.tile[y][x] instanceof Portal) {
            if (x > posX) {
                spaceGrass.add(posX + "," + y + ",right");
            }
            if (x < posX) {
                spaceGrass.add(posX + "," + y + ",left");
            }
            if (y < posY) {
                spaceGrass.add(x + "," + posY + ",up");
            }
            if (y > posY) {
                spaceGrass.add(x + "," + posY + ",down");
            }
        }
    }

    /**check cỏ của automove thử nghiệm.*/
    public void checkGrass2(int x, int y) {
        if (BombermanGame.tile[y][x] instanceof Grass || BombermanGame.tile[y][x] instanceof Portal) {
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
    /**ham auto move thử nghiệm.*/
    public void directionReturn() {
        if ((posX != (x + soliArea.x) / 40 || posY != (y + soliArea.y) / 40)) {
            dirNew = new ArrayList<>();
        switch (direction) {
            case "up":
                checkGrass2(posX, posY - 1);
                checkGrass2(posX - 1, posY);
                checkGrass2(posX + 1, posY);
                if (dirNew.size() > 1) {
                    int k = (int) (Math.random()*30) % dirNew.size();
                    direction = dirNew.get(k);
                    System.out.println(direction);
                    dirNew.clear();
                }
                break;
            case "down":
                checkGrass2(posX, posY + 1);
                checkGrass2(posX - 1, posY);
                checkGrass2(posX + 1, posY);
                if (dirNew.size() > 1) {
                    int k = (int) (Math.random()*30) % dirNew.size();
                    direction = dirNew.get(k);
                    System.out.println(direction);
                    dirNew.clear();
                }
                break;
            case "right":
                checkGrass2(posX + 1, posY);
                checkGrass2(posX, posY - 1);
                checkGrass2(posX, posY + 1);
                if (dirNew.size() > 1) {
                    int k = (int) ( Math.random()*30) % dirNew.size();
                    direction = dirNew.get(k);
                    dirNew.clear();
                }
                break;
            case "left":
                checkGrass2(posX - 1, posY);
                checkGrass2(posX, posY - 1);
                checkGrass2(posX, posY + 1);
                if (dirNew.size() > 1) {
                    int k = (int) (Math.random()*30) % dirNew.size();
                    direction = dirNew.get(k);
                    dirNew.clear();
                }
                break;
        }
        }
    }

    /**thêm vị trí các ô cỏ có thể đi của quái.*/
    public void addStack() {
        switch (direction) {
            case "up":
                if (System.nanoTime() % 2 == 0) {
                    checkGrass(posX - 1, posY);
                    checkGrass(posX + 1, posY);
                } else {
                    checkGrass(posX + 1, posY);
                    checkGrass(posX - 1, posY);
                }
                break;
            case "down":
                if (System.nanoTime() % 2 == 0) {
                    checkGrass(posX + 1, posY);
                    checkGrass(posX - 1, posY);
                } else {
                    checkGrass(posX - 1, posY);
                    checkGrass(posX + 1, posY);
                }
                break;
            case "left":
                if (System.nanoTime() % 2 == 0) {
                    checkGrass(posX, posY + 1);
                    checkGrass(posX, posY - 1);
                } else {
                    checkGrass(posX, posY - 1);
                    checkGrass(posX, posY + 1);
                }
                break;
            case "right":
                if (System.nanoTime() % 2 == 0) {
                    checkGrass(posX, posY - 1);
                    checkGrass(posX, posY + 1);
                } else {
                    checkGrass(posX, posY + 1);
                    checkGrass(posX, posY - 1);
                }
                break;
        }
    }

    /**vị trí cần đến.*/
    public void posReturn() {
        String dir;
        if (sizeSpace() > 1) {
            int k = random.nextInt(sizeSpace() - 1);
            while (k > 0) {
                spaceGrass.pop();
                k--;
            }
            dir = spaceGrass.pop();
            String[] cut = dir.split(",");
            spaceGrass.clear();
            frX = Integer.parseInt(cut[0]) * Sprite.SCALED_SIZE;
            frY = (int) (Integer.parseInt(cut[1]) * Sprite.SCALED_SIZE - (img.getHeight() - Sprite.SCALED_SIZE));
            frDirection = cut[2];
        }
    }

    /** kiểm tra tọa độ xem vị trí cần đến chưa.*/
    public boolean checkReturn() {
        if (frX == x && frY == y) {
            direction = frDirection;
            return true;
        }
        return false;
    }
}
