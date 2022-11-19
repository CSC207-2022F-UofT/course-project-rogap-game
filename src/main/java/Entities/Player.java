package Entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player {
    private GamePanel gamePanel;
    private BufferedImage sprites;
    public BufferedImage[][] animations;

    private int velX = 0, velY = 0;
    private int absXPlayer = 1882, absYPlayer = 1738;

    private int aniTick, aniIndex, aniSpeed= 25;
    private int playerAction = 0;
    private boolean moving = false;
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
        InputStream ps = getClass().getResourceAsStream("/Sprites.png");
        try {
            assert ps != null;
            sprites = ImageIO.read(ps);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadAnimation() {
        animations = new BufferedImage[4][12];
        for (int j = 0; j < animations.length; j++){
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = sprites.getSubimage(i*50, j*69,50,69);
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            if ((velX == -2 & velY == -2) || (velX == -2 & velY == 2) || (velX == -2)) { //Left and up or left and down or left
                playerAction = 2;
            } else if ((velX == 2 & velY == -2) || (velX == 2 & velY == 2)|| (velX == 2)) { //Right and up or right and down or right
                playerAction = 1;
            } else if (velY == -2){ //Down
                playerAction = 0;
            } else if (velY == 2) { //UP
                playerAction = 3;
            }
        }
    }
    private int getSpriteAmount(int playerAction) {
        switch (playerAction) {
            case 0:
            case 1:
            case 2:
            case 3:
                return 3;
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
