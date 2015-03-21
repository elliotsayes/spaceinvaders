
import GameScreen.gameScreen;
import MainMenu.mainMenu;
import java.awt.Color;
import javax.swing.JFrame;
import killscreen.Winnerr;

/*
 * GameEngine includes Main class, handles game logic and menus
 * Controls JFrame
 */


public class gameEngine {
    
   
    // Config
    private static final String windowTitle = "*PewPew*";
    private static final Color backgroundColor = Color.black;
    

    public static void main(String[] args) throws InterruptedException{ 
        
        
         // Creating Game Window, JFrame
        JFrame gameWindow = new JFrame(windowTitle);
        gameWindow.setSize(800, 600);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // * Add game panels here *
        mainMenu MainMenu = new mainMenu(370,300);
        gameScreen playScreen = new gameScreen();
        Winnerr win = new Winnerr();
        
         // Set screens background color
        playScreen.setBackground(backgroundColor);
        MainMenu.setBackground(Color.white);
        win.setBackground(backgroundColor);
   
        // Add main menu to frame
        gameWindow.add(MainMenu);
        gameWindow.validate();
        MainMenu.repaint();
       
        // MainMenu loop
        while(!(MainMenu.selection() == 1)){
        MainMenu.repaint();
        Thread.sleep(10);
        }
        
        // Removes MainMenu and loads next panel
        MainMenu.removeAll();
        gameWindow.remove(MainMenu);
        Thread.sleep(10);
        gameWindow.add(playScreen);
        gameWindow.validate();
        playScreen.requestFocus();
        playScreen.repaint();
        
        // Play screen loop
        while(!playScreen.winner()){
            playScreen.move();
            playScreen.collision();
            playScreen.repaint();
            Thread.sleep(10);
        }
        
        // Removes playScreen
        gameWindow.remove(playScreen);
        Thread.sleep(10);
        
        // Adds winner screen
        gameWindow.add(win);
        gameWindow.validate();
        playScreen.requestFocus();
        playScreen.repaint();
        }
        
        
    
}
    
    
    

