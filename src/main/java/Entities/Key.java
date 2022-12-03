package Entities;


import Use_Cases.Purchasable;
import main.GamePanel;

public class Key extends StaticAnimatedSprite implements Purchasable {
    // == Constructor ==
    public Key(GamePanel gamePanel) {
        super(gamePanel);
        importImage("/Key.png");
        loadAnimation(40, 48, 6);
    }
    public int getPrice(){
        return 100;
    }
}
