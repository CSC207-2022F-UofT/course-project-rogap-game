package Entities;

import java.awt.image.BufferedImage;

public interface player_animation_interface {

    public void importImage();
    public void loadAnimation();
    public void setAnimation();
    public void setIdleDirection(int dir);
    public int getSpriteAmount(int playerAction);
    public void updateAnimationTick();

    public BufferedImage getCurrentImage();
}
