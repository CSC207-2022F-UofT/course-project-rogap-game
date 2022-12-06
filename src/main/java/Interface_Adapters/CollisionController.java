package Interface_Adapters;

import Use_Cases.CollisionInputBoundary;

import java.util.ArrayList;

public class CollisionController {
    CollisionInputBoundary collisionInputBoundary;
    public CollisionController(CollisionInputBoundary collisionInputBoundary) {
        this.collisionInputBoundary = collisionInputBoundary;
    }
    public boolean movable(int currX, int currY, int changeX, int changeY, int width, int height) {
        collisionInputBoundary.updateWalls(currX, currY);
        return collisionInputBoundary.movable(currX, currY, changeX, changeY, width, height);
    }
}
