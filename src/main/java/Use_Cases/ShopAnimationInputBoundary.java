package Use_Cases;

import java.awt.image.BufferedImage;

public interface ShopAnimationInputBoundary {
    BufferedImage getCurrAnimation();
    void setAnimation(BufferedImage[] animation);
    BufferedImage[] getAnimation();
}
