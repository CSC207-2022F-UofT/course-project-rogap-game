package main;

import Frameworks.GamePanel;
import Frameworks.GameWindow;
import Interface_Adapters.GameLoopManager;
import Interface_Adapters.GameScreenPresenter;
import Interface_Adapters.PauseGameController;
import Interface_Adapters.UpdateScreenModel;
import Use_Cases.PauseGame;
import Use_Cases.PauseGameInputBoundary;

public class MainClass {
    public static void main(String[] args) {

/*
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Welcome " + userName + "!");  // Output user input
*/
        UpdateScreenModel screenModel = new GamePanel();
        GameScreenPresenter presenter = new GameScreenPresenter(screenModel);
        //GameWindow application = new GameWindow(presenter);

        GameLoopManager gameManager = new GameLoopManager(presenter);
        PauseGameInputBoundary pauseGameInteractor = new PauseGame();
        PauseGameController pauseGameController = new PauseGameController(pauseGameInteractor, gameManager);

        screenModel.setUp(pauseGameController);
        gameManager.start();
    }
}
