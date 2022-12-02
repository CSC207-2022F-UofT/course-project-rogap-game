package main;

import Frameworks.GamePanel;
import Frameworks.GameWindow;
import Interface_Adapters.Game;
import Interface_Adapters.GameScreenPresenter;

public class MainClass {
    public static void main(String[] args) {

/*
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Welcome " + userName + "!");  // Output user input
*/
        GamePanel gamePanel = new GamePanel();
        GameWindow application = new GameWindow(gamePanel);

        GameScreenPresenter presenter = new GameScreenPresenter(gamePanel);
        new Game(presenter);

    }
}
