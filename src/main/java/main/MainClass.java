package main;

import Entities.Player;
import Frameworks.GamePanel;
import Frameworks.GameWindow;
import Frameworks.PlayerAnimationImport;
import Interface_Adapters.*;
import Use_Cases.*;

public class MainClass {
    public static void main(String[] args) {
        //TODO: Use the username

//        Scanner myObj = new Scanner(System.in);
//        System.out.println("Enter username");
//
//        String userName = myObj.nextLine();  // Read user input
//        System.out.println("Welcome " + userName + "!");  // Output user input

        UpdateScreenBoundary screenModel = new GamePanel();
        GameScreenPresenter presenter = new GameScreenPresenter(screenModel);


        //Creating player sprites in blue layer, maybe controller needed?
        PlayerAnimationImport playerAnimationImport = new PlayerAnimationImport();

        //Player movement and collisions
        Player player = new Player("hello");
        PlayerMovement playerMovement = new PlayerMovement(player);

        Collision collision = new Collision();
        CollisionInputBoundary collisionInteractor = new CollisionInteractor(collision);
        CollisionController collisionController = new CollisionController(collisionInteractor);

        //Player animation and movement setup
        //TODO: Player takes parameters ABUUUU -> Don't forget to add player username
        Player player = new Player();
        PlayerAnimationImport playerAnimationImport = new PlayerAnimationImport();
        PlayerMovement playerMovement = new PlayerMovement(playerAnimationImport.getPlayerAnimations());
        PlayerMovementInputBoundary playerMovementInteractor = new PlayerMovementInteractor(playerMovement);
        PlayerMovementController playerMovementController = new PlayerMovementController(playerMovementInteractor, collisionController);

        AnimationsImportController animationsImportController = new AnimationsImportController(playerAnimationImport.getPlayerAnimations(),
                playerMovementController);
        GameLoopInteractorReference gameManager = new GameLoopManagerLoop(presenter, playerMovementController);

        // Stat Bars Use Case


        StatBarsInputBoundary statBarsInputBoundary = new StatBarsInteractor(player);
        StatBarsPresenterBoundary statBarsPresenterBoundary = new StatBarsPresenter(statBarsInputBoundary);

        // Display Stats Use Case
        ShowStatsInputBoundary showStatsInputBoundary = new ShowStatsInteractor();
        ShowStatsController showStatsController = new ShowStatsController(gameManager, showStatsInputBoundary);

        // Pause game Use Case
        PauseGameInputBoundary pauseGameInteractor = new PauseGameInteractor();
        ShowMapInputBoundary showMapInteractor = new ShowMapInteractor();
        PauseGameController pauseGameController = new PauseGameController(pauseGameInteractor, gameManager);
        ShowMapController showMapController = new ShowMapController(showMapInteractor, gameManager);

        //Create Enemies use-case
        CreateEnemyInputBoundary enemyManagerInteractor = new EnemyManagerInteractor();
        CreateEnemyController createEnemyController = new CreateEnemyController(enemyManagerInteractor);
        createEnemyController.create();

        screenModel.setUp(pauseGameController, showMapController, statBarsPresenterBoundary,
                showStatsController, playerMovementController, createEnemyController);
        gameManager.start();
    }
}
