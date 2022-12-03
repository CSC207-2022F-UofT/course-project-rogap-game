package Use_Cases;

import java.awt.*;
import java.util.ArrayList;

public class Movement {

    private int velX = 0, velY = 0;
/*    private ArrayList currMoveCollision(int x, int y) {
        *//*

         *//*
        return this.getWallCollision().moveAbleWall(616 + 12, 326 + 12,
                x, y, 24, 24);
    }*/

    public Movement() {

    }
    public Rectangle getHitBox() {
        Rectangle hitBox = new Rectangle(absXPlayer + 6, absYPlayer + 6, 36, 36);
        return hitBox;
    }

    public int newXLocation() {
        return this.getAbsXPlayer() += velX;
    }
    public int newYLocation() {
        return this.getAbsXPlayer() += velY;
    }


    public void setVelX(int x) {this.velX = x; this.setMoving();}
    public void setVelY(int y) {this.velY = y; this.setMoving();}
    private void setMoving () {
        if (velX != 0 || velY != 0) {
            moving = true;
        } else {moving = false;}
    }

}
