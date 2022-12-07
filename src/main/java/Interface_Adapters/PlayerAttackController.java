package Interface_Adapters;

import Use_Cases.PlayerAttackInputBoundary;

public class PlayerAttackController {
    PlayerAttackInputBoundary playerAttackInputBoundary;
    PlayerMovementController playerMovementController;

    public PlayerAttackController(PlayerAttackInputBoundary playerAttackInputBoundary,
                                  PlayerMovementController playerMovementController) {
        this.playerAttackInputBoundary = playerAttackInputBoundary;
        this.playerMovementController = playerMovementController;
    }

    public void attack() {
        playerAttackInputBoundary.attack();
    }

}
