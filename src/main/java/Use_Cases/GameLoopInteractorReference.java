package Use_Cases;

public interface GameLoopInteractorReference {
    void changeIsPaused();
    void changeMinimapVisible();

    void start();
    void reDraw();
}
