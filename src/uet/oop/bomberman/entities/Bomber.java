package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public int flame = 1;
    double frame = 0;
    public int maxBoom = 1;
    public int DELTASPRITE = 7;
    public int me = 10;

    private int vel_x = 0;
    private int vel_y = 0;

    //protected boolean putBom = false;
    public boolean checkSprite = false;

    public int count = 0;
    public int sprite = 0;

    private boolean kickBom = false;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        collisionOn = false;
        CollisionChecker.gp.cChecker.checkTile(this);
        if (!collisionOn) {
            this.x += vel_x;
            this.y += vel_y;
            if (!collecItem()) {
                updatePosMap();
            }
        } else {
            moveBom();
        }
    }

    public void setVel_x(int vel_x) {
        this.vel_x = vel_x;
    }

    public void setVel_y(int vel_y) {
        this.vel_y = vel_y;
    }

    public void die() {
        checkDie = true;
        setImg(Sprite.diePlayer[4][0].getFxImage());
    }

    public void event(Scene scene) {
        if (checkSprite) count++;
        else {
            count = 0;
        }
        if (count > DELTASPRITE) {
            sprite++;
            count = 0;
        }
        if (sprite >= 6) {
            sprite = 0;
        }
        if (!checkDie) {
            scene.setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case UP:
                        direction = "up";
                        setVel_y(-getSpeed());
                        checkSprite = true;
                        setVel_x(0);
                        break;
                    case DOWN:
                        direction = "down";
                        setVel_y(getSpeed());
                        checkSprite = true;
                        setVel_x(0);
                        break;
                    case LEFT:
                        direction = "left";
                        checkSprite = true;
                        setVel_x(-getSpeed());
                        setVel_y(0);
                        break;
                    case RIGHT:
                        direction = "right";
                        checkSprite = true;
                        setVel_x(getSpeed());
                        setVel_y(0);
                        break;
                    case SPACE:
                        if (maxBoom > 0) {
                            Bomb bomb = new Bomb((x + soliArea.x + soliArea.width / 2) / 40, (y + soliArea.y + soliArea.height / 2)
                                    / 40, Sprite.explosionBomb[3][1].getFxImage());
                            if (BombermanGame.bom[(y + soliArea.y + soliArea.height / 2) / 40][(x + soliArea.x + soliArea.width / 2) / 40] == null) {
                                BombermanGame.bom[(y + soliArea.y + soliArea.height / 2) / 40][(x + soliArea.x + soliArea.width / 2) / 40]
                                        = bomb;
                                bomb.setRange(flame);
                                maxBoom--;
                                if (BombermanGame.run.isRunning()) {
                                    BombermanGame.run.playMedia(false);
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            });
            scene.setOnKeyReleased(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case UP:
                        if (direction.equals("up")) {
                            frame += 1;
                            setVel_y(0);
                            checkSprite = false;
                            sprite++;
                        }
                        break;
                    case DOWN:
                        if (direction.equals("down")) {
                            frame += 1;
                            setVel_y(0);
                            checkSprite = false;
                            sprite++;
                        }
                        break;
                    case RIGHT:
                        if (direction.equals("right")) {
                            frame += 1;
                            setVel_x(0);
                            checkSprite = false;
                            sprite++;
                        }
                        break;
                    case LEFT:
                        if (direction.equals("left")) {
                            frame += 1;
                            setVel_x(0);
                            checkSprite = false;
                            sprite++;
                        }
                        break;
                    default:
                        break;
                }
                keyEvent.consume();
            });
            if ((int) (countDelay + DELTA) != (int) countDelay) {
                update();
            }
            countDelay += DELTA;
        } else {
            setVel_x(0);
            setVel_y(0);
            frame = 0;
            frame += 0.35;
            die();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (checkDie) {
            setImg(Sprite.diePlayer[4][0].getFxImage());
        } else {
            switch (direction) {
                case "up":
                    setImg(Sprite.player_1[0][sprite].getFxImage());
                    break;
                case "down":
                    setImg(Sprite.player_1[3][sprite].getFxImage());
                    break;
                case "right":
                    setImg(Sprite.player_1[1][sprite].getFxImage());
                    break;
                case "left":
                    setImg(Sprite.player_1[2][sprite].getFxImage());
                    break;
            }
        }
        super.render(gc);
    }

    public boolean collecItem() {
        if ((posX != (x + soliArea.x) / 40 || posY != (y + soliArea.y) / 40)
                && (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof KickItem
                || BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof FlameItem
                || BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof BombItem
                || BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof SpeedItem)) {
            /**am thanh.*/
            if (BombermanGame.confirm.isRunning()) {
                BombermanGame.confirm.playMedia(false);
            }

            if (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof FlameItem) {
                if (flame < 4) {
                    flame++;
                }
            } else if (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof KickItem) {
                kickBom = true;
            } else if (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof SpeedItem) {
                DELTA = 1;
                if (DELTASPRITE == 7) {
                    DELTASPRITE--;
                }
            } else if (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof BombItem) {
                maxBoom++;
            } else {

            }
            BombermanGame.tile[posY][posX] = BombermanGame.grass.get(0);
            BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] = this;
            posX = (x + soliArea.x) / 40;
            posY = (y + soliArea.y) / 40;
            return true;
        }
        return false;
    }

    public void updatePosMap() {
        if ((posX != (x + soliArea.x + soliArea.width / 2) / 40 || posY != (y + soliArea.y + soliArea.height / 2) / 40)
                && (BombermanGame.tile[(y + soliArea.y + soliArea.height / 2) / 40][(x + soliArea.x + soliArea.width / 2) / 40] instanceof Grass
                || BombermanGame.tile[(y + soliArea.y + soliArea.height / 2) / 40][(x + soliArea.x + soliArea.width / 2) / 40] == null)) {
            BombermanGame.tile[posY][posX] = null;
            BombermanGame.tile[(y + soliArea.y + soliArea.height / 2) / 40][(x + soliArea.x + soliArea.width / 2) / 40] = this;
            posX = (x + soliArea.x + soliArea.width / 2) / 40;
            posY = (y + soliArea.y + soliArea.height / 2) / 40;
        }
    }

    public void moveBom() {
        switch (direction) {
            case "up":
                if ((BombermanGame.bom[posY - 1][posX] instanceof Bomb || BombermanGame.bom[posY - 1][posX] instanceof BomSao) && kickBom) {
                    BombermanGame.bom[posY - 1][posX].direction = "up";
                    BombermanGame.bom[posY - 1][posX].move = true;
                }
                break;
            case "down":
                if ((BombermanGame.bom[posY + 1][posX] instanceof Bomb || BombermanGame.bom[posY + 1][posX] instanceof BomSao) && kickBom) {
                    BombermanGame.bom[posY + 1][posX].direction = "down";
                    BombermanGame.bom[posY + 1][posX].move = true;
                }
                break;
            case "left":
                if ((BombermanGame.bom[posY][posX - 1] instanceof Bomb || BombermanGame.bom[posY][posX - 1] instanceof BomSao)  && kickBom) {
                    BombermanGame.bom[posY][posX - 1].direction = "left";
                    BombermanGame.bom[posY][posX - 1].move = true;
                }
                break;
            case "right":
                if ((BombermanGame.bom[posY][posX + 1] instanceof Bomb ||BombermanGame.bom[posY][posX + 1] instanceof BomSao)&& kickBom) {
                    BombermanGame.bom[posY][posX + 1].direction = "right";
                    BombermanGame.bom[posY][posX + 1].move = true;
                }
                break;
        }
    }
}