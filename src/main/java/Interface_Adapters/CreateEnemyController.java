package Interface_Adapters;

import Use_Cases.CreateEnemyInputBoundary;

import java.util.ArrayList;

public class CreateEnemyController {

    private CreateEnemyInputBoundary createEnemyInputBoundary;
    private PlayerMovementController playerMovementController;
    private EnemyMovementController enemyMovementController;

    public CreateEnemyController(CreateEnemyInputBoundary createEnemyInputBoundary,
                                 PlayerMovementController playerMovementController,
                                 EnemyMovementController enemyMovementController){
        this.createEnemyInputBoundary = createEnemyInputBoundary;
        this.playerMovementController = playerMovementController;
        this.enemyMovementController = enemyMovementController;
    }
    public void updateMapLocation(int xDelta, int yDelta) {
        createEnemyInputBoundary.updateEnemies(xDelta, yDelta);
    }
    public void create(){
        createEnemyInputBoundary.createEnemies(playerMovementController.getVisualX(),
                playerMovementController.getVisualY());
    }

    public ArrayList<ArrayList> getEnemyInfo(){
        return createEnemyInputBoundary.getEnemiesInfo();
    }
    public void update() {

    }
}
