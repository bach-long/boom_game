package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomb extends Entity {
    private int sizeFlame = 1;

    private static final int STARTCOUNTDOWN = 50;
    private int timeToExplosion = STARTCOUNTDOWN;

    private int range = 1;

    private int indexExplosion = 0;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (timeToExplosion > 250) {
            setImg(Sprite.boom[0][0].getFxImage());
            timeToExplosion --;
        } else if (timeToExplosion > 100) {
            setImg(Sprite.boom[0][1].getFxImage());
            timeToExplosion --;
        } else if (timeToExplosion > 0) {
            setImg(Sprite.boom[0][2].getFxImage());
            timeToExplosion --;
        } else if (timeToExplosion == 0) {
            CollisionChecker.gp.cChecker.checkCollisionBomp(this);
            timeToExplosion --;
        } else {
            ;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (timeToExplosion > 0) {
            gc.drawImage(img, x, y);
        } else if (timeToExplosion == 0) {
            indexExplosion++;
            if (indexExplosion == 3) {
                indexExplosion = 0;
            }

            gc.drawImage(Sprite.fontExplosion[0][indexExplosion].getFxImage(), x, y);
            if (BombermanGame.tile[y / 40][x / 40 + 1] instanceof Grass) {
                gc.drawImage(Sprite.rightExplosion[0][indexExplosion].getFxImage(), x, y);
            }
            if (BombermanGame.tile[y / 40][x / 40 ] instanceof Grass) {
                gc.drawImage(Sprite.leftExplosion[0][indexExplosion].getFxImage(), x, y);
            }
            if (BombermanGame.tile[y / 40 ][x / 40] instanceof Grass) {
                gc.drawImage(Sprite.upExplosion[0][indexExplosion].getFxImage(), x, y);
            }
            if (BombermanGame.tile[y / 40 + 1][x / 40] instanceof Grass) {
                gc.drawImage(Sprite.downExplosion[0][indexExplosion].getFxImage(), x, y);
            }
        }
    }

    public int getTimeToExplosion() {
        return timeToExplosion;
    }

    public void setTimeToExplosion(int timeToExplosion) {
        this.timeToExplosion = timeToExplosion;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
