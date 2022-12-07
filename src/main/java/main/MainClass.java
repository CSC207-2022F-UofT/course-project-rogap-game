package main;

import Frameworks.GamePanel;
import Frameworks.MonsterAnimationImport;
import Frameworks.PlayerAnimationImport;
import Interface_Adapters.*;
import Use_Cases.*;

public class MainClass {
    public static void main(String[] args) {

/*
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Welcome " + userName + "!");  // Output user input
*/

        UpdateScreenBoundary screenModel = new GamePanel();
        GameScreenPresenter presenter = new GameScreenPresenter(screenModel);
        //GameWindow application = new GameWindow(presenter);

        //Creating player sprites in blue layer, maybe controller needed?
        PlayerAnimationImport playerAnimationImport = new PlayerAnimationImport();
        //Player movement and collisions
        PlayerMovement playerMovement = new PlayerMovement(playerAnimationImport.getPlayerAnimations());
        Collision collision = new Collision();
        CollisionInputBoundary collisionInteractor = new CollisionInteractor(collision);
        CollisionController collisionController = new CollisionController(collisionInteractor);
        PlayerMovementInputBoundary playerMovementInteractor = new PlayerMovementInteractor(playerMovement);
        PlayerMovementController playerMovementController = new PlayerMovementController(playerMovementInteractor, collisionController);

        //Player & Monster attack/hit
        MonsterAnimationImport monsterAnimationImport = new MonsterAnimationImport();
        PlayerAttack playerAttack = new PlayerAttack(playerAnimationImport.getPlayerAttackHitAnimations());
        MeleeAttack meleeAttack = new MeleeAttack(monsterAnimationImport.getPlayerAttackHitAnimations());
        RangedAttack rangedAttack = new RangedAttack(monsterAnimationImport.getPlayerAttackHitAnimations());
        MeleeAttack[] meleeAttacks = new MeleeAttack[1]; // TODO: change to arrays from Enemy Manager class
        meleeAttacks[0] = meleeAttack;
        RangedAttack[] rangedAttacks = new RangedAttack[1];
        rangedAttacks[0] = rangedAttack;
        PlayerAttackInputBoundary playerAttackInputBoundary = new PlayerAttackInteractor(playerAttack,
                meleeAttacks, rangedAttacks);
        MonsterAttackInputBoundary monsterAttackInputBoundary = new MonsterAttackInteractor(meleeAttacks, rangedAttacks,
                playerAttack);
        AttackController attackController = new AttackController(playerAttackInputBoundary, monsterAttackInputBoundary);



        GameLoopInteractorReference gameManager = new GameLoopManagerLoop(presenter, playerMovementController);

        PauseGameInputBoundary pauseGameInteractor = new PauseGameInteractor();
        ShowMapInputBoundary showMapInteractor = new ShowMapInteractor();

        PauseGameController pauseGameController = new PauseGameController(pauseGameInteractor, gameManager);
        ShowMapController showMapController = new ShowMapController(showMapInteractor, gameManager);




        screenModel.setUp(pauseGameController, showMapController, playerMovementController, attackController);
        gameManager.start();
    }
}
