package Interface_Adapters;

import Use_Cases.PauseGameInputBoundary;

public class PauseGameController {
    PauseGameInputBoundary gameState;
    GameLoopManager gameManager;
    // Have Game Loop Manager Reference

    public PauseGameController(PauseGameInputBoundary gameState, GameLoopManager gameManager){
        this.gameState = gameState;
        this.gameManager = gameManager;
    }

    public void pause(){
        this.gameState.pause(gameManager);
        gameManager.gameScreenPresenter.update();
    }
}
