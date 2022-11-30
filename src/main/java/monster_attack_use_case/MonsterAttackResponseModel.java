package monster_attack_use_case;

// use case layer

import Entities.Monster;
import Entities.Player;

public class MonsterAttackResponseModel {

    Monster monster;
    Player player;

    public MonsterAttackResponseModel(Monster monster, Player player) {
        this.monster = monster;
        this.player = player;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }



}
