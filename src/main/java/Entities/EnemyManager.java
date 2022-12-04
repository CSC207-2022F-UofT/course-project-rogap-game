package Entities;

import main.GamePanel;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Entities.MonsterConstants.*;

public class EnemyManager {

    private GamePanel gamePanel;
    private int xDelta = -2546, yDelta = -2132;

    private BufferedImage[][] rangedArr;
    private ArrayList<RangedEnemy> rangedEnemies = new ArrayList<>();
    private BufferedImage[][] meleeArr;
    private ArrayList<MeleeEnemy> meleeEnemies = new ArrayList<>();

    public EnemyManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        addEnemies();
    }

    private void addEnemies() {  // TODO: update with monster spawn methods
        MeleeEnemy meleeOne = new MeleeEnemy(gamePanel, xDelta, yDelta, 3780, 3220);
        MeleeEnemy meleeTwo = new MeleeEnemy(gamePanel, xDelta, yDelta, 4000, 4000);
        meleeEnemies.add(meleeOne);
        meleeEnemies.add(meleeTwo);
        RangedEnemy rangedOne = new RangedEnemy(gamePanel, xDelta, yDelta, 3820, 3220);
        RangedEnemy rangedTwo = new RangedEnemy(gamePanel, xDelta, yDelta, 4020, 4000);
        rangedEnemies.add(rangedOne);
        rangedEnemies.add(rangedTwo);
    }

    // check if monsters took damage from player's attacks
    public void checkMonsterHit(Ellipse2D.Float attackRadius) {
        for (RangedEnemy r : rangedEnemies) {
            Area playerAttackRadius = new Area(attackRadius);     // find area of Player's attack box
            playerAttackRadius.intersect(new Area(r.getHitBox()));      // find intersection between Player attackbox and enemy hitbox
            if (!playerAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
                r.takeDamage();   // takes damage and updates animation (HIT or DEAD)
                return;
            }
        }
        for (MeleeEnemy m : meleeEnemies) {
            Area playerAttackRadius = new Area(attackRadius);     // find area of Player's attack box
            playerAttackRadius.intersect(new Area(m.getHitBox()));      // find intersection between Player attackbox and enemy hitbox
            if (!playerAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
                m.takeDamage();   // takes damage and updates animation (HIT or DEAD)
                return;
            }
        }
    }

    public void update() {
        for (RangedEnemy r : rangedEnemies)
            r.update();
        for (MeleeEnemy m : meleeEnemies)
            m.update();
    }




}
