package Entities;

public class MonsterConstants {
    public static final int LEFT_IDLE = 0;
    public static final int RIGHT_IDLE = 1;
    public static final int RIGHT_MOVEMENT = 2;
    public static final int LEFT_MOVEMENT = 3;
    public static final int RIGHT_ATTACK = 4;
    public static final int LEFT_ATTACK = 5;
    public static final int HIT = 6;
    public static final int DEAD = 7;

    public static int GetSpriteAmount(int enemy_state) {
        switch (enemy_state) {
            case LEFT_IDLE:
            case RIGHT_IDLE:
                return 7;
            case LEFT_MOVEMENT:
            case RIGHT_MOVEMENT:
            case RIGHT_ATTACK:
            case LEFT_ATTACK:
                return 5;
            case HIT:           // TODO: change values
            case DEAD:          // TODO: change values
                return 3;       // TODO: change values
        }
        return 0;
    }

}
