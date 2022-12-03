package Interface_Adapters;

import Interface_Adapters.GameLoopManager;
import Use_Cases.MeleeMovement;
import Use_Cases.MovementInputBoundary;
import Use_Cases.PlayerMovement;
import Use_Cases.RangedMovement;

public class MovementController {
    GameLoopManager gameloop;
    PlayerMovement playerMovement;
    MeleeMovement meleeMovement;
    RangedMovement rangedMovement;
    public MovementController(GameLoopManager gameloop, MovementInputBoundary){
        this.gameloop = gameloop;
    }

    public void playerMove() {

    }
    public void rangedEnemyMove() {

    }
    public void meleeEnemyMove() {

    }


}
