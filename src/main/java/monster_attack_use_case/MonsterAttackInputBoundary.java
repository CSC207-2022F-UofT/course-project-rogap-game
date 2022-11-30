package monster_attack_use_case;

public interface MonsterAttackInputBoundary {
    MonsterAttackResponseModel attack(MonsterAttackRequestModel requestModel);
}
