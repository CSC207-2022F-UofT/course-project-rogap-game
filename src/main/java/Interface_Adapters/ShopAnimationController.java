package Interface_Adapters;

import java.awt.image.BufferedImage;
import Use_Cases.ShopAnimationInputBoundary;

public class ShopAnimationController {

    ShopAnimationInputBoundary shopAnimationInputBoundary;

    public ShopAnimationController(ShopAnimationInputBoundary shopAnimationInputBoundary){
        this.shopAnimationInputBoundary = shopAnimationInputBoundary;
    }
    /**
     * @return the current sprite image that is on display
     */
    public BufferedImage getCurrAnimation() {
        return shopAnimationInputBoundary.getCurrAnimation();
    }

    /**
     *  passes in the animations to the interactor
     * @param animations: the sprite list for enemy movement
     */
    public void setAnimations(BufferedImage[][] animations) {
        shopAnimationInputBoundary.setAnimations(animations);
    }

    /**
     * @return a list all the animations sprites
     */
    public BufferedImage[][] getAnimations() {
        return shopAnimationInputBoundary.getAnimations();
    }
}
