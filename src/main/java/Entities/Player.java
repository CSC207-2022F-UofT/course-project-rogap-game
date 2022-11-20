package Entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private GamePanel gamePanel;
    private BufferedImage leftIdle, rightIdle, rightMovement, leftMovement;
    private BufferedImage[] sprites = new BufferedImage[4];
    public BufferedImage[][] animations;
    private int idleDir = 0;

    private int velX = 0, velY = 0;
    private int absXPlayer = 1882, absYPlayer = 1738;

    private int aniTick, aniIndex, aniSpeed= 10;
    private int playerAction = 0;
    private boolean moving = false;
    public Rectangle hitBox;
    public Player(GamePanel gamePanel, int xDelta, int yDelta) {
        this.gamePanel = gamePanel;
        importImage();
        loadAnimation();
    }
    public void update() {
        gamePanel.changeXDelta(velX);
        gamePanel.changeYDelta(velY);
        this.absXPlayer -= velX;
        this.absYPlayer -= velY;
        updateAnimationTick();
        setAnimation();
        hitBox = new Rectangle(absXPlayer, absYPlayer, 36, 36);
    }

    //Helper methods
    public int getAbsXPlayer() {return this.absXPlayer;}
    public int getAbsYPlayer() {return this.absYPlayer;}

    //Handles all of player movement
    public void setVelX(int x) {
        velX = x;
    }
    public void setVelY(int y) {
        velY = y;
    }
    public void setMoving () {
        if (velX != 0 || velY != 0) {
            moving = true;
        } else {moving = false;}
    }
    //All the functions that control player animations
    private void importImage() {
        InputStream lI = getClass().getResourceAsStream("/leftIdle.png");
        InputStream rI = getClass().getResourceAsStream("/rightIdle.png");
        InputStream lM = getClass().getResourceAsStream("/leftMovement.png");
        InputStream rM = getClass().getResourceAsStream("/rightMovement.png");
        try {
            assert lI != null & rI != null & lM != null & rM != null;
            sprites[0] = ImageIO.read(lI);
            sprites[1] = ImageIO.read(rI);
            sprites[2] = ImageIO.read(lM);
            sprites[3] = ImageIO.read(rM);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lI.close();
                rM.close();
                rI.close();
                lM.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAnimation() {
        animations = new BufferedImage[4][6];
        for (int j = 0; j < animations.length; j++){
            for (int i = 0; i < animations[j].length; i++) {
                if (j <= 1) {
                    animations[j][i] = sprites[j].getSubimage(i*32, 0,32,32);
                } else if (j >= 2 & i <= 4) {
                    animations[j][i] = sprites[j].getSubimage(i*32, 0,32,32);
                }
            }
        }
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
        return this.animations[playerAction][aniIndex];
    }
}
