package Entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface player_collision_interface {
    public void update();
    public ArrayList currMoveCollision(int x, int y);
    public void updateLocation(int x, int y);

    public void updateWalls();

    public boolean movable(int targetX, int targetY);

}
