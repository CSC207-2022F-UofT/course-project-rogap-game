package Interface_Adapters;

public interface UpdateScreenBoundary {

    void update();

    void requestFocus();

    void setUp(PauseGameController pauseGameController, ShowMapController showMapController,
               StatBarsPresenterBoundary statBarsPresenterBoundary, ShowStatsController showStatsController,
               PlayerMovementController playerMovementController, CreateEnemyController createEnemyController,
               ShopAnimationController shopAnimationController, PlayerMovementController playerMovementController, 
               AttackController attackController, CreateEnemyController createEnemyController);
}
