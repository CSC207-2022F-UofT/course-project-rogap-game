package Entities;

public class RangedEnemy extends Enemy{

    /**
     * This is the enemy class constructor
     * @param name: This is the name of the RangedEnemy
     * @param x: This is the current x-coordinate of the map
     * @param y: This is the current y-coordinate of the map
     * @param spawnX: This is the x-coord for the enemy spawn
     * @param spawnY: This is the y-coord for the enemy spawn
     */
    public RangedEnemy(String name, int x, int y, int spawnX, int spawnY) {
        super(name, x, y, spawnX, spawnY);

    }
}
