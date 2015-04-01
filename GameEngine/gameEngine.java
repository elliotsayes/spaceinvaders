package GameEngine;

import java.awt.Color;
import javax.swing.JFrame;
import MainMenu.mainMenu;
import GameScreen.GamePanel;
import killscreen.Winnerr;

/*
 * GameEngine includes Main class, handles game menus logic 
 * Controls JFrame
 */
public class gameEngine {

    // Config
    private static final String windowTitle = "*PewPew*";
    private static final Color backgroundColor = Color.BLACK;
    private static final IntVector2D windowSize = new IntVector2D(800, //window width
            600);//window height

    public static void main(String[] args) throws InterruptedException {

        // Creating Game Window, JFrame
        JFrame gameWindow = new JFrame(windowTitle);
        gameWindow.setSize(windowSize.getX(), windowSize.getY());
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // * Add game panels here *
        mainMenu MainMenu;
        GamePanel playScreen;
        Winnerr win;

        // Initialise game state
        int game_state = 0;
        while (game_state != -1) {
            switch (game_state) {
                case 0: // Main Menu
                    // Add main menu to frame
                    MainMenu = new mainMenu(new IntVector2D(windowSize.getX(), windowSize.getY()));
                    MainMenu.setBackground(Color.white);
                    gameWindow.add(MainMenu);
                    gameWindow.validate();
                    MainMenu.repaint();

                    // MainMenu loop Waits for a getSelection to be made
                    while (MainMenu.getSelection() == 0) {
                        Thread.sleep(1);
                    }
                    // Removes MainMenu 
                    gameWindow.remove(MainMenu);
                    game_state = MainMenu.getSelection();
                    break;

                case 1: // Play Screen
                    playScreen = new GamePanel();
                    playScreen.setBackground(backgroundColor);
                    gameWindow.add(playScreen);
                    gameWindow.validate();
                    playScreen.requestFocus();

                    // Play screen loop
                    while (playScreen.getSelection() == 1) {
                        Thread.sleep(1);
                    }
                    // Removes playScreen
                    game_state = playScreen.getSelection();
                    playScreen.restart();
                    gameWindow.remove(playScreen);

                    break;

                case 2: // Win Screen
                    win = new Winnerr();
                    win.setBackground(backgroundColor);
                    gameWindow.add(win);
                    gameWindow.validate();
                    Thread.sleep(5000);
                    gameWindow.remove(win);
                    game_state = 0;
                    break;
            }
        }
        gameWindow.dispose();
    }
}