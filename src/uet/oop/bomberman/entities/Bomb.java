package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Bomb extends Entity {
    private int sizeFlame = 1;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
