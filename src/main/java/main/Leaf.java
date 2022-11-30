package main;

import java.util.Random;

public class Leaf {
    private int x = randomCoord(), y = 0;

    public void positionChange(){
        x++;
        y++;

        if (x>= 1280 || y >= 720){
            x = randomCoord();
            y = 0;
        }
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public int randomCoord(){
        Random r = new Random();
        return r.nextInt((1200 - (-1200)) + 1) -1200;
    }
}