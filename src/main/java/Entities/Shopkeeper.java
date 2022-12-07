package Entities;

import main.GamePanel;

public class Shopkeeper extends StaticAnimatedSprite{
    // == Constructor ==
    public Shopkeeper() {
        importImage("/ShopKeeper.png");
        loadAnimation(100, 100, 10);
    }
}
