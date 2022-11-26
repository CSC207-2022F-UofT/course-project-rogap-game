package screens;

// Interface adapters layer

import player_attack_use_case.PlayerAttackPresenter;
import player_attack_use_case.PlayerAttackResponseModel;

public class GameplayScreen implements PlayerAttackPresenter {
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



}
