package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomber<collisionOn> extends Entity {
    int frame = 0;
    private int vel_x = 0;
    private int vel_y = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void set_direction(String s) {
        direction = s;
    }

    @Override
    public void update() {
        collisionOn = false;
        CollisionChecker.gp.cChecker.checkTile(this);
        if (!collisionOn) {
            this.x += vel_x;
            this.y += vel_y;
            updatePosMap();
        } else {
            String dir;
            if (sizeSpace() > 1) {
                dir = spaceGrass.pop();
                System.out.println(dir);
                spaceGrass.clear();
            }
        }
    }

    public void setVel_x(int vel_x) {
        this.vel_x = vel_x;
    }

    public void setVel_y(int vel_y) {
        this.vel_y = vel_y;
    }

    public void event(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        direction = "up";
                        // player_1[0][frame]
                        setImg(Sprite.player_1[0][0].getFxImage());
                        setVel_y(-getSpeed());
                        setVel_x(0);
                        break;
                    case DOWN:
                        direction = "down";
                        setImg(Sprite.player_1[3][0].getFxImage());
                        setVel_y(getSpeed());
                        setVel_x(0);
                        break;
                    case LEFT:
                        direction = "left";
                        setImg(Sprite.player_1[2][0].getFxImage());
                        setVel_x(-getSpeed());
                        setVel_y(0);
                        break;
                    case RIGHT:
                        direction = "right";
                        setImg(Sprite.player_1[1][0].getFxImage());
                        setVel_x(getSpeed());
                        setVel_y(0);
                        break;
                    default:
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        setVel_y(0);
                        break;
                    case DOWN:
                        setVel_y(0);
                        break;
                    case RIGHT:
                        setVel_x(0);
                        break;
                    case LEFT:
                        setVel_x(0);
                        break;
                    default:
                        break;
                }
                //update();
            }
        });
        update();
    }
}
