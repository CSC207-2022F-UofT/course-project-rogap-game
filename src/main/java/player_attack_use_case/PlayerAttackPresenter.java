package player_attack_use_case;

// use case layer

public interface PlayerAttackPresenter {

    PlayerAttackResponseModel preparePlayerAttackView(PlayerAttackResponseModel player);

    PlayerAttackResponseModel prepareRangedHitView(PlayerAttackResponseModel player);

    PlayerAttackResponseModel prepareMeleeHitView(PlayerAttackResponseModel player);

}
