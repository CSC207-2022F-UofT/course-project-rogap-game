package player_attack_use_case;

// use case layer

public interface PlayerAttackPresenter {

    PlayerAttackResponseModel preparePlayerAttackView(PlayerAttackResponseModel playerAttackResponseModel);

    PlayerAttackResponseModel prepareRangedHitView(PlayerAttackResponseModel playerAttackResponseModel);

    PlayerAttackResponseModel prepareMeleeHitView(PlayerAttackResponseModel playerAttackResponseModel);

}
