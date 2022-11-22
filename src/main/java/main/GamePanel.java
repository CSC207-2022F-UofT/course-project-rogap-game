package main;

import Entities.MeleeEnemy;
import Entities.Player;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class GamePanel extends JPanel{
    public Player player;
    private int xDelta = -2546, yDelta = -2132;
    private boolean isPaused = false;
    private boolean showMinimap = false;
    private ArrayList<Leaf> leafList = new ArrayList<>();


    private BufferedImage map;
    private BufferedImage minimap;
    private BufferedImage minimapCursor;
    private BufferedImage pauseIcon;
    private BufferedImage leaf;
    private MeleeEnemy[] enemyList;
    private Rectangle[] wallLayout = new Rectangle[4];
    public MeleeEnemy enemyOne;
    public MeleeEnemy enemyTwo;
    // Has access to keyboard and mouse inputs
    public GamePanel(){
        // Adding leaves
        enemyList = new MeleeEnemy[2];
        leafList.add(new Leaf());
        leafList.add(new Leaf());

        // Initializing methods
        player = new Player(this, xDelta, yDelta);
        enemyOne = new MeleeEnemy(this, xDelta, yDelta, 3780, 3220);
        enemyTwo = new MeleeEnemy(this, xDelta, yDelta, 4000, 4000);
        enemyList[0] = enemyOne;
        enemyList[1] = enemyTwo;
        importImage();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        this.setBackground(new Color(0, 0, 0));
    }

    public MeleeEnemy[] getEnemyList() {
        return this.enemyList;
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/Floor1.png");
        InputStream mc = getClass().getResourceAsStream("/MapCursor.png");
        InputStream mm = getClass().getResourceAsStream("/Minimap.png");
        InputStream pi = getClass().getResourceAsStream("/Paused.png");
        InputStream lf = getClass().getResourceAsStream("/Leaf.png");

        try {
            assert is != null;
            map = ImageIO.read(is);
            minimapCursor = ImageIO.read(mc);
            minimap = ImageIO.read(mm);
            pauseIcon = ImageIO.read(pi);
            leaf = ImageIO.read(lf);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                mc.close();
                mm.close();
                pi.close();
                lf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPointerLocation(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }
    public void updateGame(){
    }

    public void changeXDelta(int x) {this.xDelta += x; this.enemyOne.changeXEnemy(x);this.enemyTwo.changeXEnemy(x);
    }
    public void changeYDelta(int y) {this.yDelta += y; this.enemyOne.changeYEnemy(y);this.enemyTwo.changeYEnemy(y);
    }
    public boolean getIsPaused(){return this.isPaused;
    }
    public void setIsPaused(boolean set){
        this.isPaused = set;
    }

    public void setMinimapVisible(boolean set){
        this.showMinimap = set;
    }

    public boolean getMinimapVisible(){
        return this.showMinimap;
    }

    public void animateLeaf(Graphics g, ArrayList<Leaf> alist){
        for (Leaf curr : alist ){
            g.drawImage(leaf, curr.getX(), curr.getY(), null);
            curr.positionChange();
        }
    }
    public void createWallLayout(int[][] verticalWallHelper, int[][] horizontalWallHelper) {
        int zeroX = xDelta + 2546 - 1265;
        int zeroY = yDelta + 2132 - 1410;
        int i = 0;
        for (int[] wall : verticalWallHelper) {
                if (wall[2] == 0) {
                    wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY + (wall[1] * 705), 15, 250);
                    i += 1;
                    wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY + (wall[1] * 705) + 470, 15, 250);
                    i += 1;
                } else {
                    wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY + (wall[1] * 705) , 15, 720);
                    i += 1;
                }
            }
        for (int[] wall: horizontalWallHelper) {
            if (wall[2] == 0) {
                wallLayout[i] = new Rectangle(zeroX + (wall[0] * 1265), zeroY+ (wall[1] * 705), 490, 15);
                i += 1;
                wallLayout[i] = new Rectangle(zeroX + 770 + (wall[0] * 1265), zeroY + (wall[1] * 705), 510, 15);
                i += 1;
            } else {
                wallLayout[i] = new Rectangle(zeroX+ (wall[0] * 1265), zeroY + (wall[1] * 705), 1280, 15);
                i += 1;
            }
        }
        }

    // This is for drawing stuff
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Image image = img.getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        g.drawImage(map, xDelta, yDelta, null);

        // player VISUAL goes here
        g.drawImage(player.getCurrentImage(), 616, 326, 48,48, null);

        //Enemy visual goes here
        g.drawImage(player.getCurrentImage(), enemyOne.getxEnemy(), enemyOne.getyEnemy(), null);
        g.drawImage(player.getCurrentImage(), enemyTwo.getxEnemy(), enemyTwo.getyEnemy(), null);

        //Walls
        g.setColor(Color.pink);
        int[][] testingVER = new int[2][3];
        int[][] testingHOR = new int[2][3];
        int[] cord1 = {0,0,1};
        int[] cord2 = {0,0,1};
        int[] cord3 = {1,2,1};
        int[] cord4 = {1,2,1};
        testingVER[0] = cord1;
        testingVER[1] = cord3;

        testingHOR[0] = cord2;
        testingHOR[1] = cord4;
        this.createWallLayout(testingVER, testingHOR);
        for (Rectangle wall: wallLayout) {
            g.drawRect(wall.x, wall.y, wall.width, wall.height);
        }
        //TODO Move to health bar file
        // Health bar goes here
        //g.setColor(new Color(0, 0, 0, 194));
        //g.fillRect(618, 312, 45, 13);

        // TODO: Scale the green bar according to health
        // TODO: Change color of health bar based on current health
        //g.setColor(new Color(46, 175, 127, 255));
        // g.fillRect(620, 314, 41, 9);


        // Pause menu
        if (getIsPaused()){
            g.setColor(new Color(162, 155, 155, 139));
            g.fillRect(0, 0, 1280, 720);
            if (!showMinimap){
                g.drawImage(pauseIcon, 500, 295, null);
            }
        }else{
            if(!showMinimap){
                animateLeaf(g, leafList);
            }
        }

        //Showing Minimap
        if (showMinimap){
            g.drawImage(minimap, -52 + xDelta/7, -130 + yDelta/6, null);
            g.drawImage(minimapCursor, 598, 284, null);
        }

    }

}
