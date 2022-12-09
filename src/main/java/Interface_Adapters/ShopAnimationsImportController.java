package Interface_Adapters;

import java.awt.image.BufferedImage;

public class ShopAnimationsImportController {
    ShopAnimationController shopAnimationController;
    /**
     * This constructor passes on all the imported images from the
     * frameworks layer to the controllers
     *@param itemAnimation: contains all the sprites for item animations
     */
    public ShopAnimationsImportController(BufferedImage[] itemAnimation, ShopAnimationController shopAnimationController) {
        this.shopAnimationController = shopAnimationController;
        shopAutoImport(itemAnimation);
    }
    /**
     * Passes a list of all the sprites that are used for item animations
     * @param itemAnimation all the animations of the different items
     */
    public void shopAutoImport(BufferedImage[] itemAnimation) {
        shopAnimationController.setAnimation(itemAnimation);
    }

}
