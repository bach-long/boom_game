package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Collision.CollisionChecker;

import java.awt.*;

public class Oneal extends Entity {
    private int speed = 1;
    private int spriteCounter = 0;
    boolean left = false, up = false, right = false, down = false;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

        soliArea = new Rectangle();
        soliArea.x = 0;
        soliArea.y = (int) (img.getHeight() - 40);
        soliArea.width = 39;
        soliArea.height = 39;
        collisionOn = false;
        CollisionChecker.gp.cChecker.checkTile(this);
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    this.y -= speed;
                    break;
                case "down":
                    this.y += speed;
                    break;
                case "left":
                    this.x -= speed;
                case "right":
                    this.x += speed;
            }
        }
    }
}
