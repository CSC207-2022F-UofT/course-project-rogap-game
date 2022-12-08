package Use_Cases;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class PlayerAttack extends Attack{

    private int aniTick, aniIndex, aniSpeed= 10;
    private boolean attacking = false, hit = false;
    private int playerAction = 0; // TODO might have to change this to some other number so that it doesnt think its attacking

    private static Ellipse2D.Float hitRadius;
    private static Ellipse2D.Float attackRadius;
    private BufferedImage[][] playerAttackHitAnimations;

    public PlayerAttack(BufferedImage[][] playerAttackHitAnimations) {
        this.playerAttackHitAnimations = playerAttackHitAnimations;
    }

//    @Override
//    public void initHitRadius() {
//        // htiRadius is created at position (615, 325) with size 40x40
//        hitRadius = new Ellipse2D.Float(615, 325, 48, 48);
//    }

    @Override
    public Ellipse2D.Float getHitRadius() {
        hitRadius = new Ellipse2D.Float(615, 325, 48, 48);
        return hitRadius;
    }

//    @Override
//    public void updateHitRadius() {
//        // There is no need to update hitRadius since the Player's position relative to the screen is always the same
//    }

//    @Override
//    public void initAttackRadius() {
//        attackRadius = new Ellipse2D.Float(590, 300, 100, 100);
//    }

    @Override
    public Ellipse2D.Float getAttackRadius() {
        attackRadius = new Ellipse2D.Float(590, 300, 100, 100);
        return attackRadius;
    }

//    @Override
//    public void updateAttackRadius() {
//        // attack radius stays the same
//    }

    public void setAttacking(boolean value) {
        this.attacking = value;
    }

    public void setHit(boolean value) {
        this.hit = value;
    }

    public void takeDamage(){
        // decrease health
    }

    @Override
    public void setAttackHitAnimation() {
        if (attacking) {
            // TODO check for attacking direction
            playerAction = 0;
        }
        if (hit) {
            // TODO check for hit direction
            playerAction = 2;
        }
    }

    /**
     * Returns the sprite amount for specific player action:
     * - Attack left -> case 0
     * - Attack right -> case 1
     * - Hit left -> case 2
     * - Hit right -> case 3
     * @param playerAction
     * @return int spriteAmount
     */
    @Override
    public int getSpriteAmount(int playerAction) {
        switch (playerAction) {
            case 0:
            case 1:
                return 5;
            case 2:
            case 3:
                return 6;
        }
        return 0;
    }

    /**
     * Updates animation tick for the animations.
     */
    @Override
    public void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    /**
     * Gets the current image depending on the current player action and animation index.
     * @return BufferedImage currentImage
     */
    @Override
    public BufferedImage getCurrentImage() {
        updateAnimationTick();
        setAttackHitAnimation();
        return this.playerAttackHitAnimations[playerAction][aniIndex];
    }

    @Override
    public void drawAttackRadius(Graphics g) {
        g.setColor(Color.red);
        g.drawOval((int) attackRadius.x, (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
    }

    @Override
    public void updatePlayer() {

    }

    @Override
    public void updateMonsters() {

    }

}
