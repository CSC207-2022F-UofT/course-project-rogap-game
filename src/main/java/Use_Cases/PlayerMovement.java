package Use_Cases;

import Entities.Player;

import java.awt.image.BufferedImage;

public class PlayerMovement extends Movement{

    private int velX = 0, velY = 0;
    private int aniTick, aniIndex, aniSpeed= 10;
    private int playerAction = 0;
    private boolean moving = false;
    private int speed = 2;
    private int idleDir = 0;

    private int currLocationX = -2546, currLocationY = -2132;
    private int helperX = 1882, helperY = 1738;
    private boolean right, left, up, down;
    private BufferedImage[][] animations;
    public Player player;
    public PlayerMovement(Player player) {
        this.animations = animations;
        this.player = player;
    }

    public void updateX() {
        if (left) {
            this.currLocationX += speed;
            this.helperX -= speed;
        } else if (right) {
            this.currLocationX += -speed;
            this.helperX -= -speed;
        }
    }

    public void updateY() {
        if (up) {
            this.currLocationY += speed;
            this.helperY -= speed;
        } else if (down) {
            this.currLocationY += -speed;
            this.helperY -= -speed;
        }
    }
    //Potential bug fixes
    public int getVelX () {
        if (left) {
            return speed;
        } else if (right) {
            return -speed;
        } else {
            return 0;
        }
    }
    public int getVelY () {
        if (down) {
            return -speed;
        } else if (up) {
            return speed;
        } else {
            return 0;
        }
    }
    public void rightActivator() {
        this.right = true;
        this.setMoving();
    }
    public void leftActivator() {
        this.left = true;
        this.setMoving();
    }
    public void upActivator() {
        this.up = true;
        this.setMoving();
    }
    public void downActivator() {
        this.down = true;
        this.setMoving();
    }
    public void rightDeactivator() {
        this.right = false;
        this.setMoving();
    }
    public void leftDeactivator() {
        this.left = false;
        this.setMoving();
    }
    public void upDeactivator() {
        this.up = false;
        this.setMoving();
    }
    public void downDeactivator() {
        this.down = false;
        this.setMoving();
    }
    public void setMoving () {
        if (right || left || up || down) {
            moving = true;
        } else {moving = false;}
    }

    //Getting locations
    public int getVisualX() {
        return currLocationX;
    }
    public int getVisualY() {
        return currLocationY;
    }
    public int getHelperX() {
        return helperX;
    }
    public int getHelperY() {
        return helperY;
    }

    public void setAnimations(BufferedImage[][] animations) {
        player.setAnimations(animations);
    }
    public BufferedImage[][] getAnimations() {
        return player.getAnimations();
    }
    private void updateAnimation() {
        if (moving) {
            if ((right & up) || (right & down) || (right)) {
                playerAction = 3;
            } else if ((left & up) || (left & down)|| (left)) {
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
        updateAnimation();
        return this.animations[playerAction][aniIndex];
    }
}
