package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Stack;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    private int frX = 0;
    private int frY = 0;
    private String frDirection;
    protected int x;
    public String direction = "left";
    protected int posX;
    protected int posY;
    private int speed = 1;
    protected Stack<String> spaceGrass = new Stack<>();
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    public boolean collisionOn = false;
    public boolean collision = false;
    public Rectangle soliArea = new Rectangle();


    public void setImg(Image img) {
        this.img = img;
    }

    protected Image img;
    public int sizeSpace() {
        return spaceGrass.size();
    }
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = (int) (yUnit * Sprite.SCALED_SIZE - (img.getHeight() - Sprite.SCALED_SIZE));
        this.img = img;
        soliArea = new Rectangle();
        soliArea.x = 5;
        soliArea.y = (int) (img.getHeight() - 35);
        soliArea.width = 30;
        soliArea.height = 30;
        posX = (this.x + soliArea.x) / 40;
        posY =(this.y + soliArea.y) / 40;
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
    public void set_direction(String s) {
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**Cap nhat vi tri nhan vat.*/
    public void updatePosMap() {
        if ((posX != (x + soliArea.x)/40 || posY != (y + soliArea.y)/40) && BombermanGame.tile[(y + soliArea.y)/40][(x + soliArea.x)/40] instanceof Grass) {
            BombermanGame.tile[posY][posX] = BombermanGame.grass.get(0);
            BombermanGame.tile[(y + soliArea.y)/40][(x + soliArea.x)/40] = this;
                posX = (x + soliArea.x)/40;
                posY = (y + soliArea.y)/40;
                addStack();
        }
    }

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

    public void addStack() {
        switch (direction) {
            case "up":
                if (System.nanoTime()%2 == 0) {
                    checkGrass(posX - 1, posY);
                    checkGrass(posX + 1, posY);
                } else {
                    checkGrass(posX + 1, posY);
                    checkGrass(posX - 1, posY);
                }
                break;
            case "down":
                if (System.nanoTime()%2 == 0) {
                    checkGrass(posX + 1, posY);
                    checkGrass(posX - 1, posY);
                } else {
                    checkGrass(posX - 1, posY);
                    checkGrass(posX + 1, posY);
                }
                break;
            case "left":
                if (System.nanoTime()%2 == 0) {
                    checkGrass(posX, posY + 1);
                    checkGrass(posX, posY - 1);
                } else {
                    checkGrass(posX, posY - 1);
                    checkGrass(posX, posY + 1);
                }
                break;
            case "right":
                if (System.nanoTime()%2 == 0) {
                    checkGrass(posX, posY - 1);
                    checkGrass(posX, posY + 1);
                } else {
                    checkGrass(posX, posY + 1);
                    checkGrass(posX, posY - 1);
                }
                break;
        }
    }

    public void posReturn() {
        String dir;
        if (sizeSpace() > 1) {
            dir = spaceGrass.pop();
            String[] cut = dir.split(",");
            spaceGrass.clear();
            frX = Integer.parseInt(cut[0]) * Sprite.SCALED_SIZE;
            frY = (int) (Integer.parseInt(cut[1]) * Sprite.SCALED_SIZE - (img.getHeight() - Sprite.SCALED_SIZE));
            frDirection = cut[2];
        }
    }
    public boolean checkReturn() {
        if (frX == x && frY == y) {
            direction = frDirection;
            return true;
        }
        return false;
    }
}
