package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Oneal extends Entity {
    private int speed = 1;
    private int spriteCounter = 0;
    boolean left = false, up = false, right = false, down = false;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        this.x += speed;
    }
}
