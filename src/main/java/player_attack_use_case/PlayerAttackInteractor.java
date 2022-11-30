package player_attack_use_case;

import Entities.MeleeMonster;
import Entities.RangedMonster;

import static utilz.Constants.EnemyConstants.HIT;

public class PlayerAttackInteractor implements PlayerAttackInputBoundary{

    final PlayerAttackPresenter userPresenter;

    public PlayerAttackInteractor(PlayerAttackPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public PlayerAttackResponseModel attack(PlayerAttackRequestModel requestModel) {
        /*
        1. Set player state to attacking = true and load player animation for attack (P)
        2. Check for ranged monsters that were in player's attack radius
            a. if hit, set monster state to hit and load monster animation for hit (P)
            b. reduce health
        3. Check for melee monsters that were in player's attack radius
            a. if hit, set monster state to hit and load monster animation for hit (P)
            b. reduce health
        4. call userPresenter.preparePlayerAttackView and userPresenter.prepareMonsterHitView (PPPPPP)


        STILL NOT SURE WHERE TO UPDATE THE VIEW
        MAYBE MAKE TWO SEPERATE METHODS FOR PLAYER/MONSTERS???

        FOR THE MONSTER.NEWSTATE() METHOD, IT IS USING CONSTANTS. NEED TO MOVE THEM TO
        MONSTER CLASS OR CAN KEEP A CONSTANTS CLASS??????
         */

        // change player's state to attacking = true
        requestModel.getPlayer().setAttacking(true);

        for (RangedMonster r : requestModel.getRangedMonsters()) { // search through ranged monsters
            if (r.isActive()) {
                if (requestModel.getPlayerAttackRadius().intersects(r.getHitbox())) {
                    // if player's attack radius intersects with a ranged monster's hitbox:
                    r.newState(HIT);
                    r.hurt(10); // reduce health
                }
            }
        }
        for (MeleeMonster m : requestModel.getMeleeMonsters()) { // search through melee monsters
            if (m.isActive()) {
                if (requestModel.getPlayerAttackRadius().intersects(m.getHitbox())) {
                    // if player's attack radius intersects with a melee monster's hitbox:
                    m.newState(HIT);
                    m.hurt(10); // reduce health
                }
            }
        }

        PlayerAttackResponseModel playerAttackResponseModel =
                new PlayerAttackResponseModel(requestModel.getPlayer(),
                        requestModel.getRangedMonsters(),
                        requestModel.getMeleeMonsters());
        userPresenter.preparePlayerAttackView(playerAttackResponseModel);
        userPresenter.prepareRangedHitView(playerAttackResponseModel);
        userPresenter.prepareMeleeHitView(playerAttackResponseModel);



        return null;
    }

}
