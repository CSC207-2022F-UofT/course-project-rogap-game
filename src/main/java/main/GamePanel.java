package main;

import Entities.MeleeEnemy;
import Entities.Player;
import Entities.Key;
import Entities.Potion;
import Entities.Shopkeeper;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import Use_Cases.ShopSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class GamePanel extends JPanel{
    public Player player;
    public Key key;
    public Potion potion;
    public Shopkeeper shopkeeper;
    private ShopSystem gameShop;
    private JLabel timerGui;

    // Needs to be public
    private int xDelta = -2546, yDelta = -2132;

    private boolean isPaused = false;
    private boolean showMinimap = false;
    private ArrayList<Leaf> leafList = new ArrayList<>();

    private BufferedImage map;
    private BufferedImage minimap;
    private BufferedImage minimapCursor;
    private BufferedImage pauseIcon;
    private BufferedImage leaf;

    private BufferedImage bushes;

    // Variables for shop system GUI
//    private BufferedImage shopKeeper;
//    private BufferedImage healthPotion;
//    private BufferedImage keySprite;

    public MeleeEnemy enemyOne;
    // Has access to keyboard and mouse inputs
    public GamePanel(){
        // Adding leaves
        leafList.add(new Leaf());
        leafList.add(new Leaf());
        setTimerGui();

        // Initializing methods
        player = new Player(this, xDelta, yDelta);
        key = new Key(this, xDelta, yDelta);
        potion = new Potion(this, xDelta, yDelta);
        shopkeeper = new Shopkeeper(this, xDelta, yDelta);
        enemyOne = new MeleeEnemy(this, xDelta, yDelta, 3780, 3220);
        // Creates shop instance
        gameShop = new ShopSystem(player);

        importImage();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        this.setBackground(new Color(0, 0, 0));
    }
    private void setTimerGui(){
        timerGui = new JLabel(String.valueOf(120));
        timerGui.setBounds(640, 20, 100, 100);
        timerGui.setFont(new Font("Onyx", Font.PLAIN, 35));
        timerGui.setForeground(new Color(150, 203, 187));
        add(timerGui);
    }
    private void changeTimerGui(){
//        if (Game.getGameTimerSeconds() % 2 == 0){
//            timerGui.setForeground(new Color(150, 203, 187));
//        }else{
//            timerGui.setForeground(new Color(224, 68, 78));
//        }
        if (Game.getGameTimerSeconds() % 2 == 0){
            timerGui.setForeground(new Color(150, 203, 187));
        }else{
            timerGui.setForeground(new Color(255, 81, 81, 194));
        }
        timerGui.setText(String.valueOf(120 - Game.getGameTimerSeconds()));

    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/Floor1.png");
        InputStream mc = getClass().getResourceAsStream("/MapCursor.png");
        InputStream mm = getClass().getResourceAsStream("/Minimap.png");
        InputStream pi = getClass().getResourceAsStream("/Paused.png");
        InputStream lf = getClass().getResourceAsStream("/Leaf.png");
        InputStream bt = getClass().getResourceAsStream("/Bushes.png");
        InputStream sk = getClass().getResourceAsStream("/ShopKeeper.png");
        InputStream hp = getClass().getResourceAsStream("/HealthPotion.png");
        InputStream ky = getClass().getResourceAsStream("/Key.png");

        try {
            assert is != null;
            map = ImageIO.read(is);
            minimapCursor = ImageIO.read(mc);
            minimap = ImageIO.read(mm);
            pauseIcon = ImageIO.read(pi);
            leaf = ImageIO.read(lf);
            bushes = ImageIO.read(bt);
//            shopKeeper = ImageIO.read(sk);
//            healthPotion = ImageIO.read(hp);
//            keySprite = ImageIO.read(ky);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                mc.close();
                mm.close();
                pi.close();
                lf.close();
                bt.close();
                sk.close();
                hp.close();
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
    public void changeXDelta(int x) {this.xDelta += x; this.enemyOne.changeXEnemy(x);
    }
    public void changeYDelta(int y) {this.yDelta += y; this.enemyOne.changeYEnemy(y);}
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

    // This is for drawing stuff
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Drawing the basic map
        g.drawImage(map, xDelta, yDelta, null);


        // TODO: Character visuals go here
        g.drawImage(player.getCurrentImage(), 615, 325, 48,48, null);
        g.drawImage(player.getCurrentImage(), enemyOne.getxEnemy(), enemyOne.getyEnemy(), null);

        // SHOP VISUAL GOES HERE
        g.drawImage(shopkeeper.getCurrentImage(), xDelta + 1857, yDelta + 1676, null);
        if (gameShop.getItemList().contains("Health Potion")){
//            g.drawImage(healthPotion, xDelta + 1857, yDelta + 1726, null);
            g.drawImage(potion.getCurrentImage(), xDelta + 1840, yDelta + 1732, null);
        }
        if (gameShop.getItemList().contains("Key")){
            g.drawImage(key.getCurrentImage(), xDelta + 1950, yDelta + 1729, null);
        }
        //Drawing the bushes
        g.drawImage(bushes, xDelta, yDelta, null);

        // TODO: Add health bar and animation

        // Timer GUi Goes here
        changeTimerGui();

        animateLeaf(g, leafList);

        // Pause menu
        if (getIsPaused()){
            g.setColor(new Color(162, 155, 155, 139));
            g.fillRect(0, 0, 1280, 720);
            if (!showMinimap){
                g.drawImage(pauseIcon, 500, 295, null);
            }
        }

        //Showing Minimap
        if (showMinimap){
            g.drawImage(minimap, -52 + xDelta/7, -130 + yDelta/6, null);
            g.drawImage(minimapCursor, 598, 284, null);
        }

        gameShop.checkLocation();

    }

}