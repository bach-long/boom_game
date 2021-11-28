package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public int flame = 2;
    double frame = 0;
    public int maxBoom = 1;
    public double DELTAFRAME = 0.4;
    private int vel_x = 0;
    private int vel_y = 0;
    protected boolean putBom = false;

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
        if (!checkDie) {
            scene.setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case UP:
                        direction = "up";
                        frame += DELTAFRAME;
                        setVel_y(-getSpeed());
                        setVel_x(0);
                        break;
                    case DOWN:
                        direction = "down";
                        frame += DELTAFRAME;
                        setVel_y(getSpeed());
                        setVel_x(0);
                        break;
                    case LEFT:
                        direction = "left";
                        frame += DELTAFRAME;
                        setVel_x(-getSpeed());
                        setVel_y(0);
                        break;
                    case RIGHT:
                        direction = "right";
                        frame += DELTAFRAME;
                        setVel_x(getSpeed());
                        setVel_y(0);
                        break;
                    case SPACE:
                        putBom = true;
                        if (maxBoom > 0) {
                            BombermanGame.bom[(y + soliArea.y + soliArea.height/2) / 40][(x + soliArea.x + soliArea.width/2) / 40]
                                    = new Bomb((x + soliArea.x + soliArea.width/2) / 40, (y + soliArea.y + soliArea.height/2)
                                    / 40,Sprite.explosionBomb[3][1].getFxImage());
                            //maxBoom--;
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
                        }
                        break;
                    case DOWN:
                        if (direction.equals("down")) {
                            frame += 1;
                            setVel_y(0);
                        }
                        break;
                    case RIGHT:
                        if (direction.equals("right")) {
                            frame += 1;
                            setVel_x(0);
                        }
                        break;
                    case LEFT:
                        if (direction.equals("left")) {
                            frame += 1;
                            setVel_x(0);
                        }
                        break;
                    default:
                        break;
                }
                keyEvent.consume();
            });
            if ((int)(countDelay + DELTA) != (int) countDelay) {
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
                    setImg(Sprite.player_1[0][(int) frame % 6].getFxImage());
                    break;
                case "down":
                    setImg(Sprite.player_1[3][(int) frame % 6].getFxImage());
                    break;
                case "right":
                    setImg(Sprite.player_1[1][(int) frame % 6].getFxImage());
                    break;
                case "left":
                    setImg(Sprite.player_1[2][(int) frame % 6].getFxImage());
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
            if (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof FlameItem) {
                flame++;
            } else if (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof KickItem) {

            } else if (BombermanGame.tile[(y + soliArea.y) / 40][(x + soliArea.x) / 40] instanceof SpeedItem) {
                int k = getSpeed() + 1;
                DELTAFRAME = 0.5;
                DELTA = 1;
                //setSpeed(k);
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
        if ((posX != (x + soliArea.x + soliArea.width/2) / 40 || posY != (y + soliArea.y + soliArea.height/2) / 40)
                && BombermanGame.tile[(y + soliArea.y + soliArea.height/2) / 40][(x + soliArea.x + soliArea.width/2) / 40] instanceof Grass) {
            BombermanGame.tile[posY][posX] = BombermanGame.grass.get(0);
            BombermanGame.tile[(y + soliArea.y + soliArea.height/2)/40][(x + soliArea.x + soliArea.width/2) / 40] = this;
            posX = (x + soliArea.x + soliArea.width/2) / 40;
            posY = (y + soliArea.y + soliArea.height/2) / 40;
        }
    }
}