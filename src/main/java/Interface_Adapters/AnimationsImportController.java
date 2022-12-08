package Interface_Adapters;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class AnimationsImportController {
    PlayerMovementController playerMovementController;
    public AnimationsImportController(BufferedImage[][] playerAnimations, PlayerMovementController playerMovementController) {
        this.playerMovementController = playerMovementController;

        playerAutoImport(playerAnimations);
    }
    public void playerAutoImport(BufferedImage[][] playerAnimations) {
        playerMovementController.setAnimations(playerAnimations);
    }
}
