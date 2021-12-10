package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Entity {
    int count = 0;
    int sprite = 0;
    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        count++;
        if (count >= 19) {
            if (y >= posY * 40 - 8) {
                y--;
            }  else {
                y++;
            }
            count = 0;
            sprite++;
        }
        if (sprite >= 3) {
            sprite = 0;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        setImg(Sprite.items[3][sprite].getFxImage());
        super.render(gc);
    }
}
