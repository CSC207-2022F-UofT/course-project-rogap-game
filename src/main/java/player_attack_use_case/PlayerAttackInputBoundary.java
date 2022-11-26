package player_attack_use_case;

public interface PlayerAttackInputBoundary {
    PlayerAttackResponseModel attack(PlayerAttackRequestModel requestModel);
}
