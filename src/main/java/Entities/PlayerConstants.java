package Entities;

public class PlayerConstants {
    // TODO: change values
    public static final int LEFT_IDLE = 0;
    public static final int RIGHT_IDLE = 1;
    public static final int RIGHT_MOVEMENT = 2;
    public static final int LEFT_MOVEMENT = 3;
    public static final int RIGHT_ATTACK = 4;
    public static final int LEFT_ATTACK = 5;
    public static final int HIT = 6;
    public static final int DEAD = 7;

    public static int GetSpriteAmount(int player_action) {
        switch (player_action) {
            case LEFT_IDLE:
            case RIGHT_IDLE:
                return 6;
            case LEFT_MOVEMENT:
            case RIGHT_MOVEMENT:
                return 5;
            case RIGHT_ATTACK:  // TODO: change values
            case LEFT_ATTACK:   // TODO: change values
            case HIT:
            case DEAD:
                return 3;       // TODO: change values
        }
        return 0;
    }

}
