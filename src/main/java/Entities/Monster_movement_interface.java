package Entities;

import java.util.ArrayList;

public interface Monster_movement_interface {

    public void enemyMovement ();
    public int enemyMoveHelper(int c, int targetC);
    public ArrayList currMoveCollision(int x, int y);
}
