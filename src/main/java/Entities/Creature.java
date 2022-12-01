package Entities;

import main.GamePanel;

public abstract class Creature {
    public GamePanel gamePanel;

    public Creature(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


}
