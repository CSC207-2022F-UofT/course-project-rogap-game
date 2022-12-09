package Use_Cases;

import Entities.Enemy;
import Entities.Player;
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
    private Enemy e1;
    private Enemy e2;
    private Player player;

    public MonsterAttackInteractor(ArrayList<Enemy> enemies, MonsterAttack monsterAttack, Player player) {
        this.monsterAttack = monsterAttack;
        this.e1 = enemies.get(0);
        this.e2 = enemies.get(1);
        this.player = player;
    }


    @Override
    public void attack() {
        System.out.println("asdfghj");
        System.out.println("yep");
        Ellipse2D.Float enemyAttackRadius = new Ellipse2D.Float(e1.getHelperX(), e1.getHelperY(), 36, 36);
        Area playerHitRadius = new Area(player.getHitRadius());     // find area of enemy's attack radius
        playerHitRadius.intersect(new Area(enemyAttackRadius)); // find intersection between Player hit radius and enemy attack radius
        if (!enemyAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
            e1.setAttacking(true);
            player.setHit(true);
        }

        Ellipse2D.Float enemyAttackRadius2 = new Ellipse2D.Float(e2.getHelperX(), e2.getHelperY(), 55, 55);
        Area playerHitRadius2 = new Area(player.getHitRadius());     // find area of enemy's attack radius
        playerHitRadius2.intersect(new Area(enemyAttackRadius2)); // find intersection between Player hit radius and enemy attack radius
        if (!enemyAttackRadius2.isEmpty()) {  // player attack box and monster hitbox do intersect
            e2.setAttacking(true);
            player.setHit(true);
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
        e1.drawMonsterHitRadius(g);
        e2.drawMonsterHitRadius(g);
    }
    @Override
    public void drawMonstersAttackRadius(Graphics g) {
        e1.drawMonsterAttackRadius(g);
        e2.drawMonsterAttackRadius(g);
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
