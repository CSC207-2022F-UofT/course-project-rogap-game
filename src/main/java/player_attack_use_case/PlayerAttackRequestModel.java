package player_attack_use_case;

import Entities.MeleeMonster;
import Entities.Player;
import Entities.RangedMonster;

import java.awt.geom.Ellipse2D;

public class PlayerAttackRequestModel {

    private Player player;   // player's hitradius

    // need to add instances of player width and height to initialize player??

    private RangedMonster[] rangedMonsters; // array containing all ranged monsters
    private MeleeMonster[] meleeMonsters; // array containing all melee monsters

    public PlayerAttackRequestModel(Player player,
                                    RangedMonster[] rangedMonsters, MeleeMonster[] meleeMonsters) {
        // keyPressed is spacebar
        this.player = player;
        this.rangedMonsters = rangedMonsters;
        this.meleeMonsters = meleeMonsters;
    }

    Player getPlayer() {
        return player;
    }
    void setPlayer(Player player) {   // i think we might not need this??
        this.player = player;
    }

    Ellipse2D.Float getPlayerHitRadius() {
        return player.getHitRadius();
    }

    RangedMonster[] getRangedMonsters() {
        return rangedMonsters;
    }
    void setRangedMonsters(RangedMonster[] rangedMonsters) {
        this.rangedMonsters = rangedMonsters;
    }

    MeleeMonster[] getMeleeMonsters() {
        return meleeMonsters;
    }
    void setMeleeMonsters(MeleeMonster[] meleeMonsters) {
        this.meleeMonsters = meleeMonsters;
    }




}
