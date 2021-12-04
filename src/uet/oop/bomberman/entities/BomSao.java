package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

public class BomSao extends Entity {
    int count = 0;
    int sprite = 0;
    public BomSao(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (BombermanGame.bot[posY][posX] != null) {
            collision = false;
        }
        count++;
        if (count > 20) {
            count = 0;
            sprite++;
            if (sprite >= 3) {
                sprite = 0;
            }
        }
        if (checkDie && sprite >= 0) {
            sprite--;
        }
        if (sprite <0) {
            BombermanGame.bom[posY][posX] = null;
        }
        if (move) {
            kickBomb();
        }
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

    public void kickBomb() {
        collisionOn = false;
        CollisionChecker.gp.cChecker.checkTile(this);
        if (!checkDie) {
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
                move = false;
                checkDie = true;
                sprite = 1;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!checkDie) {
            setImg(Sprite.bomSao[0][sprite].getFxImage());
        } else {
            setImg(Sprite.no[0][sprite].getFxImage());
        }
        super.render(gc);
    }
}
