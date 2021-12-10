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

    /**
     * kiểm tra va chạm.
     */
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
                if (gp.bom[entityTopRow][entityLeftCol] instanceof Bomb || gp.bom[entityTopRow][entityLeftCol] instanceof BomSao) {
                    num1 = gp.bom[entityTopRow][entityLeftCol];
                }
                if (gp.bom[entityTopRow][entityRightCol] instanceof Bomb || gp.bom[entityTopRow][entityRightCol] instanceof BomSao) {
                    num2 = gp.bom[entityTopRow][entityRightCol];
                }
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb || num1 instanceof BomSao) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb || num2 instanceof BomSao) && num2.collision)) {
                    if (!(num1 == entity || num2 == entity))
                        entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottom + entity.getSpeed()) / 40;
                num1 = gp.tile[entityBottomRow][entityLeftCol];
                num2 = gp.tile[entityBottomRow][entityRightCol];
                if (gp.bom[entityBottomRow][entityLeftCol] instanceof Bomb || gp.bom[entityBottomRow][entityLeftCol] instanceof BomSao) {
                    num1 = gp.bom[entityBottomRow][entityLeftCol];
                }
                if (gp.bom[entityBottomRow][entityRightCol] instanceof Bomb || gp.bom[entityBottomRow][entityRightCol] instanceof BomSao) {
                    num2 = gp.bom[entityBottomRow][entityRightCol];
                }
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb || num1 instanceof BomSao) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb || num2 instanceof BomSao) && num2.collision)) {
                    if (!(num1 == entity || num2 == entity))
                        entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeft - entity.getSpeed()) / 40;
                num1 = gp.tile[entityTopRow][entityLeftCol];
                num2 = gp.tile[entityBottomRow][entityLeftCol];
                if (gp.bom[entityTopRow][entityLeftCol] instanceof Bomb || gp.bom[entityTopRow][entityLeftCol] instanceof BomSao) {
                    num1 = gp.bom[entityTopRow][entityLeftCol];
                }
                if (gp.bom[entityBottomRow][entityLeftCol] instanceof Bomb || gp.bom[entityBottomRow][entityLeftCol] instanceof BomSao) {
                    num2 = gp.bom[entityBottomRow][entityLeftCol];
                }
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb || num1 instanceof BomSao) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb || num2 instanceof BomSao) && num2.collision)) {
                    if (!(num1 == entity || num2 == entity))
                        entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRight + entity.getSpeed()) / 40;
                num1 = gp.tile[entityTopRow][entityRightCol];
                num2 = gp.tile[entityBottomRow][entityRightCol];
                if (gp.bom[entityTopRow][entityRightCol] instanceof Bomb || gp.bom[entityTopRow][entityRightCol] instanceof BomSao) {
                    num1 = gp.bom[entityTopRow][entityRightCol];
                }
                if (gp.bom[entityBottomRow][entityRightCol] instanceof Bomb || gp.bom[entityBottomRow][entityRightCol] instanceof BomSao) {
                    num2 = gp.bom[entityBottomRow][entityRightCol];
                }
                if (((num1 instanceof Wall || num1 instanceof Brick || num1 instanceof Bomb || num1 instanceof BomSao) && num1.collision)
                        || ((num2 instanceof Wall || num2 instanceof Brick || num2 instanceof Bomb || num2 instanceof BomSao) && num2.collision)) {
                    if (!(num1 == entity || num2 == entity))
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
        if (gp.bot[entity.getY() / 40][entity.getX() / 40] != null) {
            num1 = gp.bot[entity.getY() / 40][entity.getX() / 40];
        }
        if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
            if (num1 instanceof Bomber) {
                deleBomber((Bomber) num1);
            } else {
                num1.checkDie = true;
                BombermanGame.botDead.playMedia(false);
            }
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityTop) / 40 - i >= 0) {
                num1 = gp.tile[(entityTop) / 40 - i][entityLeft / 40];
                if (gp.bot[(entityTop) / 40 - i][entityLeft / 40] != null) {
                    num1 = gp.bot[(entityTop) / 40 - i][entityLeft / 40];
                }
                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                    if (num1 instanceof Bomber) {
                        deleBomber((Bomber) num1);
                    } else {
                        num1.checkDie = true;
                        BombermanGame.botDead.playMedia(false);
                    }
                } else if (num1 instanceof Brick) {
                    randomItem(valueItem, entityLeft / 40, (entityTop) / 40 - i);
                    break;
                }
            }
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityBottom) / 40 + i <= 12) {
                num1 = gp.tile[(entityBottom) / 40 + i][entityLeft / 40];
                if (gp.bot[(entityBottom) / 40 + i][entityLeft / 40] != null) {
                    num1 = gp.bot[(entityBottom) / 40 + i][entityLeft / 40];
                }
                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                    if (num1 instanceof Bomber) {
                        deleBomber((Bomber) num1);
                    } else {
                        BombermanGame.botDead.playMedia(false);
                        num1.checkDie = true;
                    }
                } else if (num1 instanceof Brick) {
                    randomItem(valueItem, entityLeft / 40, (entityBottom) / 40 + i);
                    break;
                }
            }
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityLeft) / 40 - i >= 0) {
                num1 = gp.tile[entityTop / 40][(entityLeft) / 40 - i];
                if (gp.bot[entityTop / 40][(entityLeft) / 40 - i] != null) {
                    num1 = gp.bot[entityTop / 40][(entityLeft) / 40 - i];
                }
                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                    if (num1 instanceof Bomber) {
                        deleBomber((Bomber) num1);
                    } else {
                        num1.checkDie = true;
                        BombermanGame.botDead.playMedia(false);
                    }
                } else if (num1 instanceof Brick) {
                    randomItem(valueItem, entityLeft / 40 - i, entityTop / 40);
                    break;
                }
            }
        }

        for (int i = 1; i <= entity.getRange(); i++) {
            if ((entityRight) / 40 + i <= 30) {
                num1 = gp.tile[entityTop / 40][(entityRight) / 40 + i];
                if (gp.bot[entityTop / 40][(entityRight) / 40 + i] != null) {
                    num1 = gp.bot[entityTop / 40][(entityRight) / 40 + i];
                }
                if (num1 instanceof Wall) {
                    break;
                } else if ((num1 instanceof Bomber && num1.collision) || num1 instanceof Oneal || num1 instanceof Balloon) {
                    if (num1 instanceof Bomber) {
                        deleBomber((Bomber) num1);
                    } else {
                        num1.checkDie = true;
                        BombermanGame.botDead.playMedia(false);
                    }
                } else if (num1 instanceof Brick) {
                    randomItem(valueItem, (entityRight) / 40 + i, entityTop / 40);
                    break;
                }
            }
        }
    }

    public void deleBomber(Bomber num1) {
        if (num1.wait == 0) {
            BombermanGame.dead.playMedia(false);
            if (((Bomber) num1).me > 1) {
                ((Bomber) num1).me--;
                num1.wait = 1;
            }
            else {
                ((Bomber) num1).me--;
                num1.checkDie = true;
                num1.wait = 1;
            }
        } else if (num1.wait >= 1) {
            if (num1.wait <= 450) {
                num1.setVel_x(0);
                num1.setVel_y(0);
            }
            if (num1.wait > 700) {
                num1.wait = 0;
            }
        }
    }

    public void randomItem(int valueItem, int posX, int posY) {
        if (valueItem == 1) {
            int sx = (int) ((Math.random())*10) % 2;
            if (sx == 1) {
                Entity object = new BombItem(posX, posY, Sprite.items[3][1].getFxImage());
                gp.tile[posY][posX] = object;
            } else {
                gp.tile[posY][posX] = null;
            }
        } else if (valueItem == 2) {
            int sx = (int) ((Math.random())*10) % 2;
            if (sx == 1) {
                Entity object = new SpeedItem(posX, posY, Sprite.items[0][1].getFxImage());
                gp.tile[posY][posX] = object;
            } else {
                gp.tile[posY][posX] = null;
            }
        } else if (valueItem == 3) {
            int sx = (int) ((Math.random())*10) % 2;
            if (sx == 1) {
                Entity object = new KickItem(posX, posY, Sprite.items[2][1].getFxImage());
                gp.tile[posY][posX] = object;
            } else {
                gp.tile[posY][posX] = null;
            }
        } else if (valueItem == 4) {
            int sx = (int) ((Math.random())*10) % 2;
            if (sx == 1) {
                Entity object = new HP(posX, posY, Sprite.hp[0][1].getFxImage());
                gp.tile[posY][posX] = object;
            } else {
                gp.tile[posY][posX] = null;
            }
        } else {
            int sx = (int) ((Math.random())*10) % 2;
            if (sx == 1) {
                Entity object = new FlameItem(posX, posY, Sprite.items[1][1].getFxImage());
                gp.tile[posY][posX] = object;
            } else {
                gp.tile[posY][posX] = null;
            }
        }
    }
}
