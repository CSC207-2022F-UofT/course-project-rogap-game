package Use_Cases;

public class ShowMapInteractor implements ShowMapInputBoundary {
    /**
     * @param gameManagerRef: This is a GameLoopManager reference, used to perform this use case
     */
    @Override
    public void showMap(GameLoopInteractorReference gameManagerRef) {
        gameManagerRef.changeMinimapVisible();
        gameManagerRef.reDraw();
    }
}
