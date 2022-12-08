package Use_Cases;

import java.awt.image.BufferedImage;

public class ShopAnimationInteractor implements ShopAnimationInputBoundary{
    ShopAnimation shopAnimation;

    public ShopAnimationInteractor(ShopAnimation shopAnimation){
        this.shopAnimation = shopAnimation;
    }
    public void setAnimations(BufferedImage[][] animations) {
        shopAnimation.setAnimations(animations);
    }

    public BufferedImage[][] getAnimations() {
        return shopAnimation.getAnimations();
    }
    public BufferedImage getCurrAnimation() {
        return shopAnimation.getCurrentImage();
    }
}
