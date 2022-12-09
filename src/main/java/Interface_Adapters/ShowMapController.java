package Interface_Adapters;

import Use_Cases.GameLoopInteractorReference;
import Use_Cases.ShowMapInputBoundary;

public class ShowMapController {
    GameLoopInteractorReference gameManagerRef;
    ShowMapInputBoundary showMapInputBoundary;

    /**
     * @param showMapInputBoundary: This is the input boundary used for Show Map use case
     * @param gameManagerRef: This is a GameLoopManager reference, used to perform this use case
     */
    public ShowMapController(ShowMapInputBoundary showMapInputBoundary, GameLoopInteractorReference gameManagerRef){
        this.gameManagerRef = gameManagerRef;
        this.showMapInputBoundary = showMapInputBoundary;
    }

    public void updateMap(){
        showMapInputBoundary.showMap(gameManagerRef);
    }

}
