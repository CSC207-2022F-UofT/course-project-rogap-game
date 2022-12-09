package Interface_Adapters;

import Frameworks.GameWindow;
import Use_Cases.GameLoopInteractorReference;

/**
 * Manager class that calls various use cases and uses presenter to update view
 */
public class GameLoopManager implements Runnable, GameLoopInteractorReference {

    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 120;


    // Game Timer Variables
    private static long startTime = System.currentTimeMillis();
    private static long gameTimerSeconds = 0;
    private long pauseTime = 0;

    private static boolean isPaused = false;

    private static boolean showMinimap = false;
    private static boolean showStats = false;

    GameScreenPresenter gameScreenPresenter;
    UpdateScreenBoundary screenModel;
    PlayerMovementController playerMovementController;
    CreateEnemyController createEnemyController;
    WriteToBoardController writeToBoardController;

    /**
     * Passing in the controllers KeyboardInputs need to handle when key-pressed
     * that will be used to perform various use-cases.
     * @param gameScreenPresenter: This game screen presenter is used to repaint the screen
     * @param playerMovementController: This movement controller is used to perform movement use case
     * @param createEnemyController: This controller is used to create enemy controller
     * @param writeToBoardController: This controller is used to perform leaderboard writing use case
     */
    public GameLoopManager(GameScreenPresenter gameScreenPresenter, PlayerMovementController playerMovementController,
                           CreateEnemyController createEnemyController, WriteToBoardController writeToBoardController){

        this.gameScreenPresenter = gameScreenPresenter;
        this.playerMovementController = playerMovementController;
        this.createEnemyController = createEnemyController;
        this.writeToBoardController = writeToBoardController;

        screenModel = gameScreenPresenter.create();
        new GameWindow(screenModel);
        screenModel.requestFocus();
    }

    public void start(){
        startGameLoop();
    }

    /**
     * Update the player's location whenever the player moves, based on the designated controller
     */
    public void update(){
        playerMovementController.update();
        createEnemyController.updateEnemies(playerMovementController.getVelX(),
                playerMovementController.getVelY());
    }

    /**
     * Redrawing everything in our Jpanel to update any changes
     */
    public void reDraw(){
        gameScreenPresenter.update();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // returns game time
    public static int getGameTimerSeconds(){
        return (int) ((int)(gameTimerSeconds - startTime) / 1000F);
    }
    public static boolean getIsPaused(){
        return isPaused;
    }
    public void changeIsPaused(){
        isPaused = !isPaused;
    }

    public static boolean getMinimapVisible(){
        return showMinimap;
    }
    public void changeMinimapVisible(){
        showMinimap = !showMinimap;
    }
    public static boolean getStatsVisible(){
        return showStats;
    }
    public void changeStatsVisible() {
        showStats = !showStats;
    }

    /**
     * This method represents the game-loop:
     * - It keeps track of game time,
     * - Calls update() and reDraw() methods.
     */
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double deltaF = 0;

        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        double deltaU = 0;


        while(true){
            // This is for the UPS check and update
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                // Only update if the game is not paused
                if (!getIsPaused()){
                    update();
                    gameTimerSeconds = System.currentTimeMillis() - pauseTime;
                }else{
                    pauseTime = System.currentTimeMillis() - gameTimerSeconds;
                }
                deltaU--;
            }

            // This is for the FPS check and repaint
            if (deltaF >= 1){
                if (!getIsPaused()){
                    reDraw();
                }
                deltaF--;
            }
        }
    }
}