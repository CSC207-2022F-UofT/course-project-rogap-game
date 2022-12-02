package main;

import Entities.MeleeEnemy;
import Entities.Player;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import Use_Cases.ShopSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class GamePanel extends JPanel{
    public Player player;

    private ShopSystem gameShop;
    private JLabel timerGui;

    // Needs to be public

    private int xDelta = -2546, yDelta = -2132;

    private boolean isPaused = false;
    private boolean showMinimap = false;

    private boolean showStatBar = false;

    private ArrayList<Leaf> leafList = new ArrayList<>();
    private BufferedImage map;
    private BufferedImage minimap;
    private BufferedImage minimapCursor;
    private BufferedImage pauseIcon;
    private BufferedImage leaf;

    private MeleeEnemy[] enemyList;
    public MeleeEnemy enemyOne;
    public MeleeEnemy enemyTwo;
    // Has access to keyboard and mouse inputs


    private BufferedImage bushes;

    // Variables for shop system GUI
    private BufferedImage shopKeeper;
    private BufferedImage healthPotion;

    private BufferedImage statsBar;
    private BufferedImage healthBar;
    private BufferedImage timerPill;
    private BufferedImage buffbar;

    // Has access to keyboard and mouse inputs

    public GamePanel(){
        // Adding leaves
        enemyList = new MeleeEnemy[2];
        leafList.add(new Leaf());
        leafList.add(new Leaf());
        setTimerGui();

        // Initializing methods
        player = new Player(this);
        enemyOne = new MeleeEnemy(this, xDelta, yDelta, 3780, 3220);
        enemyTwo = new MeleeEnemy(this, xDelta, yDelta, 4000, 4000);
        enemyList[0] = enemyOne;
        enemyList[1] = enemyTwo;

        // Creates shop instance
        gameShop = new ShopSystem(player);


        importImage();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        this.setBackground(new Color(0, 0, 0));
    }
    private void setTimerGui(){
        timerGui = new JLabel(String.valueOf(120));
        timerGui.setBounds(605, -19, 100, 100);
        timerGui.setFont(new Font("Arial", Font.PLAIN, 35));
        timerGui.setForeground(new Color(150, 203, 187));
        add(timerGui);
    }
    private void changeTimerGui(){
        timerGui.setBounds(605, -19, 100, 100);
        timerGui.setHorizontalAlignment(0);
        if (Game.getGameTimerSeconds() % 2 == 0){
            timerGui.setForeground(new Color(150, 203, 187));
        }else{
            timerGui.setForeground(new Color(255, 81, 81, 194));
        }
        timerGui.setText(String.valueOf(120 - Game.getGameTimerSeconds()));

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
        InputStream bt = getClass().getResourceAsStream("/Bushes.png");
        InputStream sk = getClass().getResourceAsStream("/ShopKeeper.png");
        InputStream hp = getClass().getResourceAsStream("/HealthPotion.png");
        InputStream sb = getClass().getResourceAsStream("/StatsBar.png");
        InputStream hb = getClass().getResourceAsStream("/HealthBar.png");
        InputStream tp = getClass().getResourceAsStream("/TimerPill.png");
        InputStream bb = getClass().getResourceAsStream("/Buffbar.png");

        try {
            assert is != null;
            map = ImageIO.read(is);
            minimapCursor = ImageIO.read(mc);
            minimap = ImageIO.read(mm);
            pauseIcon = ImageIO.read(pi);
            leaf = ImageIO.read(lf);
            bushes = ImageIO.read(bt);
            shopKeeper = ImageIO.read(sk);
            healthPotion = ImageIO.read(hp);
            statsBar = ImageIO.read(sb);
            healthBar = ImageIO.read(hb);
            timerPill = ImageIO.read(tp);
            buffbar = ImageIO.read(bb);


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
                sb.close();
                hb.close();
                tp.close();
                bb.close();
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
    public int getXDelta () {return this.xDelta;}
    public int getYDelta () {return this.yDelta;}
    public void changeXDelta(int x) {this.xDelta += x; this.enemyOne.changeXEnemy(x); this.enemyTwo.changeXEnemy(x);
    }
    public void changeYDelta(int y) {this.yDelta += y; this.enemyOne.changeYEnemy(y); this.enemyTwo.changeYEnemy(y);
    }
    public boolean getIsPaused(){return this.isPaused;
    }
    public boolean getMinimapVisible(){
        return this.showMinimap;
    }
    public void setIsPaused(boolean set){
        this.isPaused = set;
    }

    public void setMinimapVisible(boolean set){
        this.showMinimap = set;
    }

    public void changeStatsBarVisible(){
        this.showStatBar = !this.showStatBar;
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

        // player VISUAL goes here
        g.drawImage(player.getCurrentImage(), 616, 326, 48,48, null);

        //Enemy visual goes here
        g.drawImage(player.getCurrentImage(), enemyOne.getXEnemy(), enemyOne.getYEnemy(), null);
        g.drawImage(player.getCurrentImage(), enemyTwo.getXEnemy(), enemyTwo.getYEnemy(), null);


        // SHOP VISUAL GOES HERE
        g.drawImage(shopKeeper, xDelta + 1857, yDelta + 1676, null);
        if (gameShop.getItemList().contains("Health Potion")){
            g.drawImage(healthPotion, xDelta + 1857, yDelta + 1726, null);
        }

        //Drawing the bushes
        g.drawImage(bushes, xDelta, yDelta, null);

        // TODO: Add health bar and animation

        // Timer GUi Goes here
        g.drawImage(timerPill, 607, 5, null);
        changeTimerGui();

        //HealthBar and Stats stuff go here
        if (!showMinimap && !isPaused){
            drawStats(g);
        }

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

    /**
     * Draw the Health Bar and the Stats Menu on the screen
     * @param g Graphics object used to draw
     */
    private void drawStats(Graphics g) {
        // Drawing the outside of the health bar
        g.drawImage(healthBar, 17, 14, null);
        // Drawing the health bar
        g.setColor( new Color(225, 50, 50) );
        g.fillRoundRect(95, 43, 275, 30, 27, 27);
        // Drawing the stats menu
        g.drawImage(buffbar, 495, 619, null);
        if (showStatBar){
            g.drawImage(statsBar, 9, 109, null);
        }
    }
}