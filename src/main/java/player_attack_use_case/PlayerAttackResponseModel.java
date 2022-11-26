package player_attack_use_case;

// use case layer

import Entities.MeleeMonster;
import Entities.Player;
import Entities.RangedMonster;

public class PlayerAttackResponseModel {

    Player player;
    RangedMonster[] rangedMonsters;
    MeleeMonster[] meleeMonsters;

    public PlayerAttackResponseModel(Player player, RangedMonster[] rangedMonsters, MeleeMonster[] meleeMonsters) {
        this.player = player;
        this.rangedMonsters = rangedMonsters;
        this.meleeMonsters = meleeMonsters;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public RangedMonster[] getRangedMonsters() {
        return rangedMonsters;
    }

    public void setRangedMonsters(RangedMonster[] rangedMonsters) {
        this.rangedMonsters = rangedMonsters;
    }


    public MeleeMonster[] getMeleeMonsters() {
        return meleeMonsters;
    }

    public void setMeleeMonsters(MeleeMonster[] meleeMonsters) {
        this.meleeMonsters = meleeMonsters;
    }





}
