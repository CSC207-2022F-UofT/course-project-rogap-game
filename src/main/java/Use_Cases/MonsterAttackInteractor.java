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
    private Enemy e1;
    private Enemy e2;

    public MonsterAttackInteractor(ArrayList<Enemy> enemies, PlayerAttack playerAttack) {
        this.e1 = enemies.get(0);
        this.e2 = enemies.get(1);
        this.playerAttack = playerAttack;
    }


    @Override
    public void attack() {
        Ellipse2D.Float enemyAttackRadius = new Ellipse2D.Float(e1.getHelperX(), e1.getHelperY(), 36, 36);
        Area playerHitRadius = new Area(playerAttack.getHitRadius());     // find area of enemy's attack radius
        playerHitRadius.intersect(new Area(enemyAttackRadius)); // find intersection between Player hit radius and enemy attack radius
        if (!enemyAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
            e1.setAttacking(true);
            playerAttack.setHit(true);
        }

        Ellipse2D.Float enemyAttackRadius2 = new Ellipse2D.Float(e2.getHelperX(), e2.getHelperY(), 55, 55);
        Area playerHitRadius2 = new Area(playerAttack.getHitRadius());     // find area of enemy's attack radius
        playerHitRadius2.intersect(new Area(enemyAttackRadius2)); // find intersection between Player hit radius and enemy attack radius
        if (!enemyAttackRadius2.isEmpty()) {  // player attack box and monster hitbox do intersect
            e2.setAttacking(true);
            playerAttack.setHit(true);
        }
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
