package Entities;


import main.GamePanel;

abstract class Potion extends StaticAnimatedSprite implements Purchasable {
    private final int HEIGHT = 45;
    private final int WIDTH = 32;

    // == Constructor ==
    public Potion(String potion_type) {
        super.importImage(potion_type);
        super.loadAnimation(32, 45, 5);
    }
    public int getHeight(){
        return HEIGHT;
    }
    public int getWidth(){
        return WIDTH;
    }
}
