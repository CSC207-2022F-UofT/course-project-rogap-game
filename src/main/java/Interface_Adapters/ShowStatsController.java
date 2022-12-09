package Interface_Adapters;

import Use_Cases.GameLoopInteractorReference;
import Use_Cases.ShowStatsInputBoundary;

/**
 * Controller that toggles the player's stats menu
 */
public class ShowStatsController {

    GameLoopInteractorReference gameManager;
    ShowStatsInputBoundary showStatsInteractor;

    /**
     * @param showStatsInputBoundary: This is the input boundary used for this use case
     * @param gameManager: This is a GameLoopManager reference, used to perform this use case
     */
    public ShowStatsController(GameLoopInteractorReference gameManager, ShowStatsInputBoundary showStatsInputBoundary){
        this.gameManager = gameManager;
        this.showStatsInteractor = showStatsInputBoundary;
    }

    /**
     * Toggles the visibility of the stats menu
     */
    public void showStats(){
        showStatsInteractor.showStats(gameManager);
    }
}
