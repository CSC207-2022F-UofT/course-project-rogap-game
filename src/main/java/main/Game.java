package main;

import Use_Cases.ShopSystem;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 144;
    private final int UPS_SET = 144;


    // Game Timer Variables
    private static long startTime = System.currentTimeMillis();
    private static long gameTimerSeconds = 0;
    private long pauseTime = 0;


    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        // Focuses on what is happening here
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void update(){
        // Everything that needs to me updated, gets updated here :)
        gamePanel.player.update();
        gamePanel.key.update();
        gamePanel.potion.update();
        gamePanel.shopkeeper.update();
        gamePanel.enemyOne.update();
        gamePanel.updateGame();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public static int getGameTimerSeconds(){
        return (int) ((int)(gameTimerSeconds - startTime) / 1000F);}

    // Main game loop
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastCheck = System.currentTimeMillis();
        double deltaF = 0;
        int frames = 0;

        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        double deltaU = 0;
        int update = 0;


        while(true){
            // This is for the UPS check and update
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;


            if (deltaU >= 1){
                // Only update if the game is not paused
                if (!gamePanel.getIsPaused()){
                    update();
                    gameTimerSeconds = System.currentTimeMillis() - pauseTime;
                }else{
                    pauseTime = System.currentTimeMillis() - gameTimerSeconds;
                }
                update++;
                deltaU--;
            }

            // This is for the FPS check and repaint
            if (deltaF >= 1){
                if (!gamePanel.getIsPaused()){
                    gamePanel.repaint();
                }

                frames++;
                deltaF--;
            }

            // Display Stats
            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | UPS: " + update);
                frames= 0;
                update = 0;
            }
        }
    }
}