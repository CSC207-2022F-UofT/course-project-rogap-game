package Entities;

public class Key extends StaticAnimatedSprite implements Purchasable {
    // == Constructor ==
    public Key() {
        importImage("/Key.png");
        loadAnimation(40, 48, 6);
    }
    public int getPrice(){
        return 100;
    }
}
