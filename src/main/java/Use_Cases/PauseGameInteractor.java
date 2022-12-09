package Use_Cases;

public class PauseGameInteractor implements PauseGameInputBoundary{

    /**
     * @param gameManagerRef: This is a GameLoopManager reference,
     *                      used to perform this pause game use case
     */
    @Override
    public void pause(GameLoopInteractorReference gameManagerRef) {
        gameManagerRef.changeIsPaused();
        gameManagerRef.reDraw();
    }
}
