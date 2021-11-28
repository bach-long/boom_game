package uet.oop.bomberman.Collision;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class CollisionChecker {
    public static BombermanGame gp;

    public CollisionChecker(BombermanGame bombermanGame) {
        this.gp = bombermanGame;
    }

    /** kiểm tra va chạm.*/
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

    public void checkCollisionBomb(Bomb entity) {
        int entityLeft = entity.getX() + entity.soliArea.x;
        int entityRight = entity.getX() + entity.soliArea.x + entity.soliArea.width;
        int entityTop = entity.getY() + entity.soliArea.y;
        int entityBottom = entity.getY() + entity.soliArea.y + entity.soliArea.height;

        Entity num1;

        Random randomItem = new Random();
        int valueItem = randomItem.nextInt(4) + 1;

        num1 = gp.tile[entity.getY() / 40][entity.getX() / 40];

        if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
            num1.checkDie = true;
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityTop) / 40 - i >= 0) {
                num1 = gp.tile[(entityTop) / 40 - i][entityLeft / 40];

                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                   num1.checkDie = true;
                } else if (num1 instanceof Brick) {
                    if (valueItem == 1) {
                        Entity object = new BombItem(entityLeft / 40, (entityTop) / 40 - i, Sprite.items[3][1].getFxImage());
                        gp.tile[(entityTop) / 40 - i][entityLeft / 40] = object;
                    } else if (valueItem == 2) {
                        Entity object = new SpeedItem(entityLeft / 40, (entityTop) / 40 - i, Sprite.items[0][1].getFxImage());
                        gp.tile[(entityTop) / 40 - i][entityLeft / 40] = object;
                    } else if (valueItem == 3) {
                        Entity object = new KickItem(entityLeft / 40, (entityTop) / 40 - i, Sprite.items[2][1].getFxImage());
                        gp.tile[(entityTop) / 40 - i][entityLeft / 40] = object;
                    } else {
                        Entity object = new FlameItem(entityLeft / 40, (entityTop) / 40 - i, Sprite.items[1][1].getFxImage());
                        gp.tile[(entityTop) / 40 - i][entityLeft / 40] = object;
                    }
                }
            }
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityBottom) / 40 + i <= 12) {
                num1 = gp.tile[(entityBottom) / 40 + i][entityLeft / 40];

                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                    num1.checkDie = true;
                } else if (num1 instanceof Brick) {
                    if (valueItem == 1) {
                        Entity object = new BombItem(entityLeft / 40, (entityBottom) / 40 + i, Sprite.items[3][1].getFxImage());
                        gp.tile[(entityBottom) / 40 + i][entityLeft / 40] = object;
                    } else if (valueItem == 2) {
                        Entity object = new SpeedItem(entityLeft / 40, (entityBottom) / 40 + i, Sprite.items[0][1].getFxImage());
                        gp.tile[(entityBottom) / 40 + i][entityLeft / 40] = object;
                    } else if (valueItem == 3) {
                        Entity object = new KickItem(entityLeft / 40, (entityBottom) / 40 + i, Sprite.items[2][1].getFxImage());
                        gp.tile[(entityBottom) / 40 + i][entityLeft / 40] = object;
                    } else {
                        Entity object = new FlameItem(entityLeft / 40, (entityBottom) / 40 + i, Sprite.items[1][1].getFxImage());
                        gp.tile[(entityBottom) / 40 + i][entityLeft / 40] = object;
                    }
                }
            }
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityLeft) / 40 - i >= 0) {
                num1 = gp.tile[entityTop / 40][(entityLeft) / 40 - i];

                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                    num1.checkDie = true;
                } else if (num1 instanceof Brick) {
                    if (valueItem == 1) {
                        Entity object = new BombItem((entityLeft) / 40 - i, entityTop / 40, Sprite.items[3][1].getFxImage());
                        gp.tile[entityTop / 40][(entityLeft) / 40 - i] = object;
                    } else if (valueItem == 2) {
                        Entity object = new SpeedItem((entityLeft) / 40 - i, entityTop / 40, Sprite.items[0][1].getFxImage());
                        gp.tile[entityTop / 40][(entityLeft) / 40 - i] = object;
                    } else if (valueItem == 3) {
                        Entity object = new KickItem((entityLeft) / 40 - i, entityTop / 40, Sprite.items[2][1].getFxImage());
                        gp.tile[entityTop / 40][(entityLeft) / 40 - i] = object;
                    } else {
                        Entity object = new FlameItem((entityLeft) / 40 - i, entityTop / 40, Sprite.items[1][1].getFxImage());
                        gp.tile[entityTop / 40][(entityLeft) / 40 - i] = object;
                    }
                }
            }
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityRight) / 40 + i <= 30) {
                num1 = gp.tile[entityTop / 40][(entityRight) / 40 + i];

                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                    num1.checkDie = true;
                } else if (num1 instanceof Brick) {
                    if (valueItem == 1) {
                        Entity object = new BombItem((entityRight) / 40 + i, entityTop / 40, Sprite.items[3][1].getFxImage());
                        gp.tile[entityTop / 40][(entityRight) / 40 + i] = object;
                    } else if (valueItem == 2) {
                        Entity object = new SpeedItem((entityRight) / 40 + i, entityTop / 40, Sprite.items[0][1].getFxImage());
                        gp.tile[entityTop / 40][(entityRight) / 40 + i] = object;
                    } else if (valueItem == 3) {
                        Entity object = new KickItem((entityRight) / 40 + i, entityTop / 40, Sprite.items[2][1].getFxImage());
                        gp.tile[entityTop / 40][(entityRight) / 40 + i] = object;
                    } else {
                        Entity object = new FlameItem((entityRight) / 40 + i, entityTop / 40, Sprite.items[1][1].getFxImage());
                        gp.tile[entityTop / 40][(entityRight) / 40 + i] = object;
                    }
                }
            }
        }
    }
}
