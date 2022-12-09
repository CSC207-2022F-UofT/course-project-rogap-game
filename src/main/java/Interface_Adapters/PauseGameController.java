package Interface_Adapters;

import Use_Cases.PauseGameInputBoundary;
import Use_Cases.GameLoopInteractorReference;

public class PauseGameController {
    PauseGameInputBoundary gameState;
    GameLoopInteractorReference gameManagerRef;

    /**
     *
     * @param gameState: This is the input boundary used for this use case
     * @param gameManagerRef: This is a GameLoopManager reference, used to perform this use case
     */
    public PauseGameController(PauseGameInputBoundary gameState, GameLoopInteractorReference gameManagerRef){
        this.gameState = gameState;
        this.gameManagerRef = gameManagerRef;
    }

    public void pause(){
        gameState.pause(gameManagerRef);
    }
}
