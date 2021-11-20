package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Balloon extends Entity {
    private int speed = 1;
    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {
        collisionOn = false;
        CollisionChecker.gp.cChecker.checkTile(this);
        if (collisionOn) {
            posReturn();
            switch (direction) {
                case "up":
                    direction = "down";
                    break;
                case "down":
                    direction = "up";
                    break;
                case "right":
                    direction = "left";
                    break;
                case "left":
                    direction = "right";
                    break;
            }
        }
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    this.y -= speed;
                    setImg(Sprite.crep2[0][0].getFxImage());
                    updatePosMap();
                    checkReturn();
                    break;
                case "down":
                    this.y += speed;
                    setImg(Sprite.crep2[0][3].getFxImage());
                    updatePosMap();
                    checkReturn();
                    break;
                case "left":
                    this.x -= speed;
                    setImg(Sprite.crep2[0][2].getFxImage());
                    updatePosMap();
                    checkReturn();
                    break;
                case "right":
                    this.x += speed;
                    setImg(Sprite.crep2[0][1].getFxImage());
                    updatePosMap();
                    checkReturn();
                    break;
            }
        }
    }
}
