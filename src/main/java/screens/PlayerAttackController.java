package screens;

import Gabi_attack_method_code.MeleeMonster;
import Gabi_attack_method_code.Player;
import Gabi_attack_method_code.RangedMonster;
import player_attack_use_case.PlayerAttackInputBoundary;
import player_attack_use_case.PlayerAttackRequestModel;
import player_attack_use_case.PlayerAttackResponseModel;

public class PlayerAttackController {

    final PlayerAttackInputBoundary playerAttackInput;


    public PlayerAttackController(PlayerAttackInputBoundary keyboardInput) {
        this.playerAttackInput = keyboardInput;
    }

    PlayerAttackResponseModel attack(Player player, RangedMonster[] rangedMonsters, MeleeMonster[] meleeMonsters) {
        PlayerAttackRequestModel requestModel =
                new PlayerAttackRequestModel(player, rangedMonsters, meleeMonsters);

        return playerAttackInput.attack(requestModel);
    }



}
