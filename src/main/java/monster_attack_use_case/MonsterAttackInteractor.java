package monster_attack_use_case;

import Entities.MeleeMonster;
import Entities.Monster;
import Entities.Player;
import Entities.RangedMonster;

import static utilz.Constants.EnemyConstants.ATTACK;
import static utilz.Constants.EnemyConstants.HIT;

public class MonsterAttackInteractor implements MonsterAttackInputBoundary {

    final MonsterAttackPresenter userPresenter;

    public MonsterAttackInteractor(MonsterAttackPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public MonsterAttackResponseModel attack(MonsterAttackRequestModel requestModel) {
        /*
        1. Set monster state to attacking = true (and load monster animation for attack (P))
        2. Check if monster's attack box intersects with the Player's hit radius
            a. If yes, set Player's state to hit (and load Player's animation for hit (P))
            b. reduce Player's health
        3. call userPresenter.prepareMonsterAttackView and userPresenter.presenterPlayerHitView (PPPPP)


        SHOULD THERE BE ONE VIEW METHOD THAT SHOWS BOTH THE MONSTER ATTACKING AND PLAYER BEING HIT AT THE SAME TIME????
         */

        // change monster's state to attacking = true
        requestModel.getMonster().newState(ATTACK);

        Monster monster = requestModel.getMonster();
        Player player = requestModel.getPlayer();

        if (requestModel.getMonsterAttackRadius().intersects(player.getHitbox())) {
            player.changeHealth(10);
        }

        MonsterAttackResponseModel monsterAttackResponseModel =
                new MonsterAttackResponseModel(monster, player);
        userPresenter.prepareMonsterAttackView(monsterAttackResponseModel);
        userPresenter.preparePlayerHitView(monsterAttackResponseModel);

        return null;
    }

}
