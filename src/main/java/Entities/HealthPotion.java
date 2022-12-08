package Entities;

import java.awt.image.BufferedImage;

public class HealthPotion extends Potion{
    private BufferedImage[][] animations;

    public void setAnimations(BufferedImage[][] animations) {
        this.animations = animations;
    }
    public BufferedImage[][] getAnimations() {
        return this.animations;
    }

}
