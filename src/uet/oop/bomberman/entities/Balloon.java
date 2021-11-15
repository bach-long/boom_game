package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Balloon extends Entity {
    private int speed = 1;
    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        this.x -= speed;
    }
}
