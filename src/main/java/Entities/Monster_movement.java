package Entities;

import java.util.ArrayList;

public class Monster_movement implements Monster_movement_interface {

    private int xEnemy;
    private int yEnemy;
    private double distance;
    private int velX = 0, velY = 0;
    private int spawnX, spawnY;

    @Override
    public void enemyMovement() { //In order to update current enemy location must update absXenemy.
        distance = Math.sqrt((Math.pow((gamePanel.player.getAbsXPlayer() - xEnemy - spawnX + 1896),2) + Math.pow((gamePanel.player.getAbsYPlayer() - yEnemy -spawnY + 1046), 2)));
        if (distance < 600 & distance > 110) {
            System.out.println(yEnemy);
            velX = enemyMoveHelper(xEnemy - 616 - 1280,gamePanel.player.getAbsXPlayer() - spawnX);
            velY = enemyMoveHelper(yEnemy - 326 - 720,gamePanel.player.getAbsYPlayer() - spawnY);
            ArrayList wall = currMoveCollision(velX, velY);
            if ((Boolean) wall.get(0)) {
                xEnemy -= velX;
                spawnX -= velX;
                yEnemy -= velY;
                spawnY -= velY;
            } else {
                //TODO: make enemies move randomly while it is touching the border.
            }
        } else if (distance < 110) {
            //TODO: add a function that calls the attack for enemy.
        } else {
            //TODO: make enemies move randomly while player is not close
        }
    }
    @Override
    private ArrayList currMoveCollision(int x, int y) {
        return gamePanel.player.getWallCollision().moveAbleWall(xEnemy + 4, yEnemy + 4,
                x, y, 24, 24);
    }
    @Override
    public int enemyMoveHelper(int c, int targetC) {
        if (c < targetC) {
            return -1;
        } else if (c == targetC) {
            return 0;
        } else {
            return 1;
        }
    }
}
