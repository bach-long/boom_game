package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Collision.CollisionChecker;

import java.awt.*;

public class Oneal extends Entity {
    private int speed = 1;
    private int spriteCounter = 0;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        soliArea = new Rectangle();
        soliArea.x = 2;
        soliArea.y = (int) (img.getHeight() - 38);
        soliArea.width = 36;
        soliArea.height = 36;
        collisionOn = false;
        //direction = "left";
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
                    break;
                case "right":
                    this.x += speed;
                    break;
            }
        }
    }
}
