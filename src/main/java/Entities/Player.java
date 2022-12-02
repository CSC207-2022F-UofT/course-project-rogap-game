package Entities;

import Use_Cases.ShopSystem;
import main.Game;
import main.GamePanel;
import main.WallCollision;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
    
    // VARIABLES FOR SHOP SYSTEM
    private int gold = 100;
    private int health = 10;

    private int[][] verticalWalls = {{0,1,1},{0,4,1},{1,1,0},{1,2,1},{1,3,1},{1,4,0},{2,0,1},{2,1,0},{2,2,1},{2,3,0},{2,4,1},{3,0,1},{3,1,1},{3,3,1}};
    private int[][] horizontalWalls = {{2,0,1},{0,1,1},{1,1,1},{2,1,0},{0,2,1},{1,2,0},{2,2,1},{1,3,0},{2,3,1},{1,4,0},{0,4,1},{2,4,1},{0,5,1},{1,5,1}};
    private WallCollision wallCollision = new WallCollision(verticalWalls, horizontalWalls);

    // for attack
    private Ellipse2D.Float hitBox, attackRadius;
    

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        importImage();
        loadAnimation();

        initHitBox();
        initAttackBox();
    }
    public void update() {
        updateWalls();
        ArrayList wallCheck = currMoveCollision(-velX, -velY);
        if (movable(absXPlayer - velX, absYPlayer - velY) & (boolean) wallCheck.get(0)) {
            gamePanel.changeXDelta(velX);
            gamePanel.changeYDelta(velY);
            updateLocation(velX, velY);
        } else if (movable(absXPlayer - velX, absYPlayer - velY) & !((boolean) wallCheck.get(0))) {
            if (wallCheck.get(1) == "y" & (int) wallCheck.get(2) <= 1) {
                gamePanel.changeXDelta(velX);
                updateLocation(velX, 0);
            } else if (wallCheck.get(1) == "x" & (int) wallCheck.get(2) <= 1) {
                gamePanel.changeYDelta(velY);
                updateLocation(0, velY);
            }
        }
        updateAnimationTick();
        setAnimation();
    }
    private ArrayList currMoveCollision(int x, int y) {
        return this.getWallCollision().moveAbleWall(616 + 12, 326 + 12,
                x, y, 24, 24);
    }
    //Player collisions with enemy and walls.
    private void updateLocation(int x, int y){
        this.absXPlayer -= x;
        this.absYPlayer -= y;
    }
    public void updateWalls() {wallCollision.createWallLayout(gamePanel.getXDelta() + velX, gamePanel.getYDelta() + velY);}
    public WallCollision getWallCollision() {return this.wallCollision;}
//    public Rectangle getHitBox() {
//        Rectangle hitBox = new Rectangle(absXPlayer + 6, absYPlayer + 6, 36, 36);
//        return hitBox;
//    }
    // changed Player hitbox to an ellipse
    private void initHitBox() {
//        hitBox = new Ellipse2D.Float(absXPlayer + 6, absYPlayer + 6, 48, 48);
        hitBox = new Ellipse2D.Float(615, 325, 48, 48);
    }
    public Ellipse2D.Float getHitBox() {
        return hitBox;
    }
    private boolean movable(int targetX, int targetY) {
        Rectangle hitBox = new Rectangle(targetX + 6, targetY + 6, 36, 36);
        boolean move = true;
        for (MeleeEnemy enemy : gamePanel.getEnemyList()) {
//            if (hitBox.intersects(enemy.getHitBox())) {

            // changed the method of finding the intersection to accommodate for the
            // hitbox shape change (from rect to ellipse)
            Area hitBoxArea = new Area(hitBox);     // find area of hitBox
            hitBoxArea.intersect(new Area(enemy.getHitBox()));      // find intersection between hitBox and enemy.getHitBox()
            if (!hitBoxArea.isEmpty()){         // if they intersect (the intersection is non-empty)
                move = false;
            }
        }
        return move;
    }
    public int getHealth() {
        return this.health;
    }

    public void addHealth(int amount){
        System.out.println("Health Before: " + getHealth());
        this.health += amount;
        if (getHealth() > 100){
            this.health = 100;
        }
        System.out.println("Health After: " + getHealth());
    }

    public int getGold(){
        return this.gold;
    }
    public void addGold(int gold){
        this.gold += gold;
    }

    public void removeGold(int amount){
        this.gold -= amount;
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
    private void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= PlayerConstants.GetSpriteAmount(playerAction)) {
                // changed get sprite amount method so that it calls it from the PlayerConstants class
                aniIndex = 0;
            }
        }
    }
    public BufferedImage getCurrentImage () {
        return this.animations[playerAction][aniIndex];
    }

    // for attack
    private void initAttackBox() {
        attackRadius = new Ellipse2D.Float(590, 300, 100, 100);
        // attack radius at position (590, 300) with width and heigth 100
    }

    public Ellipse2D.Float getAttackRadius() {
        return attackRadius;
    }

    public void drawAttackRadius(Graphics g) {
        g.setColor(Color.red);
        g.drawOval((int) attackRadius.x, (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
        // can remove lvlOffsetX after
    }

    //  FOR DEBUGGING
    public void drawPlayerHitbox(Graphics g) {
        g.setColor(Color.red);
        g.drawOval((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

}
