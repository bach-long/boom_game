package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class Boss extends Entity {
    boolean rage = false;
    int hp = 70;
    String[] directions = {"up", "left", "down", "right"};
    int index = 0;
    int count = 0;
    int count1 = 0;
    double min = 100000;
    int vel_x = 0;
    int vel_y = 0;
    int randInt = 0;
    int p_x = 0;
    int p_y  = 0;

    double distance_left = -1;
    double distance_right = -1;
    double distance_up = -1;
    double distance_down = -1;
    String random_direction = "up";
    public static Entity[][] tiles = BombermanGame.tile;
    public static Entity[][] bombs = BombermanGame.bom;

    public Boss(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        this.x += vel_x;
        this.y += vel_y;
        updatePosMap();
    }

    public void optimize(Bomber a) {
        /**check va cham player. */
        if (a.getPosX() >= posX && a.getPosX() <= posX + 2 && a.getPosY() <= posY && a.getPosY() >= posY - 1) {
            a.checkDie = true;
        }
        System.out.println(hp);
        rage = (hp <= 80 && hp >= 60) || (hp <= 40 && hp >= 20);
        if(!rage) {
            if(count1 > 500) {
                count1 = 0;
            }
            if (count1 == 0) {
                random_direction = directions[new Random().nextInt(directions.length)];
            }
            if (random_direction.equals("up")) {
                vel_x = 0;
                vel_y = -1;
                setImg(Sprite.boss[0][0].getFxImage());
            } else if (random_direction.equals("down")) {
                vel_x = 0;
                vel_y = 1;
                setImg(Sprite.boss[0][3].getFxImage());
            } else if (random_direction.equals("left")) {
                vel_x = -1;
                vel_y = 0;
                setImg(Sprite.boss[0][2].getFxImage());
            } else if (random_direction.equals("right")) {
                vel_x = 1;
                vel_y = 0;
                setImg(Sprite.boss[0][1].getFxImage());
            }
            count1++;
        } else {
            if (count > 70) {
                count = 0;
            }
            if (count == 0) {
                p_x = a.getPosX();
                p_y = a.getPosY();
                if (posX + 3 < 30) {
                    distance_right = Math.pow(posX + 3 - p_x, 2) + Math.pow(posY - 1 - p_y, 2);
                    if (min > distance_right) {
                        min = distance_right;
                    }
                    //distance[1] = distance_right;
                }
                if (posX - 1 > 0) {
                    distance_left = Math.pow(posX - 1 - p_x, 2) + Math.pow(posY - 1 - p_y, 2);
                    if (min > distance_left) {
                        min = distance_left;
                    }
                    //distance[0] = distance_left;
                }
                if (posY - 1 > 0) {
                    distance_up = Math.pow(posX + 1 - p_x, 2) + Math.pow(posY - 2 - p_y, 2);
                    if (min > distance_up) {
                        min = distance_up;
                    }
                    //distance[2] = distance_up;
                }
                if (posY + 1 < 12) {
                    distance_down = Math.pow(posX + 1 - p_x, 2) + Math.pow(posY - p_y, 2);
                    if (min > distance_down) {
                        min = distance_down;
                    }
                    //distance[3] = distance_down;
                }
                //Arrays.sort(distance);
                if (min == distance_down) {
                    vel_y = 1;
                    vel_x = 0;
                    setImg(Sprite.boss[0][3].getFxImage());
                    //System.out.println(vel_x + " " + vel_y);
                } else if (min == distance_up) {
                    vel_y = -1;
                    vel_x = 0;
                    setImg(Sprite.boss[0][0].getFxImage());
                    //System.out.println(vel_x + " " + vel_y);
                } else if (min == distance_left) {
                    vel_y = 0;
                    vel_x = -1;
                    setImg(Sprite.boss[0][2].getFxImage());
                    //System.out.println(vel_x + " " + vel_y);
                } else if (min == distance_right) {
                    vel_y = 0;
                    vel_x = 1;
                    setImg(Sprite.boss[0][1].getFxImage());
                    //System.out.println(vel_x + " " + vel_y);
                }
            }
            count++;
            min = 1000000;
            distance_left = -1;
            distance_right = -1;
            distance_up = -1;
            distance_down = -1;

        }
        //int[] choice = {-1, 1};
        //(posX + 3 >= 30) || (posX - 1 <= 0) || (posY + 3 >= 12) || (posY - 1 <= 0)
        updatePosMap();
        if ((posX + 3 >= 30 ) && Objects.equals(random_direction, "right")) {
            vel_x = 0;
            vel_y = posY - 1 > 6 ? -1 : 1;
            random_direction = vel_y == -1 ? "up" : "down";
            setImg(vel_y == -1 ? Sprite.boss[0][0].getFxImage() : Sprite.boss[0][3].getFxImage());
        }
        else if ((posX - 1 <= 0 ) && random_direction.equals("left")) {
            vel_x = 0;
            vel_y = posY - 1 > 6 ? -1 : 1;
            random_direction = vel_y == -1 ? "up" : "down";
            setImg(vel_y == -1 ? Sprite.boss[0][0].getFxImage() : Sprite.boss[0][3].getFxImage());
        }
        else if ((posY + 1 >= 12 ) && random_direction.equals("down")) {
            vel_y = 0;
            vel_x = posX + 1 > 15 ? -1 : 1;
            random_direction = vel_x == -1 ? "left" : "right";
            setImg(vel_x == -1 ? Sprite.boss[0][2].getFxImage() : Sprite.boss[0][1].getFxImage());
        }
        else if ((posY - 4 <= 0 ) && random_direction.equals("up")) {
            vel_y = 0;
            vel_x = posX + 1 > 15 ? -1 : 1;
            random_direction = vel_x == -1 ? "left" : "right";
            setImg(vel_x == -1 ? Sprite.boss[0][2].getFxImage() : Sprite.boss[0][1].getFxImage());
        }
        updatePosMap();
        for(int i = posY - 5; i <= posY + 2; i++) {
            for(int j = posX - 2; j <= posX + 4; j++) {
                if(!(i < posY && j < posX) && !(i < posY && j > posX + 2) && !(i > posY && j > posX + 2) && !(i>posY && j < posX)
                        && i > 0 && i < 13 && j > 0 && j < 31) {
                    if (bombs[i][j] instanceof Bomb && !((Bomb) bombs[i][j]).getState()) {
                        hp -= 5;
                        bombs[i][j] = null;
                    }
                }
            }
        }
    }

    public void updatePosMap() {
        if ((posX != (x + soliArea.x + soliArea.width/2) / 40 || posY != (y + soliArea.y + soliArea.height/2) / 40)) {
            BombermanGame.Arrayboss[posY][posX] = null;
            BombermanGame.Arrayboss[(y + soliArea.y + soliArea.height/2)/40][(x + soliArea.x + soliArea.width/2) / 40] = this;
            posX = (x + soliArea.x + soliArea.width/2) / 40;
            posY = (y + soliArea.y + soliArea.height/2) / 40;
        }
    }
}