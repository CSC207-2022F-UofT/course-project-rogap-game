package Interface_Adapters;

import java.awt.image.BufferedImage;
import Use_Cases.ShopAnimationInputBoundary;

public class ShopAnimationController {

    ShopAnimationInputBoundary shopAnimationInputBoundary;

    public ShopAnimationController(ShopAnimationInputBoundary shopAnimationInputBoundary){
        this.shopAnimationInputBoundary = shopAnimationInputBoundary;
    }
    /**
     * @return the current sprite (frame of a sprite sheet) image that is on display
     */
    public BufferedImage getCurrAnimation() {
        return shopAnimationInputBoundary.getCurrAnimation();
    }

    /**
     *  passes in the animation to the interactor
     */
    public void setAnimation(BufferedImage[] animation) {
        shopAnimationInputBoundary.setAnimation(animation);
    }

    /**
     * @return a list of all the sprites (frames of a sprite sheet)
     */
    public BufferedImage[] getAnimation() {
        return shopAnimationInputBoundary.getAnimation();
    }
}
