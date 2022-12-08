package Use_Cases;

import Entities.Enemy;
import Entities.MeleeEnemy;
import Entities.RangedEnemy;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class MonsterAttackInteractor implements MonsterAttackInputBoundary{
    private MonsterAttack monsterAttack;
    private ArrayList<Enemy> enemies;
    private PlayerAttack playerAttack;

    public MonsterAttackInteractor(ArrayList<Enemy> enemies, PlayerAttack playerAttack) {
        this.enemies = enemies;
        this.playerAttack = playerAttack;
    }


    @Override
    public void attack() {
        for (Enemy e : enemies) {
            // TODO: update ranged attack radius size
            Ellipse2D.Float enemyAttackRadius = new Ellipse2D.Float(e.getHelperX(), e.getHelperY(), 55, 55);
            Area playerHitRadius = new Area(playerAttack.getHitRadius());     // find area of enemy's attack radius
            playerHitRadius.intersect(new Area(enemyAttackRadius)); // find intersection between Player hit radius and enemy attack radius
            if (!enemyAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
//                r.setAttacking(true);
                playerAttack.setHit(true);
//                playerAttack.takeDamage();
                // takes damage and updates animation (HIT or DEAD)
                return;
            }
        }

//        for (MeleeEnemy m : meleeEnemies.values()) { // TODO: change this with enemy manager
//            Ellipse2D.Float meleeAttackRadius = new Ellipse2D.Float(m.getHelperX(), m.getHelperY(), 48, 48);
//            Area playerHitRadius = new Area(playerAttack.getHitRadius());     // find area of enemy's attack radius
//            playerHitRadius.intersect(new Area(meleeAttackRadius)); // find intersection between Player hit radius and enemy attack radius
//            if (!meleeAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
////                m.setAttacking(true);
//                playerAttack.setHit(true);
////                playerAttack.takeDamage();
//                // takes damage and updates animation (HIT or DEAD)
//                return;
//            }
//        }
//        for (RangedEnemy r : rangedEnemies.values()) {
//            // TODO: update ranged attack radius size
//            Ellipse2D.Float rangedAttackRadius = new Ellipse2D.Float(r.getHelperX(), r.getHelperY(), 55, 55);
//            Area playerHitRadius = new Area(playerAttack.getHitRadius());     // find area of enemy's attack radius
//            playerHitRadius.intersect(new Area(rangedAttackRadius)); // find intersection between Player hit radius and enemy attack radius
//            if (!rangedAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
////                r.setAttacking(true);
//                playerAttack.setHit(true);
////                playerAttack.takeDamage();
//                // takes damage and updates animation (HIT or DEAD)
//                return;
//            }
//        }
    }

    @Override
    public BufferedImage getCurrAttackHitAnimation() {
        return monsterAttack.getCurrentImage();
    }

    public BufferedImage getMonsterAttackAnimation() {
        return monsterAttack.getCurrentImage();  // TODO this should be called on all melee enemies
    }

    @Override
    public void drawMonstersHitRadius(Graphics g) {
        for (Enemy e : enemies) {
            e.drawMonsterHitRadius(g);
        }
    }
    @Override
    public void drawMonstersAttackRadius(Graphics g) {
        for (Enemy e : enemies) {
            e.drawMonsterAttackRadius(g);
        }
    }

    @Override
    public BufferedImage getCurrAnimation() {
        return null;
    }

    @Override
    public void setAnimations(BufferedImage[][] animations) {
        monsterAttack.setAnimations(animations);
    }

    @Override
    public BufferedImage[][] getAnimations() {
        return monsterAttack.getAnimations();
    }

}
