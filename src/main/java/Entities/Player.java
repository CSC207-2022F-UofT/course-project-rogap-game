package Entities;

import main.GamePanel;
import main.WallCollision;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player {
    private GamePanel gamePanel;
    private BufferedImage[] sprites = new BufferedImage[4];
    private BufferedImage[][] animations;
    private int idleDir = 0;

    private int velX = 0, velY = 0;
    private int absXPlayer = 1882, absYPlayer = 1738;

    private int aniTick, aniIndex, aniSpeed= 10;
    private int playerAction = 0;
    private boolean moving = false;
    private int[][] verticalWalls = {{0,1,1},{0,4,1},{1,1,0},{1,2,1},{1,3,1},{1,4,0},{2,0,1},{2,1,0},{2,2,1},{2,3,0},{2,4,1},{3,0,1},{3,1,1},{3,3,1}};
    private int[][] horizontalWalls = {{2,0,1},{0,1,1},{1,1,1},{2,1,0},{0,2,1},{1,2,0},{2,2,1},{1,3,0},{2,3,1},{1,4,0},{0,4,1},{2,4,1},{0,5,1},{1,5,1}};
    private WallCollision wallCollision = new WallCollision(verticalWalls, horizontalWalls);

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        importImage();
        loadAnimation();
    }
    public void update() {
        updateWalls();
        String[] wallCheck = currMoveCollision(-velX, -velY);
        if (movable(absXPlayer - velX, absYPlayer - velY) & wallCheck[0] == "true") {
            gamePanel.changeXDelta(velX);
            gamePanel.changeYDelta(velY);
            updateLocation(velX, velY);
        } else if (wallCheck[1] == "y") {
            gamePanel.changeXDelta(velX);
            updateLocation(velX, 0);
        } else if (wallCheck[1] == "x") {
            gamePanel.changeYDelta(velY);
            updateLocation(0, velY);
        }
        updateAnimationTick();
        setAnimation();
    }

    private String[] currMoveCollision(int x, int y) {
        return this.getWallCollision().enemyMovableWall(616 + 12, 326 + 12,
                x, y, 24, 24);
    }
    //Player collisions with enemy and walls.
    private void updateLocation(int x, int y){
        this.absXPlayer -= x;
        this.absYPlayer -= y;
    }
    public void updateWalls() {wallCollision.createWallLayout(gamePanel.getXDelta() + velX, gamePanel.getYDelta() + velY);}
    public WallCollision getWallCollision() {return this.wallCollision;}
    public Rectangle getHitBox() {
        Rectangle hitBox = new Rectangle(absXPlayer + 6, absYPlayer + 6, 36, 36);
        return hitBox;
    }
    private boolean movable(int targetX, int targetY) {
        Rectangle hitBox = new Rectangle(targetX + 6, targetY + 6, 36, 36);
        boolean move = true;
        for (MeleeEnemy enemy : gamePanel.getEnemyList()) {
            if (hitBox.intersects(enemy.getHitBox())) {
                move = false;
            }
        }
        return move;
    }
    //Helper methods
    public int getAbsXPlayer() {return this.absXPlayer;}
    public int getAbsYPlayer() {return this.absYPlayer;}

    //Handles all of player movement
    public void setVelX(int x) {this.velX = x; this.setMoving();}
    public void setVelY(int y) {this.velY = y; this.setMoving();}
    private void setMoving () {
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
