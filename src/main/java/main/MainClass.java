package main;

import Entities.HealthPotion;
import Entities.Player;
import Frameworks.*;
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



        //Collisions set up
        Collision collision = new Collision();
        CollisionInputBoundary collisionInteractor = new CollisionInteractor(collision);
        CollisionController collisionController = new CollisionController(collisionInteractor);

        //Player animation and movement setup
        //TODO: Player takes parameters ABUUUU -> Don't forget to add player username
        Player player = new Player("hello");
        PlayerAnimationImport playerAnimationImport = new PlayerAnimationImport();
        PlayerMovement playerMovement = new PlayerMovement(player);
        PlayerMovementInputBoundary playerMovementInteractor = new PlayerMovementInteractor(playerMovement);
        PlayerMovementController playerMovementController = new PlayerMovementController(playerMovementInteractor, collisionController);
        new AnimationsImportController(playerAnimationImport.getPlayerAnimations(), playerMovementController);

        //Initialize Potions
        HealthPotion healthPotion = new HealthPotion();
        ShopAnimation shopAnimation = new ShopAnimation(healthPotion);
        ShopAnimationInputBoundary shopAnimationInteractor = new ShopAnimationInteractor(shopAnimation);
        ShopAnimationController shopAnimationController = new ShopAnimationController(shopAnimationInteractor);

        ShopAnimationsImport shopAnimationsImport = new ShopAnimationsImport(shopAnimationController);
        // TODO: THIS IS THE GOOD STUFF
        ShopAnimationsImportController shopAnimationsImportController = new ShopAnimationsImportController(shopAnimationsImport.getItemAnimation(), shopAnimationController);



        //Create Enemies use-case
        CreateEnemyInputBoundary enemyManagerInteractor = new EnemyManagerHandler();
        CreateEnemyController createEnemyController = new CreateEnemyController(enemyManagerInteractor,
                playerMovementController);
        createEnemyController.create();

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
                showStatsController, playerMovementController, createEnemyController, shopAnimationController);
        gameManager.start();
    }
}
