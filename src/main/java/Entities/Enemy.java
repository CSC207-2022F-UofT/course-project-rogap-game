package Entities;

import Frameworks.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Enemy {
    public abstract int getVisualX();
    public abstract int getVisualY();
    public abstract int getHelperX();
    public abstract int getHelperY();
    public abstract void changeVisualX(int x);
    public abstract void changeVisualY(int y);
    public abstract void changeHelperX(int x);
    public abstract void changeHelperY(int y);
    public abstract Rectangle getHitBox();
    public abstract void setAnimations(BufferedImage[][] animations);
    public abstract BufferedImage[][] getAnimations();
}