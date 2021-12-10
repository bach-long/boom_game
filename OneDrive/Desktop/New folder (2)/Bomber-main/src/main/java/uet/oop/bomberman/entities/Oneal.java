package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Entity {
    int count = 0;
    int sprite = 0;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (checkDie) {
            count++;
            if (count >= 19) {
                count = 0;
                sprite++;
            }
            if (sprite >= 3) {
                BombermanGame.bot[posY][posX] = null;
                if (BombermanGame.le == 1 || BombermanGame.le == 2) {
                    BombermanGame.countBot--;
                    if (BombermanGame.countBot == 0) BombermanGame.winCheck = true;
                }
            }
        } else {
            collisionOn = false;
            CollisionChecker.gp.cChecker.checkTile(this);
            if (collisionOn) {
                switch (direction) {
                    case "up":
                        direction = "down";
                        break;
                    case "down":
                        direction = "up";
                        break;
                    case "right" :
                        direction = "left";
                        break;
                    case "left":
                        direction = "right";
                        break;
                }
            } else {
                switch (direction) {
                    case "up":
                        this.y -= speed;
                        directionReturn();
                        updatePosMap();
                        break;
                    case "down":
                        this.y += speed;
                        directionReturn();
                        updatePosMap();
                        break;
                    case "left":
                        this.x -= speed;
                        directionReturn();
                        updatePosMap();
                        break;
                    case "right":
                        this.x += speed;
                        directionReturn();
                        updatePosMap();
                        break;
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (checkDie) {
            setImg(Sprite.ghost[0][sprite].getFxImage());
        }
        else {
            switch (direction) {
                case "up":
                    setImg(Sprite.crep1[0][0].getFxImage());
                    break;
                case "down":
                    setImg(Sprite.crep1[0][3].getFxImage());
                    break;
                case "left":
                    setImg(Sprite.crep1[0][2].getFxImage());
                    break;
                case "right":
                    setImg(Sprite.crep1[0][1].getFxImage());
                    break;
            }
        }
        super.render(gc);
    }

    public void updatePosMap() {
        if ((posX != (x + soliArea.x + soliArea.width/2) / 40 || posY != (y + soliArea.y + soliArea.height/2) / 40)
                && BombermanGame.bot[(y + soliArea.y + soliArea.height/2) / 40][(x + soliArea.x + soliArea.width/2) / 40] == null) {
            BombermanGame.bot[posY][posX] = null;
            BombermanGame.bot[(y + soliArea.y + soliArea.height/2)/40][(x + soliArea.x + soliArea.width/2) / 40] = this;
            posX = (x + soliArea.x + soliArea.width/2) / 40;
            posY = (y + soliArea.y + soliArea.height/2) / 40;
        }
    }
}
