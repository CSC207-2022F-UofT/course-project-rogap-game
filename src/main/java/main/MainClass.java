package main;

import Entities.Player;
import Frameworks.GamePanel;
import Interface_Adapters.*;
import Use_Cases.*;

public class MainClass {
    public static void main(String[] args) {


//        Scanner myObj = new Scanner(System.in);
//        System.out.println("Enter username");
//
//        String userName = myObj.nextLine();  // Read user input
//        System.out.println("Welcome " + userName + "!");  // Output user input
        Player player = new Player();
        StatBarsInputBoundary statBarsInputBoundary = new StatBarsInteractor(player);
        StatBarsPresenterBoundary statBarsPresenterBoundary = new StatBarsPresenter(statBarsInputBoundary);

        UpdateScreenBoundary screenModel = new GamePanel(statBarsPresenterBoundary);
        GameScreenPresenter presenter = new GameScreenPresenter(screenModel);
        //GameWindow application = new GameWindow(presenter);

        GameLoopInteractorReference gameManager = new GameLoopManagerLoop(presenter);

        PauseGameInputBoundary pauseGameInteractor = new PauseGameInteractor();
        ShowMapInputBoundary showMapInteractor = new ShowMapInteractor();

        PauseGameController pauseGameController = new PauseGameController(pauseGameInteractor, gameManager);
        ShowMapController showMapController = new ShowMapController(showMapInteractor, gameManager);

        screenModel.setUp(pauseGameController, showMapController);
        gameManager.start();
    }
}
