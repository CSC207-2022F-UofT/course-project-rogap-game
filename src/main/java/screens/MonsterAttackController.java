package screens;

import Gabi_attack_method_code.Monster;
import Gabi_attack_method_code.Player;
import monster_attack_use_case.MonsterAttackInputBoundary;
import monster_attack_use_case.MonsterAttackRequestModel;
import monster_attack_use_case.MonsterAttackResponseModel;

public class MonsterAttackController {

    final MonsterAttackInputBoundary monsterAttackInput;


    public MonsterAttackController(MonsterAttackInputBoundary keyboardInput) {
        this.monsterAttackInput = keyboardInput;
    }

    MonsterAttackResponseModel attack(Monster monster, Player player) {
        MonsterAttackRequestModel requestModel =
                new MonsterAttackRequestModel(monster, player);

        return monsterAttackInput.attack(requestModel);
    }
}
