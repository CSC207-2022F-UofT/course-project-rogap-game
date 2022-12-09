package main;

import Entities.Player;
import Frameworks.GamePanel;
import Frameworks.AnimationImport;
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

        //Collisions set up
        Collision collision = new Collision();
        CollisionInputBoundary collisionInteractor = new CollisionInteractor(collision);
        CollisionController collisionController = new CollisionController(collisionInteractor);

        //Player animation and movement setup
        //TODO: Player takes parameters ABUUUU -> Don't forget to add player username
        Player player = new Player("hello");
        AnimationImport animationImport = new AnimationImport();
        PlayerMovement playerMovement = new PlayerMovement(player);
        PlayerMovementInputBoundary playerMovementInteractor = new PlayerMovementInteractor(playerMovement);
        PlayerMovementController playerMovementController = new PlayerMovementController(playerMovementInteractor, collisionController);
        //Enemy stuff
        EnemyMovement enemyMovement = new EnemyMovement();
        EnemyMovementInputBoundary enemyMovementInteractor = new EnemeyMovementInteractor(enemyMovement);
        EnemyMovementController enemyMovementController = new EnemyMovementController(enemyMovementInteractor, playerMovementController, collisionController);
        //Create Enemies use-case
        CreateEnemyInputBoundary enemyManagerInteractor = new EnemyManagerHandler();
        CreateEnemyController createEnemyController = new CreateEnemyController(enemyManagerInteractor,
                playerMovementController, enemyMovementController);
        createEnemyController.create();
        new AnimationsImportController(animationImport.getPlayerAnimations(), animationImport.getEnemyAnimations(), createEnemyController,
                playerMovementController);

        // GameManager (Takes in all the controller and presenters needed for use-cases)
        GameLoopInteractorReference gameManager = new GameLoopManagerLoop(presenter, playerMovementController,
                createEnemyController);

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


        screenModel.setUp(pauseGameController, showMapController, statBarsPresenterBoundary,
                showStatsController, playerMovementController, createEnemyController);
        gameManager.start();
    }
}
