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

    private static final int STARTCOUNTDOWN = 10;
    private int timeToExplosion = STARTCOUNTDOWN;

    private int range = 2;

    private int indexExplosion = 2;

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
            CollisionChecker.gp.cChecker.checkCollisionBomb(this);
            timeToExplosion --;
        } else {
            timeToExplosion --;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if (timeToExplosion > 0) {
            gc.drawImage(img, x, y);
        } else if (timeToExplosion >= -120 && timeToExplosion <= 0){
                gc.drawImage(Sprite.boom[0][Math.abs(timeToExplosion) / 50].getFxImage(), x, y);
                System.out.println(posX + " " + posY + " " + BombermanGame.tile[posY][posX]);
                for (int i = 1; i <= getRange(); i++) {
                    if (posX + i < 31) {
                        if (BombermanGame.tile[posY][posX + i] instanceof Grass) {
                            gc.drawImage(Sprite.rightExplosion[0][Math.abs(timeToExplosion) / 50].getFxImage(), posX * 40, posY * 40 + i * Sprite.SCALED_SIZE);
                        } else {
                            break;
                        }
                    }
                }

                for (int i = 1; i <= getRange(); i++) {
                    if (posX - i > 0) {
                        if (BombermanGame.tile[posY][posX - i] instanceof Grass) {
                            gc.drawImage(Sprite.leftExplosion[0][Math.abs(timeToExplosion) / 50].getFxImage(), posX * 40, posY * 40 - i * Sprite.SCALED_SIZE);
                        } else {
                            break;
                        }
                    }
                }

                for (int i = 1; i <= getRange(); i++) {
                    if (posY - i > 0) {
                        if (BombermanGame.tile[posY - i][posX] instanceof Grass) {
                            gc.drawImage(Sprite.upExplosion[0][Math.abs(timeToExplosion) / 50].getFxImage(), posX * 40 - i * Sprite.SCALED_SIZE, posY * 40);
                        } else {
                            break;
                        }
                    }
                }

                for (int i = 1; i <= getRange(); i++) {
                    if (posY + i < 13) {
                        if (BombermanGame.tile[posY + i][posX] instanceof Grass) {
                            gc.drawImage(Sprite.downExplosion[0][Math.abs(timeToExplosion) / 50].getFxImage(), posX * 40 + i * Sprite.SCALED_SIZE, posY * 40);
                        } else {
                            break;
                        }
                    }
                }

        } else {
            ;
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
