package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends Entity {
    private static final int STARTCOUNTDOWN = 400;
    public  int timeToExplosion = STARTCOUNTDOWN;
    int count = 0;
    public static int range = 1;
    boolean returnBom = true;
    private int indexExplosion = 2;
    boolean wait = true;

    private int sprite = 0;

    private boolean tonTai = true;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(speed == 1) setSpeed(2);
        if (!collision && (BombermanGame.character.posX != posX || BombermanGame.character.posY != posY)
         && BombermanGame.bot[posY][posX] == null) {
            collision = true;
        }
        count++;
        if (count > 17) {
            count = 0;
            sprite++;
            if (sprite >= 3) sprite = 0;
        }
        if (timeToExplosion > 0) {
            if (move) {
                kickBomb();
            }
            timeToExplosion --;
        } else if (timeToExplosion == 0) {
            collision = false;
            tonTai = false;
            CollisionChecker.gp.cChecker.checkCollisionBomb(this);
                BombermanGame.bombSound.playMedia(false);
            timeToExplosion --;
        } else {
            timeToExplosion --;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if (timeToExplosion > 0) {
            setImg(Sprite.boom[0][sprite].getFxImage());
            gc.drawImage(img, x, y);
        } else if (timeToExplosion >= -29){
            if (wait) {
                sprite = 0;
                count = 0;
                wait = false;
            }
            gc.drawImage(Sprite.explosionBomb[8][2 - sprite].getFxImage(), x, y);
            for (int i = 1; i <= getRange(); i++) {
                if (posX + i < 30) {
                    if (!(BombermanGame.tile[posY][posX + i] instanceof Wall || BombermanGame.tile[posY][posX + i] instanceof Brick)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[1][2 - sprite].getFxImage(),
                                    posX * 40 + i * Sprite.SCALED_SIZE, posY * 40);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[5][2 - sprite].getFxImage(),
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
                    if (! (BombermanGame.tile[posY][posX - i] instanceof Wall || BombermanGame.tile[posY][posX - i] instanceof Brick)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[2][2 - sprite].getFxImage(),
                                    posX * 40 - i * Sprite.SCALED_SIZE, posY * 40);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[6][2 - sprite].getFxImage(),
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
                    if (! (BombermanGame.tile[posY - i][posX] instanceof Wall || BombermanGame.tile[posY - i][posX] instanceof Brick)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[0][2 - sprite].getFxImage(),
                                    posX * 40, posY * 40 - i * Sprite.SCALED_SIZE);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[4][2 - sprite].getFxImage(),
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
                if (posY + i < 14) {
                    if (! (BombermanGame.tile[posY + i][posX] instanceof Wall || BombermanGame.tile[posY + i][posX] instanceof Brick)) {
                        if (i != getRange()) {
                            gc.drawImage(Sprite.explosionBomb[3][2 - sprite].getFxImage(),
                                    posX * 40, posY * 40 + i * Sprite.SCALED_SIZE);
                        } else if (i == getRange()) {
                            gc.drawImage(Sprite.explosionBomb[7][2 - sprite].getFxImage(),
                                    posX * 40, posY * 40 + i * Sprite.SCALED_SIZE);
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        } else {
            if(returnBom) {
                BombermanGame.character.maxBoom++;
                returnBom = false;
            }
            BombermanGame.bom[posY][posX] = null;
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

    public void updatePosMap() {
        if ((posX != (x + soliArea.x + soliArea.width/2) / 40 || posY != (y + soliArea.y + soliArea.height/2) / 40)
                && BombermanGame.bom[(y + soliArea.y + soliArea.height/2) / 40][(x + soliArea.x + soliArea.width/2) / 40] == null) {
            BombermanGame.bom[posY][posX] = null;
            BombermanGame.bom[(y + soliArea.y + soliArea.height/2)/40][(x + soliArea.x + soliArea.width/2) / 40] = this;
            posX = (x + soliArea.x + soliArea.width/2) / 40;
            posY = (y + soliArea.y + soliArea.height/2) / 40;
        }
    }
    public boolean getState() {
        return tonTai;
    }

    public void kickBomb() {
        collisionOn = false;
        CollisionChecker.gp.cChecker.checkTile(this);
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    y -= speed;
                    updatePosMap();
                    break;
                case "down":
                    y += speed;
                    updatePosMap();
                    break;
                case "left":
                    x -= speed;
                    updatePosMap();
                    break;
                case "right":
                    x += speed;
                    updatePosMap();
                    break;
            }
        } else {
            setSpeed(0);
        }
    }
}
