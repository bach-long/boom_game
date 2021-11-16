package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    public String direction = "down";
    private int speed = 1;
    public Rectangle soliArea;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    public boolean collisionOn = false;
    public boolean collision = false;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = (int) (yUnit * Sprite.SCALED_SIZE - (img.getHeight() - 40));
        this.img = img;
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

}
