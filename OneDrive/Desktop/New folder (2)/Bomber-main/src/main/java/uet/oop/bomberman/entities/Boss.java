package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.io.File;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class Boss extends Entity {
    boolean rage = false;
    int hp = 100;

    String[] directions = {"up", "left", "down", "right"};
    int index = 0;
    int count = 0;
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    double min = 100000;
    int vel_x = 0;
    int vel_y = 0;
    int randInt = 0;
    int p_x = 0;
    int p_y  = 0;
    int count_rage = 0;

    boolean set_rage = true;
    boolean hit = false;
    int frame = 0;
    int cnt_frame = 0;
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
        if(a.me < 1) {
            a.checkDie = true;
            BombermanGame.startGame = false;
        }
        if (hp > 0) {
            if (x + img.getWidth() >= a.getX() + 40 && x <= a.getX() && y + img.getHeight() >= a.getY() + 40 && y + 40 <= a.getY() + 20 && a.wait == 0) {
                a.me--;
                a.wait = 1;
            } else if (a.wait >= 1) {
                if (a.wait <= 450) {
                    a.setVel_x(0);
                    a.setVel_y(0);
                }
                if (a.wait > 700) {
                    a.wait = 0;
                }
            }
        }
        if (hp > 0) {
            rage = (hp <= 80 && hp >= 60) || (hp <= 40 && hp >= 20);
            if (!rage) {
                count2 = 0;
                count3 = 0;
                count++;
                if (count > 500) {
                    count = 0;
                }
                if (count1 > 15) {
                    count1 = 0;
                    frame++;
                    if (frame >= 3) {
                        frame = 0;
                    }
                }

                if (count == 0) {
                    random_direction = directions[new Random().nextInt(directions.length)];
                }
                if (random_direction.equals("up")) {
                    direction = "up";
                    vel_x = 0;
                    vel_y = -1;
                    //setImg(Sprite.turtle_up[0][(int)frame % 3].getFxImage());
                    //frame++;
                } else if (random_direction.equals("down")) {
                    direction = "down";
                    vel_x = 0;
                    vel_y = 1;
                    //setImg(Sprite.turtle_down[0][(int)frame % 3].getFxImage());
                    //frame++;
                } else if (random_direction.equals("left")) {
                    direction = "left";
                    vel_x = -1;
                    vel_y = 0;
                    //setImg(Sprite.turtle_left[0][(int)frame % 3].getFxImage());
                    //frame++;
                } else if (random_direction.equals("right")) {
                    direction = "right";
                    vel_x = 1;
                    vel_y = 0;
                    //setImg(Sprite.turtle_right[0][(int)frame % 3].getFxImage());
                    //frame++;
                }
                count1++;
            } else {
                count1 = 0;
                count = 0;
                if (!set_rage) {
                    setImg(Sprite.rage1[0][0].getFxImage());
                }
                if (count2 > 100) {
                    count2 = 0;
                }
                count3++;
                if (count3 > 15) {
                    count3 = 0;
                    frame++;
                    if (frame >= 3) {
                        frame = 0;
                    }
                }
                if (count2 == 0) {
                    p_x = a.getPosX();
                    p_y = a.getPosY();
                    if (x + img.getWidth() < (BombermanGame.WIDTH) * 40) {
                        distance_right = Math.pow(posX + 2 - p_x, 2) + Math.pow(posY - 1 - p_y, 2);
                        if (min > distance_right) {
                            min = distance_right;
                        }
                        //distance[1] = distance_right;
                    }
                    if (x > 0) {
                        distance_left = Math.pow(posX - p_x, 2) + Math.pow(posY - 1 - p_y, 2);
                        if (min > distance_left) {
                            min = distance_left;
                        }
                        //distance[0] = distance_left;
                    }
                    if (y > 0) {
                        distance_up = Math.pow(posX + 1 - p_x, 2) + Math.pow(posY - 2 - p_y, 2);
                        if (min > distance_up) {
                            min = distance_up;
                        }
                        //distance[2] = distance_up;
                    }
                    if (y + img.getHeight() < BombermanGame.HEIGHT * 40) {
                        distance_down = Math.pow(posX + 1 - p_x, 2) + Math.pow(posY - p_y, 2);
                        if (min > distance_down) {
                            min = distance_down;
                        }
                        //distance[3] = distance_down;
                    }
                    //Arrays.sort(distance);
                    if (min == distance_down) {
                        if (!direction.equals("down")) {
                            frame = 0;
                        }
                        vel_y = 1;
                        vel_x = 0;
                        //setImg(Sprite.turtle_down[0][frame % 3].getFxImage());
                        direction = "down";
                        //System.out.println(vel_x + " " + vel_y);
                    } else if (min == distance_up) {
                        if (!direction.equals("up")) {
                            frame = 0;
                        }
                        vel_y = -1;
                        vel_x = 0;
                        //setImg(Sprite.turtle_up[0][frame % 3].getFxImage());
                        direction = "up";
                        //System.out.println(vel_x + " " + vel_y);
                    } else if (min == distance_left) {
                        if (!direction.equals("left")) {
                            frame = 0;
                        }
                        vel_y = 0;
                        vel_x = -1;
                        //setImg(Sprite.turtle_left[0][(int)frame % 3].getFxImage());
                        direction = "left";
                        //System.out.println(vel_x + " " + vel_y);
                    } else if (min == distance_right) {
                        if (!direction.equals("right")) {
                            frame = 0;
                        }
                        vel_y = 0;
                        vel_x = 1;
                        //setImg(Sprite.turtle_right[0][(int)frame % 3].getFxImage());
                        direction = "right";
                        //System.out.println(vel_x + " " + vel_y);
                    }
                }
                count2++;
                min = 1000000;
                distance_left = -1;
                distance_right = -1;
                distance_up = -1;
                distance_down = -1;

            }
            //int[] choice = {-1, 1};
            //(posX + 3 >= 30) || (posX - 1 <= 0) || (posY + 3 >= 12) || (posY - 1 <= 0)
            updatePosMap();
            if (x + img.getWidth() - 40>= BombermanGame.WIDTH * 40 && direction.equals("right")) {
                vel_x = 0;
                vel_y = posY - 1 > BombermanGame.HEIGHT / 2 ? -1 : 1;
                random_direction = vel_y == -1 ? "up" : "down";
                direction = random_direction;
                //setImg(vel_y == -1 ? Sprite.turtle_up[0][0].getFxImage() : Sprite.turtle_down[0][0].getFxImage());
                //frame++;
            } else if ((x - 40 <= 0) && direction.equals("left")) {
                vel_x = 0;
                vel_y = posY - 1 > BombermanGame.HEIGHT / 2 ? -1 : 1;
                random_direction = vel_y == -1 ? "up" : "down";
                direction = random_direction;
                //setImg(vel_y == -1 ? Sprite.turtle_up[0][0].getFxImage() : Sprite.turtle_down[0][0].getFxImage());
                //frame++;
            } else if (y + img.getHeight() + 50 >= BombermanGame.HEIGHT * 40 && direction.equals("down")) {
                vel_y = 0;
                vel_x = posX + 1 > BombermanGame.WIDTH / 2 ? -1 : 1;
                random_direction = vel_x == -1 ? "left" : "right";
                direction = random_direction;
                //setImg(vel_x == -1 ? Sprite.turtle_left[0][0].getFxImage() : Sprite.turtle_right[0][0].getFxImage());
                //frame++;
            } else if ((y <= 0) && direction.equals("up")) {
                vel_y = 0;
                vel_x = posX + 1 > BombermanGame.WIDTH / 2 ? -1 : 1;
                random_direction = vel_x == -1 ? "left" : "right";
                direction = random_direction;
                //setImg(vel_x == -1 ? Sprite.turtle_left[0][0].getFxImage() : Sprite.turtle_right[0][0].getFxImage());
                //frame++;
            }
            updatePosMap();
            for (int i = posY - 2 - Bomb.range; i <= posY + Bomb.range; i++) {
                for (int j = posX - Bomb.range; j <= posX + 3 + Bomb.range; j++) {
                    if (i >= 0 && i < 17 && j >=0 && j < 15) {
                        if (!(i < posY - 2 && j < posX) && !(i < posY - 2 && j > posX + 3) && !(i > posY && j > posX + 3) && !(i > posY && j < posX)
                                && i > 0 && i < BombermanGame.HEIGHT && j > 0 && j < BombermanGame.WIDTH) {
                            if (bombs[i][j] instanceof Bomb && !((Bomb) bombs[i][j]).getState()) {
                                hit = true;
                                bombs[i][j] = null;
                                hp -= 5;
                                BombermanGame.character.maxBoom++;
                            } else if ((i == posY || i == posY - 2 || j == posX || j == posX + 3) && bombs[i][j] instanceof BomSao && bombs[i][j].move) {
                                hit = true;
                                bombs[i][j].checkDie = true;
                                hp -= 10;
                                BombermanGame.character.maxBoom++;
                            }
                        }
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

    @Override
    public void render(GraphicsContext gc) {
        if (hp > 0) {
            switch (direction) {
                case "up":
                    if (hit && cnt_frame >= 0 && cnt_frame <= 30) {
                        if (cnt_frame == 10) {
                            BombermanGame.dead.playMedia(false);
                        }
                        setImg(Sprite.Hitup[0][0].getFxImage());
                        cnt_frame++;
                        vel_y = 0;
                        vel_x = 0;
                    } else {
                        setImg(Sprite.turtle_up[0][frame].getFxImage());
                        vel_x = 0;
                        vel_y = -1;
                        cnt_frame = 0;
                        hit = false;
                    }
                    break;
                case "down":
                    if (hit && cnt_frame >= 0 && cnt_frame <= 30) {
                        if (cnt_frame == 10) {
                            BombermanGame.dead.playMedia(false);
                        }
                        setImg(Sprite.Hitdown[0][0].getFxImage());
                        cnt_frame++;
                        vel_y = 0;
                        vel_x = 0;
                    } else {
                        setImg(Sprite.turtle_down[0][frame].getFxImage());
                        vel_y = 1;
                        vel_x = 0;
                        cnt_frame = 0;
                        hit = false;
                    }
                    break;
                case "right":
                    if (hit && cnt_frame >= 0 && cnt_frame <= 30) {
                        if (cnt_frame == 10) {
                            BombermanGame.dead.playMedia(false);
                        }
                        setImg(Sprite.Hitright[0][0].getFxImage());
                        cnt_frame++;
                        vel_x = 0;
                        vel_y = 0;
                    } else {
                        setImg(Sprite.turtle_right[0][frame].getFxImage());
                        vel_y = 0;
                        vel_x = 1;
                        cnt_frame = 0;
                        hit = false;
                    }
                    break;
                case "left":
                    if (hit && cnt_frame >= 0 && cnt_frame <= 30) {
                        if (cnt_frame == 10) {
                            BombermanGame.dead.playMedia(false);
                        }
                        setImg(Sprite.Hitleft[0][0].getFxImage());
                        cnt_frame++;
                        vel_x = 0;
                        vel_y = 0;
                    } else {
                        setImg(Sprite.turtle_left[0][frame].getFxImage());
                        vel_y = 0;
                        vel_x = -1;
                        cnt_frame = 0;
                        hit = false;
                    }
                    break;
            }
        } else {
            setImg(Sprite.turtle_die[0][0].getFxImage());
            BombermanGame.winCheck = true;
            vel_y = 0;
            vel_x = 0;
        }
        super.render(gc);
        if (hp <= 50) {
            gc.setFill(Color.RED);
        } else
            gc.setFill(Color.DEEPSKYBLUE);
        gc.fillRoundRect(x + (img.getWidth() - 150 * hp/100)/2, y - 10, 150 * hp / 100, 15, 10, 10);
    }
}