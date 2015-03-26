//package pew;

import GameScreen.GamePanel;
import MainMenu.mainMenu;
import java.awt.Color;
import javax.swing.JFrame;
import killscreen.Winnerr;

/*
 * GameEngine includes Main class, handles game menus logic 
 * Controls JFrame
 */


public class gameEngine {
    
   
    // Config
    private static final String windowTitle = "*PewPew*";
    private static final Color backgroundColor = Color.BLACK;

    public static void main(String[] args) throws InterruptedException{ 
        
        
         // Creating Game Window, JFrame
        JFrame gameWindow = new JFrame(windowTitle);
        gameWindow.setSize(800, 600);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // * Add game panels here *
        mainMenu MainMenu = new mainMenu(370,300);
        GamePanel playScreen = new GamePanel();
        Winnerr win = new Winnerr();
        
         // Set screens background color
        playScreen.setBackground(backgroundColor);
        MainMenu.setBackground(Color.white);
        win.setBackground(backgroundColor);
        int game_state = 0;
        while(game_state != -1){
            
            switch (game_state) {

                case 0:
                    // Add main menu to frame
                    gameWindow.add(MainMenu);
                    gameWindow.validate();
                    MainMenu.repaint();

                    // MainMenu loop Waits for a selection to be made
                    while (!(MainMenu.selection() == 1)) {
                        Thread.sleep(1);
                    }
                    // Removes MainMenu 
                    gameWindow.remove(MainMenu);
                    game_state = MainMenu.selection();
                    break;

                case 1:

                    gameWindow.add(playScreen);
                    gameWindow.validate();
                    playScreen.requestFocus();

                    // Play screen loop
                    while (!playScreen.winner() & !playScreen.looser()) {
                        Thread.sleep(1);
                    }

                    // Removes playScreen
                    gameWindow.remove(playScreen);
                    game_state = 2;
                    break;

                case 2:
                    // Adds winner screen
                    gameWindow.add(win);
                    gameWindow.validate();
                    playScreen.requestFocus();
                    playScreen.repaint();
                    Thread.sleep(1000);
                    playScreen.restart();
                    gameWindow.remove(win);
                    game_state = 1;
                    break;
            }
        }
    }
}
    
    
    

