package monster_attack_use_case;

// use case layer

public interface MonsterAttackPresenter {

    MonsterAttackResponseModel prepareMonsterAttackView(MonsterAttackResponseModel monsterAttackResponseModel);

    MonsterAttackResponseModel preparePlayerHitView(MonsterAttackResponseModel monsterAttackResponseModel);

}
