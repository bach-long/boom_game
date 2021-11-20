package uet.oop.bomberman.Collision;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;

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
}
