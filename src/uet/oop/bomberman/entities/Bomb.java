package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.SoundControl;

import java.awt.*;

public class Bomb extends Entity {
    private int sizeFlame = 1;

    private static final int STARTCOUNTDOWN = 200;
    private int timeToExplosion = STARTCOUNTDOWN;

    private int range = 2;

    private int indexExplosion = 2;

    private int sprite = 0;

    private boolean tonTai = true;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (timeToExplosion > 0) {
            sprite++;

            /** lay 29 de ti chia 10 ra so < 3.*/
            if (sprite >= 29) {
                sprite = 0;
            }

            timeToExplosion --;
        } else if (timeToExplosion == 0) {
            tonTai = false;
            CollisionChecker.gp.cChecker.checkCollisionBomb(this);

            if (BombermanGame.bombSound.isRunning()) {
                BombermanGame.bombSound.playMedia();
            }

            timeToExplosion --;
        } else {
            timeToExplosion --;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if (timeToExplosion > 0) {
            setImg(Sprite.boom[0][sprite / 10].getFxImage());
            gc.drawImage(img, x, y);
        } else if (timeToExplosion >= -29 && timeToExplosion <= 0){
            gc.drawImage(Sprite.explosionBomb[8][Math.abs(timeToExplosion) / 10].getFxImage(), x, y);

            for (int i = 1; i <= getRange(); i++) {
                if (posX + i < 30) {
                    if (! (BombermanGame.tile[posY][posX + i] instanceof Wall)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[1][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40 + i * Sprite.SCALED_SIZE, posY * 40);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[5][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40 + i * Sprite.SCALED_SIZE, posY * 40);
                        }
                    } else{
                        break;
                    }
                } else {
                    break;
                }
            }

            for (int i = 1; i <= getRange(); i++) {
                if (posX - i >= 1) {
                    if (! (BombermanGame.tile[posY][posX - i] instanceof Wall)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[2][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40 - i * Sprite.SCALED_SIZE, posY * 40);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[6][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40 - i * Sprite.SCALED_SIZE, posY * 40);
                        }

                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            for (int i = 1; i <= getRange(); i++) {
                if (posY - i >= 1) {
                    if (! (BombermanGame.tile[posY - i][posX] instanceof Wall)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[0][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40, posY * 40 - i * Sprite.SCALED_SIZE);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[4][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40, posY * 40 - i * Sprite.SCALED_SIZE);
                        }

                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            for (int i = 1; i <= getRange(); i++) {
                if (posY + i < 12) {
                    if (! (BombermanGame.tile[posY + i][posX] instanceof Wall)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[3][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40, posY * 40 + i * Sprite.SCALED_SIZE);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[7][Math.abs(timeToExplosion) / 10].getFxImage(),
                                    posX * 40, posY * 40 + i * Sprite.SCALED_SIZE);
                        }

                    } else {
                        break;
                    }
                } else {
                    break;
                }
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
