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

    /**
     * Updates the enemy location, so there are three cases.
     * First case: enemy is close to player then it will move towards the player
     * Second case: enemy is close to player however it is touching a wall, so then it will move back to original spot
     * Third case: enemy is not close to player and so it will stay still
     */
    public void update() {
        if (enemyMovementInputBoundary.getDistance(playerMovementController.getHelperX(),
                playerMovementController.getHelperY()) < 600 & enemyMovementInputBoundary.getDistance(playerMovementController.getHelperX(),
                playerMovementController.getHelperY()) > 100) {
            if (collisionController.movable(playerMovementController.getVisualX(), playerMovementController.getVisualY(), enemyMovementInputBoundary.getHelperX(),
                    enemyMovementInputBoundary.getHelperY(), enemyMovementInputBoundary.getVelX(playerMovementController.getHelperX()), 0, 24, 24)){
                enemyMovementInputBoundary.changeX(enemyMovementInputBoundary.getVelX(playerMovementController.getHelperX()));
            } else {

            }
            if (collisionController.movable(playerMovementController.getVisualX(), playerMovementController.getVisualY(), enemyMovementInputBoundary.getHelperX(),
                    enemyMovementInputBoundary.getHelperY(), 0, enemyMovementInputBoundary.getVelY(playerMovementController.getHelperY()), 24, 24)) {
                enemyMovementInputBoundary.changeY(enemyMovementInputBoundary.getVelY(playerMovementController.getHelperY()));
            } else {

            }
        } else {

        }
    }
    /**
     * Returns the enemy x location of enemy relative to the edge of the map
     */
    public int getHelperX() {
        return enemyMovementInputBoundary.getHelperX();
    }
    /**
     * Returns the enemy y location of enemy relative to the edge of the map
     */
    public int getHelperY(){

        return enemyMovementInputBoundary.getHelperY();
    }
    /**
     * Returns the enemy x location of enemy, that can be used to show it on the map properly.
     */
    public int getVisualX(){
        return enemyMovementInputBoundary.getVisualX();
    }
    /**
     * Returns the enemy y location of enemy, that can be used to show it on the map properly.
     */
    public int getVisualY(){
        return enemyMovementInputBoundary.getVisualY();
    }
}
