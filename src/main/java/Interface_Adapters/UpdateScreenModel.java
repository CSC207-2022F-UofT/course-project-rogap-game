package Interface_Adapters;

public interface UpdateScreenModel {

    void update();

    void requestFocus();

    void setUp(PauseGameController pauseGameController, MovementController movementController);
}
