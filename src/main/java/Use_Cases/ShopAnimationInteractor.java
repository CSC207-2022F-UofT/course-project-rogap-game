package Use_Cases;

import java.awt.image.BufferedImage;

public class ShopAnimationInteractor implements ShopAnimationInputBoundary{
    ShopAnimation shopAnimation;

    public ShopAnimationInteractor(ShopAnimation shopAnimation){
        this.shopAnimation = shopAnimation;
    }
    public void setAnimation(BufferedImage[] animation) {
        shopAnimation.setAnimation(animation);
    }
    public BufferedImage[] getAnimation() {
        return shopAnimation.getAnimation();
    }
    public BufferedImage getCurrAnimation() {
        return shopAnimation.getCurrentImage();
    }
}
