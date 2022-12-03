package Entities;

//import Use_Cases.Purchasable;
import Use_Cases.Purchasable;
import main.GamePanel;

public class Potion extends StaticAnimatedSprite implements Purchasable {
    // == Constructor ==
    public Potion(GamePanel gamePanel, String potion_type) {
        super(gamePanel);
        super.importImage(potion_type);
        super.loadAnimation(32, 45, 5);
    }
    public int getPrice(){
        return 100;
    }
}
