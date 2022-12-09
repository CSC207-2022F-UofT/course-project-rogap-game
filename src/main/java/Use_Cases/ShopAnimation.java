package Use_Cases;

import java.awt.image.BufferedImage;
import Entities.HealthPotion;
public class ShopAnimation {
    private int aniTick, aniIndex, aniSpeed= 10;
    private HealthPotion healthPotion;

    public ShopAnimation(HealthPotion healthPotion){
        this.healthPotion = healthPotion;
    }
    public void setAnimation(BufferedImage[] animation) {
        healthPotion.setAnimation(animation);
    }
    public BufferedImage[] getAnimation() {
        return healthPotion.getAnimation();
    }

    /**
     * Get the current
     * @return
     */
    public BufferedImage getCurrentImage () {
        updateAnimationTick();
        return healthPotion.getAnimation()[aniIndex];
    }

    /**
     * Resets the animation once the last frame in the sprite sheet is reached
     */
    private void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 5) {
                aniIndex = 0;
            }
        }
    }
}
