package Interface_Adapters;

import Use_Cases.CreateEnemyInputBoundary;

import java.util.ArrayList;

public class CreateEnemyController {

    private CreateEnemyInputBoundary createEnemyInputBoundary;
    private PlayerMovementController playerMovementController;

    public CreateEnemyController(CreateEnemyInputBoundary createEnemyInputBoundary,
                                 PlayerMovementController playerMovementController){
        this.createEnemyInputBoundary = createEnemyInputBoundary;
        this.playerMovementController = playerMovementController;
    }
    public void updateEnemies(int xDelta, int yDelta) {
        createEnemyInputBoundary.updateEnemies(xDelta, yDelta);
    }
    public void create(){
        createEnemyInputBoundary.createEnemies(playerMovementController.getVisualX(),
                playerMovementController.getVisualY());
    }

    public ArrayList<ArrayList> getEnemyInfo(){
        return createEnemyInputBoundary.getEnemies();
    }


}
