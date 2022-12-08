package Interface_Adapters;

import java.awt.image.BufferedImage;

public class ShopAnimationsImportController {
    ShopAnimationController shopAnimationController;
    /**
     * This constructor passes on all the imported images from the
     * frameworks layer to the controllers
     *@param itemAnimations: contains all the sprites for item animations
     */
    public ShopAnimationsImportController(BufferedImage[][] itemAnimations, ShopAnimationController shopAnimationController) {
        this.shopAnimationController = shopAnimationController;
        shopAutoImport(itemAnimations);
    }
    /**
     * Passes a list of all the sprites that are used for item animations
     * @param itemAnimations all the animations of the different items
     */
    public void shopAutoImport(BufferedImage[][] itemAnimations) {
        shopAnimationController.setAnimations(itemAnimations);
    }

}
