package screens;

// Interface adapters layer

import player_attack_use_case.PlayerAttackPresenter;
import player_attack_use_case.PlayerAttackResponseModel;
import monster_attack_use_case.MonsterAttackPresenter;
import monster_attack_use_case.MonsterAttackResponseModel;

public class GameplayScreen implements PlayerAttackPresenter, MonsterAttackPresenter {
    // From PlayerAttackPresenter
    @Override
    public PlayerAttackResponseModel preparePlayerAttackView(PlayerAttackResponseModel playerAttackResponseModel) {
        playerAttackResponseModel.getPlayer().update(); // update with new inputs (player.attacking(true)
        return null;
    }

    @Override
    public PlayerAttackResponseModel prepareRangedHitView(PlayerAttackResponseModel playerAttackResponseModel) {
        playerAttackResponseModel.getRangedMonsters().update();
        return null;
    }

    @Override
    public PlayerAttackResponseModel prepareMeleeHitView(PlayerAttackResponseModel playerAttackResponseModel) {
        playerAttackResponseModel.getMeleeMonsters().update();
        return null;
    }

    // From MonsterAttackPresenter
    @Override
    public MonsterAttackResponseModel prepareMonsterAttackView(MonsterAttackResponseModel monsterAttackResponseModel) {
        monsterAttackResponseModel.getMonster().update();
        return null;
    }

    @Override
    public MonsterAttackResponseModel preparePlayerHitView(MonsterAttackResponseModel monsterAttackResponseModel) {
        monsterAttackResponseModel.getPlayer().update();
        return null;
    }
}
