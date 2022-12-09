package Interface_Adapters;

public interface UpdateScreenBoundary {

    void update();

    void requestFocus();

    /**
     * This interface is used to set up all the controllers the GamePanel need too keep track of
     * @param pauseGameController: this is the pause game controller
     * @param showMapController: this is the pause game controller
     * @param statBarsPresenterBoundary: this is the pause game controller
     * @param showStatsController: this is the pause game controller
     * @param playerMovementController: this is the pause game controller
     * @param createEnemyController: this is the pause game controller
     */
    void setUp(PauseGameController pauseGameController, ShowMapController showMapController,
               StatBarsPresenterBoundary statBarsPresenterBoundary, ShowStatsController showStatsController,
               PlayerMovementController playerMovementController, CreateEnemyController createEnemyController);
}

