package Interface_Adapters;

import Use_Cases.PlayerAttackInputBoundary;

public class PlayerAttackController {
    PlayerAttackInputBoundary playerAttackInputBoundary;

    public PlayerAttackController(PlayerAttackInputBoundary playerAttackInputBoundary) {
        this.playerAttackInputBoundary = playerAttackInputBoundary;
    }

    public void attack() {
        playerAttackInputBoundary.attack();
    }

}
