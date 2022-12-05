package Interface_Adapters;

public interface UpdateScreenBoundary {

    void update();

    void requestFocus();

    void setUp(PauseGameController pauseGameController, ShowMapController showMapController, PlayerMovementController playerMovementController);
}
