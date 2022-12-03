package Use_Cases;

import Interface_Adapters.GameLoopManager;

public class PauseGame implements PauseGameInputBoundary{

    @Override
    public void pause(GameLoopManager gameManager) {
        gameManager.changeIsPaused();
    }
}
