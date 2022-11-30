package Entities;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public abstract class Creature {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    protected Ellipse2D.Float hitRadius;

    public Creature(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitbox(Graphics g, int xLvlOffset) {
        // For debugging the hitbox
        g.setColor(Color.GREEN);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void drawHitRadius(Graphics g, int xLvlOffset) {
        // For debugging the hitbox
        g.setColor(Color.PINK);
        g.drawOval((int) hitRadius.x - xLvlOffset, (int) hitRadius.y, (int) hitRadius.width, (int) hitRadius.height);
    }

    protected void initHitbox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void initHitRadius(float x, float y, int width, int height) {
        hitRadius = new Ellipse2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public Ellipse2D.Float getHitRadius() {
        return hitRadius;
    }

}