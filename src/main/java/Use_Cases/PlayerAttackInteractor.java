package Use_Cases;

import Entities.Enemy;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerAttackInteractor implements PlayerAttackInputBoundary{

    private final PlayerAttack playerAttack;
    private final Ellipse2D.Float playerAttackRadius;
    private final ArrayList<Enemy> enemies;

    public PlayerAttackInteractor(PlayerAttack playerAttack, ArrayList<Enemy> enemies) {
        this.playerAttack = playerAttack;
        this.playerAttackRadius = playerAttack.getAttackRadius();
//        this.meleeEnemies = enemyManagerHandler.getMeleeEnemies();
//        this.rangedEnemies = enemyManagerHandler.getRangedEnemies();
        this.enemies = enemies;
    }

    @Override
    public void attack() {
        // TODO: set Player state to attacking
        playerAttack.setAttacking(true);

        for (Enemy e : enemies) {
            Area playerAttackRadius = new Area(this.playerAttackRadius);     // find area of Player's attack box
            Ellipse2D.Float enemyHitRadius = new Ellipse2D.Float(e.getHelperX(), e.getHelperY(), 48, 48);
            playerAttackRadius.intersect(new Area(enemyHitRadius));      // find intersection between Player attackbox and enemy hitbox
            if (!playerAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
                e.setHit(true);
                playerAttack.takeDamage(e);   // TODO!!!!!!!!!!
                return;
            }
        }

//        for (MeleeEnemy m : meleeEnemies.values()) {
//            Area playerAttackRadius = new Area(this.playerAttackRadius);     // find area of Player's attack box
//            Ellipse2D.Float meleeHitRadius = new Ellipse2D.Float(m.getHelperX(), m.getHelperY(), 48, 48);
//            playerAttackRadius.intersect(new Area(meleeHitRadius));      // find intersection between Player attackbox and enemy hitbox
//            if (!playerAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
//                m.setHit(true);
////                m.takeDamage();   // TODO!!!!!!!!!
//                // takes damage and updates animation (HIT or DEAD)
//                return;
//            }
//        }
//        for (RangedEnemy r : rangedEnemies.values()) {
//            Area playerAttackRadius = new Area(this.playerAttackRadius);     // find area of Player's attack box
//            Ellipse2D.Float rangedHitRadius = new Ellipse2D.Float(r.getHelperX(), r.getHelperY(), 48, 48);
//            playerAttackRadius.intersect(new Area(rangedHitRadius));      // find intersection between Player attackbox and enemy hitbox
//            if (!playerAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
//                r.setHit(true);
////                r.takeDamage();   // TODO!!!!!!!!!!
//                // takes damage and updates animation (HIT or DEAD)
//                return;
//            }
//        }
    }

    @Override
    public void drawPlayerAttackRadius(Graphics g) {
        playerAttack.drawAttackRadius(g);
    }

    @Override
    public void drawPlayerHitRadius(Graphics g) {
        playerAttack.drawHitRadius(g);
    }

    @Override
    public void updatePlayer() {
        playerAttack.updatePlayer();
    }

    @Override
    public void updateMonsters() {
        playerAttack.updateMonsters();
    }

    @Override
    public BufferedImage getCurrAnimation() {
        return playerAttack.getCurrentImage();
    }

    @Override
    public void setAnimations(BufferedImage[][] animations) {
        playerAttack.setAnimations(animations);
    }

    @Override
    public BufferedImage[][] getAnimations() {
        return playerAttack.getAnimations();
    }

}
