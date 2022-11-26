package screens;

import Entities.MeleeMonster;
import Entities.Player;
import Entities.RangedMonster;
import player_attack_use_case.PlayerAttackInputBoundary;
import player_attack_use_case.PlayerAttackRequestModel;
import player_attack_use_case.PlayerAttackResponseModel;

public class GameplayController {

    final PlayerAttackInputBoundary attackInput;


    public GameplayController(PlayerAttackInputBoundary keyboardInput) {
        this.attackInput = keyboardInput;
    }

    PlayerAttackResponseModel attack(Player player, RangedMonster[] rangedMonsters, MeleeMonster[] meleeMonsters) {
        PlayerAttackRequestModel requestModel =
                new PlayerAttackRequestModel(player, rangedMonsters, meleeMonsters);

        return attackInput.attack(requestModel);
    }



}
