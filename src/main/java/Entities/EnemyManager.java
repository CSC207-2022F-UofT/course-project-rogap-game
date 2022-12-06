package Entities;

import main.GamePanel;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Entities.MonsterConstants.*;

/**
 * This class manages all enemies of the game. It contains array lists of all Ranged monsters and Melee monsters,
 * allowing for all monsters to be accessed updated at once.
 */
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

    /**
     * Creates new enemies, spawns them on the map, and adds each one to their respective array list.
     */
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

    /**
     * Checks if any monsters took damage from the Player's attack. This method is called when the Player's attacking
     * state is true. For each monster in the game, it checks for the intersection between the Player's attack radius
     * and the monster's hitbox. If there is an intersection, meaning that the monster was within the Player's attack
     * radius, the monster takes damage. It calls the method takeDamage to do so.
     * @param attackRadius
     */
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

    /**
     * This method is called once in SOMETHING!!!!!!!!!!!!! and it updates all monsters in the game.
     */
    public void update() {
        for (RangedEnemy r : rangedEnemies)
            r.update();
        for (MeleeEnemy m : meleeEnemies)
            m.update();
    }




}
