package Interface_Adapters;

import Use_Cases.PlayerMovement;

import java.awt.image.BufferedImage;
/**
 * This class passes the imported images into the player controller and enemy controller
 */
public class AnimationsImportController {
    PlayerMovementController playerMovementController;
    CreateEnemyController createEnemyController;
    /**
     * This constructor passes on all the imported images from the
     * frameworks layer to the controllers
     *@param playerAnimations: contains all the sprites for player animations
     */
    public AnimationsImportController(BufferedImage[][] playerAnimations, BufferedImage[][] enemyAnimations, CreateEnemyController createEnemyController,
                                      PlayerMovementController playerMovementController) {
        this.playerMovementController = playerMovementController;
        this.createEnemyController = createEnemyController;
        playerAutoImport(playerAnimations);
        EnemyAutoImport(enemyAnimations);
    }
    /**
     * Passes a list of all the sprites that are used for player animations
     * @param playerAnimations all the animations of player
     */
    public void playerAutoImport(BufferedImage[][] playerAnimations) {
        playerMovementController.setAnimations(playerAnimations);
    }
    public void EnemyAutoImport(BufferedImage[][] enemyanimations) {
        createEnemyController.setAnimations(enemyanimations);
    }
}