package Interface_Adapters;

import Use_Cases.CollisionInputBoundary;

import java.util.ArrayList;

public class CollisionController {
    CollisionInputBoundary collisionInputBoundary;
    public CollisionController(CollisionInputBoundary collisionInputBoundary) {
        this.collisionInputBoundary = collisionInputBoundary;
    }
    public boolean movable(int visualX, int visualY, int currX, int currY, int changeX, int changeY, int width, int height) {
        /*
        Checks if given x,y coordinates can move to the given
         */
        collisionInputBoundary.updateWalls(visualX, visualY);
        return collisionInputBoundary.movable(currX, currY, changeX, changeY, width, height);
    }
/*    public boolean moveAbleEnemies() {}*/
}
