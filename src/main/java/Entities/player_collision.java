package Entities;

import main.WallCollision;

import java.awt.*;
import java.util.ArrayList;

public class player_collision implements player_collision_interface {

    private int velX = 0, velY = 0;
    private int absXPlayer = 1882, absYPlayer = 1738;

    private int[][] verticalWalls = {{0,1,1},{0,4,1},{1,1,0},{1,2,1},{1,3,1},{1,4,0},{2,0,1},{2,1,0},{2,2,1},{2,3,0},{2,4,1},{3,0,1},{3,1,1},{3,3,1}};
    private int[][] horizontalWalls = {{2,0,1},{0,1,1},{1,1,1},{2,1,0},{0,2,1},{1,2,0},{2,2,1},{1,3,0},{2,3,1},{1,4,0},{0,4,1},{2,4,1},{0,5,1},{1,5,1}};
    private WallCollision wallCollision = new WallCollision(verticalWalls, horizontalWalls);

    @Override
    public void update() {
        updateWalls();
        ArrayList wallCheck = currMoveCollision(-velX, -velY);
        if (movable(absXPlayer - velX, absYPlayer - velY) & (boolean) wallCheck.get(0)) {
            gamePanel.changeXDelta(velX);
            gamePanel.changeYDelta(velY);
            updateLocation(velX, velY);
        } else if (movable(absXPlayer - velX, absYPlayer - velY) & !((boolean) wallCheck.get(0))) {
            if (wallCheck.get(1) == "y" & (int) wallCheck.get(2) <= 1) {
                gamePanel.changeXDelta(velX);
                updateLocation(velX, 0);
            } else if (wallCheck.get(1) == "x" & (int) wallCheck.get(2) <= 1) {
                gamePanel.changeYDelta(velY);
                updateLocation(0, velY);
            }
        }
        updateAnimationTick();
        setAnimation();
    }
    @Override
    public ArrayList currMoveCollision(int x, int y) {
        return this.getWallCollision().moveAbleWall(616 + 12, 326 + 12,
                x, y, 24, 24);
    }
    //Player collisions with enemy and walls.
    @Override
    public void updateLocation(int x, int y){
        this.absXPlayer -= x;
        this.absYPlayer -= y;
    }
    @Override
    public void updateWalls() {wallCollision.createWallLayout(gamePanel.getXDelta() + velX, gamePanel.getYDelta() + velY);}
    @Override
    public WallCollision getWallCollision() {return this.wallCollision;}

    @Override
    public boolean movable(int targetX, int targetY) {
        Rectangle hitBox = new Rectangle(targetX + 6, targetY + 6, 36, 36);
        boolean move = true;
        for (MeleeEnemy enemy : gamePanel.getEnemyList()) {
            if (hitBox.intersects(enemy.getHitBox())) {
                move = false;
            }
        }
        return move;
    }

}
