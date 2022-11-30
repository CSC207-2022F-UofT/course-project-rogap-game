package monster_attack_use_case;

import Entities.MeleeMonster;
import Entities.Monster;
import Entities.Player;
import Entities.RangedMonster;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MonsterAttackRequestModel {

    private Player player;   // player
    private Monster monster; // the monster that attacks

    public MonsterAttackRequestModel(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    Monster getMonster() {
        return monster;
    }

    void setMonster(Monster monster) {
        this.monster = monster;
    }

    Rectangle2D.Float getMonsterAttackRadius() {
        if (monster instanceof RangedMonster) {
            return ((RangedMonster) monster).getAttackBox();
        } else { // (monster instanceof MeleeMonster)
            return ((MeleeMonster) monster).getAttackBox();
        }
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


}