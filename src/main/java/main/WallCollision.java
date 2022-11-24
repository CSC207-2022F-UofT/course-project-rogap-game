package main;

import java.awt.*;

public class WallCollision {
    public Rectangle[] wallLayout = new Rectangle[60];
    public int[][] vWalls = {{0,1,1},{0,4,1},{1,1,0},{1,2,1},{1,3,1},{1,4,0},{2,0,1},{2,1,0},{2,2,1},{2,3,0},{2,4,1},{3,0,1},{3,1,1},{3,3,1}};
    public int[][] hWalls = {{2,0,1},{0,1,1},{1,1,1},{2,1,0},{0,2,1},{1,2,0},{2,2,1},{1,3,0},{2,3,1},{1,4,0},{0,4,1},{2,4,1},{0,5,1},{1,5,1}};
    public WallCollision(){

    }
    public void createWallLayout(int xDelta, int yDelta) {
        int zeroX = xDelta + 2546 - 1265;
        int zeroY = yDelta + 2132 - 1410;
        int i = 0;
        for (int[] wall : vWalls) {
            if (wall[2] == 0) {
                wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY + (wall[1] * 705), 15, 250);
                i += 1;
                wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY + (wall[1] * 705) + 470, 15, 250);
                i += 1;
            } else {
                wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY + (wall[1] * 705) , 15, 720);
                i += 1;
            }
        }
        for (int[] wall: hWalls) {
            if (wall[2] == 0) {
                wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY+ (wall[1] * 705), 490, 15);
                i += 1;
                wallLayout[i] = new Rectangle(zeroX + 770 + (wall[0] * 1265), zeroY + (wall[1] * 705), 510, 15);
                i += 1;
            } else {
                wallLayout[i] = new Rectangle(zeroX+ (wall[0] * 1265), zeroY + (wall[1] * 705), 1280, 15);
                i += 1;
            }
        }
    }
    public boolean movableWall(int targetX,int targetY, int width, int height) {
        boolean move = true;
        boolean helper = true;
        Rectangle hitBox = new Rectangle(targetX, targetY, width, height);
        Rectangle[] wallsToCheck = this.wallLayout;
        int i = 0;
        while (move & helper) {
            if (wallsToCheck[i] != null) {
                if (hitBox.intersects(wallsToCheck[i])) {
                    move = false;
                }
            } else {helper = false;}
            i += 1;
        }
        return move;
    }

}
