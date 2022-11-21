package main;

import Entities.MeleeMonster;
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
    private JLabel timerGui;

    private int xDelta = -2546, yDelta = -2132;
    private boolean isPaused = false;
    private boolean showMinimap = false;
    private ArrayList<Leaf> leafList = new ArrayList<>();

    private BufferedImage map;
    private BufferedImage minimap;
    private BufferedImage minimapCursor;
    private BufferedImage pauseIcon;
    private BufferedImage leaf;


    public MeleeMonster enemyOne;
    // Has access to keyboard and mouse inputs
    public GamePanel(){
        // Adding leaves
        leafList.add(new Leaf());
        leafList.add(new Leaf());
        setTimerGui();

        // Initializing methods
        player = new Player(this, xDelta, yDelta);
        enemyOne = new MeleeMonster(this, xDelta, yDelta, 3780, 3220);
        importImage();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        this.setBackground(new Color(0, 0, 0));
    }

    private void setTimerGui(){
        timerGui = new JLabel(String.valueOf(300));
        timerGui.setBounds(640, 20, 100, 100);
        timerGui.setFont(new Font("Onyx", Font.PLAIN, 35));
        timerGui.setForeground(new Color(150, 203, 187));
        add(timerGui);
    }
    private void changeTimerGui(){
        if (Game.getGameTimerSeconds() % 2 == 0){
            timerGui.setForeground(new Color(150, 203, 187));
        }else{
            timerGui.setForeground(new Color(224, 68, 78));
        }
        timerGui.setText(String.valueOf(300 - Game.getGameTimerSeconds()));
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

        //Image image = img.getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        g.drawImage(map, xDelta, yDelta, null);


        // player VISUAL goes here
        g.drawImage(player.getCurrentImage(), 615, 325, 48,48, null);

        //Enemy visual goes here
        g.drawImage(player.getCurrentImage(), enemyOne.getxEnemy(), enemyOne.getyEnemy(), null);

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

    }

}
