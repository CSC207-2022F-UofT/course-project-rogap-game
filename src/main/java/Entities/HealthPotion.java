package Entities;

import java.awt.image.BufferedImage;

public class HealthPotion extends Potion{
    private BufferedImage[] animation;

    public void setAnimation(BufferedImage[] animation) {
        this.animation = animation;
    }
    public BufferedImage[] getAnimation() {
        return this.animation;
    }

}
