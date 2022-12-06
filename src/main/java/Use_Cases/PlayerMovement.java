package Use_Cases;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class PlayerMovement extends Movement{

    private int velX = 0, velY = 0;
    private int aniTick, aniIndex, aniSpeed= 10;
    private int playerAction = 0;
    private boolean moving = false;

    private int idleDir = 0;

    private int currLocationX = -2546, currLocationY = -2132;
    private BufferedImage[][] animations;

    public PlayerMovement(BufferedImage[][] animations) {
        this.animations = animations;
    }

    @Override
    public int newXLocation() {
        return this.currLocationX + velX;
    }

    @Override
    public int newYLocation() {
        return this.currLocationY + velY;
    }

    @Override
    public void setVelX(int x) {
        this.velX = x;
        this.setMoving();
        System.out.println("myguy");
    }
    @Override
    public void setVelY(int y) {
        this.velY = y;
        this.setMoving();
        System.out.println("maddoood");
    }
    @Override
    public void setMoving () {
        if (velX != 0 || velY != 0) {
            moving = true;
        } else {moving = false;}
    }
    public int getCurrLocationX() {
        return currLocationX;
    }
    public int getCurrLocationY() {
        return currLocationY;
    }
    private void setAnimation() {
        if (moving) {
            if ((velX == -2 & velY == -2) || (velX == -2 & velY == 2) || (velX == -2)) { //Left movement
                playerAction = 3;
            } else if ((velX == 2 & velY == -2) || (velX == 2 & velY == 2)|| (velX == 2)) { //Right movement
                playerAction = 2;
            } //Needs testing for up and down
        } else {
            if (idleDir == 0) { //Left idle animation
                playerAction = 0;
            } else {
                playerAction = 1;
            }
        }
    }
    public void setIdleDirection(int dir) {
        this.idleDir = dir;
    }
    private int getSpriteAmount(int playerAction) {
        switch (playerAction) {
            case -1:
            case 0:
            case 1:
                return 6;
            case 2:
            case 3:
                return 5;
            default:
                return 1;
        }
    }
    private void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }
    public BufferedImage getCurrentImage () {
        updateAnimationTick();
        setAnimation();
        return this.animations[playerAction][aniIndex];
    }
}
