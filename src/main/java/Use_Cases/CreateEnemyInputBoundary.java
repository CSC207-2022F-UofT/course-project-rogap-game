package Use_Cases;

import java.util.ArrayList;

public interface CreateEnemyInputBoundary {
    // TODO CHANGE NAME
    void createEnemies(int xDelta, int yDelta);

    ArrayList<ArrayList> getEnemiesInfo();
    void updateEnemies(int velX, int velY);
}
