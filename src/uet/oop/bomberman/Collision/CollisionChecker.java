package uet.oop.bomberman.Collision;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionChecker {
    public static BombermanGame gp;

    public CollisionChecker(BombermanGame bombermanGame) {
        this.gp = bombermanGame;
    }

    public void checkTile(Entity entity) {
        int entityLeft = entity.getX() + entity.soliArea.x;
        int entityRight = entity.getX() + entity.soliArea.x + entity.soliArea.width;
        int entityTop = entity.getY() + entity.soliArea.y;
        int entityBottom = entity.getY() + entity.soliArea.y + entity.soliArea.height;

        int entityLeftCol = entityLeft / 40;
        int entityRightCol = entityRight / 40;
        int entityTopRow = entityTop / 40;
        int entityBottomRow = entityBottom / 40;
        Entity num1, num2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTop - entity.getSpeed()) / 40;
                num1 = gp.tile[entityTopRow][entityLeftCol];
                num2 = gp.tile[entityTopRow][entityRightCol];
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb)  && num2.collision)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottom + entity.getSpeed()) / 40;
                num1 = gp.tile[entityBottomRow][entityLeftCol];
                num2 = gp.tile[entityBottomRow][entityRightCol];
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb)  && num2.collision)) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeft - entity.getSpeed()) / 40;
                num1 = gp.tile[entityTopRow][entityLeftCol];
                num2 = gp.tile[entityBottomRow][entityLeftCol];
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb)  && num2.collision)) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRight + entity.getSpeed()) / 40;
                num1 = gp.tile[entityTopRow][entityRightCol];
                num2 = gp.tile[entityBottomRow][entityRightCol];
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb)  && num2.collision)) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void checkCollisionBomp(Bomb entity) {
        int entityLeft = entity.getX() + entity.soliArea.x;
        int entityRight = entity.getX() + entity.soliArea.x + entity.soliArea.width;
        int entityTop = entity.getY() + entity.soliArea.y;
        int entityBottom = entity.getY() + entity.soliArea.y + entity.soliArea.height;

        Entity num1, num2;
        num1 = gp.tile[(entityTop) / 40 + entity.getRange()][entityLeft / 40];
        num2 = gp.tile[(entityTop) / 40 + entity.getRange()][entityRight / 40];
        if (((num1 instanceof Bomber || num1 instanceof Balloon || num1 instanceof Oneal) && num1.collision)
                || ((num2 instanceof Bomber || num2 instanceof Balloon || num2 instanceof Oneal)  && num2.collision)) {
            Entity object = new Grass(entityLeft / 40, (entityTop) / 40 + entity.getRange(), Sprite.grass[0][1].getFxImage());
            gp.tile[(entityTop) / 40 + entity.getRange()][entityLeft / 40] = object;
        } else if ((num1 instanceof Brick) && num1.collision || (num2 instanceof Brick) && num2.collision) {
            ;
        }

        num1 = gp.tile[(entityBottom) / 40 + entity.getRange()][entityLeft / 40];
        num2 = gp.tile[(entityBottom) / 40 + entity.getRange()][entityRight / 40];
        if (((num1 instanceof Bomber || num1 instanceof Balloon || num1 instanceof Oneal) && num1.collision)
                || ((num2 instanceof Bomber || num2 instanceof Balloon || num2 instanceof Oneal)  && num2.collision)) {
            Entity object = new Grass(entityLeft / 40, (entityBottom) / 40 + entity.getRange(), Sprite.grass[0][1].getFxImage());
            gp.tile[(entityBottom) / 40 + entity.getRange()][entityLeft / 40] = object;
        } else if ((num1 instanceof Brick) && num1.collision || (num2 instanceof Brick) && num2.collision) {
            ;
        }

        num1 = gp.tile[entityTop / 40][(entityLeft) / 40 + entity.getRange()];
        num2 = gp.tile[entityBottom / 40][(entityLeft) / 40 + entity.getRange()];
        if (((num1 instanceof Bomber || num1 instanceof Balloon || num1 instanceof Oneal) && num1.collision)
                || ((num2 instanceof Bomber || num2 instanceof Balloon || num2 instanceof Oneal)  && num2.collision)) {
            Entity object = new Grass((entityLeft) / 40 + entity.getRange(), entityTop / 40, Sprite.grass[0][1].getFxImage());
            gp.tile[entityTop / 40][(entityLeft) / 40 + entity.getRange()] = object;
        } else if ((num1 instanceof Brick) && num1.collision || (num2 instanceof Brick) && num2.collision) {
            ;
        }

        num1 = gp.tile[entityTop / 40][(entityRight) / 40 + entity.getRange()];
        num2 = gp.tile[entityBottom / 40][(entityRight) / 40 + entity.getRange()];
        if (((num1 instanceof Bomber || num1 instanceof Balloon || num1 instanceof Oneal) && num1.collision)
                || ((num2 instanceof Bomber || num2 instanceof Balloon || num2 instanceof Oneal)  && num2.collision)) {
            Entity object = new Grass((entityRight) / 40 + entity.getRange(), entityTop / 40, Sprite.grass[0][1].getFxImage());
            gp.tile[entityTop / 40][(entityRight) / 40 + entity.getRange()] = object;
        } else if ((num1 instanceof Brick) && num1.collision || (num2 instanceof Brick) && num2.collision) {
            ;
        }
    }
}
