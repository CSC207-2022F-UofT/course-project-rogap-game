package Entities;

public class player_movement implements player_movement_interface {

    public void setVelX(int x) {this.velX = x; this.setMoving();}
    public void setVelY(int y) {this.velY = y; this.setMoving();}
    private void setMoving () {
        if (velX != 0 || velY != 0) {
            moving = true;
        } else {moving = false;}
    }
}
