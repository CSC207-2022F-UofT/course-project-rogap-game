package Interface_Adapters;

import Use_Cases.CreateEnemyInputBoundary;

import java.util.ArrayList;

public class CreateEnemyController {

    private CreateEnemyInputBoundary createEnemyInputBoundary;

    public CreateEnemyController(CreateEnemyInputBoundary createEnemyInputBoundary){
        this.createEnemyInputBoundary = createEnemyInputBoundary;
    }

    public void create(){
        createEnemyInputBoundary.createEnemies();
    }

    public ArrayList<ArrayList> getEnemyInfo(){
        return createEnemyInputBoundary.getEnemies();
    }


}
