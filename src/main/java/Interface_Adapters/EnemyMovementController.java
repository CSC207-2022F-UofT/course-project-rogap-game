package Interface_Adapters;

import Use_Cases.EnemeyMovementInteractor;
import Use_Cases.EnemyMovement;
import Use_Cases.EnemyMovementInputBoundary;

public class EnemyMovementController {

    PlayerMovementController playerMovementController;
    CollisionController collisionController;
    EnemyMovementInputBoundary enemyMovementInputBoundary;
    public EnemyMovementController(EnemyMovementInputBoundary enemyMovementInputBoundary, PlayerMovementController playerMovementController,
                                   CollisionController collisionController) {
        this.enemyMovementInputBoundary = enemyMovementInputBoundary;
        this.collisionController = collisionController;
        this.playerMovementController = playerMovementController;
    }

    public void update() {
        if (enemyMovementInputBoundary.getDistance(playerMovementController.getHelperX(),
                playerMovementController.getHelperY()) < 600 & enemyMovementInputBoundary.getDistance(playerMovementController.getHelperX(),
                playerMovementController.getHelperY()) > 100) {
            if (collisionController.movable(playerMovementController.getVisualX(), playerMovementController.getVisualY(), enemyMovementInputBoundary.getHelperX(),
                    enemyMovementInputBoundary.getHelperY(), enemyMovementInputBoundary.getVelX(playerMovementController.getHelperX()), 0, 24, 24)){
                enemyMovementInputBoundary.changeX(enemyMovementInputBoundary.getVelX(playerMovementController.getHelperX()));
            }
            if (collisionController.movable(playerMovementController.getVisualX(), playerMovementController.getVisualY(), enemyMovementInputBoundary.getHelperX(),
                    enemyMovementInputBoundary.getHelperY(), 0, enemyMovementInputBoundary.getVelY(playerMovementController.getHelperY()), 24, 24)) {
                enemyMovementInputBoundary.changeY(enemyMovementInputBoundary.getVelY(playerMovementController.getHelperY()));
            }
        }
    }
    public int getHelperX() {
        return enemyMovementInputBoundary.getHelperX();
    }
    public int getHelperY(){
        return enemyMovementInputBoundary.getHelperY();
    }
    public int getVisualX(){
        return enemyMovementInputBoundary.getVisualX();
    }
    public int getVisualY(){
        return enemyMovementInputBoundary.getVisualY();
    }
}
