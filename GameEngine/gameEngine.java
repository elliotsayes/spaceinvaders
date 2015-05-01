package GameEngine;

import GameScreen.BasicPlayer;
import java.awt.Color;
import javax.swing.JFrame;
import MainMenu.mainMenu;
import GameScreen.GamePanel;
import MainMenu.OptionsInfo;
import MainMenu.OptionsScreen;
import java.io.IOException;
import java.net.URISyntaxException;
import killscreen.Loser;
import killscreen.Winnerr;

/*
 * GameEngine includes Main class, handles game menus logic 
 * Controls JFrame
 */
public class gameEngine {

    // Config
    private static final String windowTitle = "*PewPew*";
    private static final Color backgroundColor = Color.BLACK;
    private static final IntVector2D windowSize = new IntVector2D(800,600);

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {

        // Creating Game Window, JFrame
        JFrame gameWindow = new JFrame(windowTitle);
        gameWindow.setSize(windowSize.getX(), windowSize.getY());
        gameWindow.setVisible(true);
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // * Add game panels here *
        /**/
        mainMenu MainMenu;
        OptionsScreen optionsScreen;
        GamePanel playScreen;
        Winnerr win;
        Loser loseWin;
        
        // Audio tracks for panels
        AudioHandler music = new AudioHandler();
        music.add("pacman_death.wav", "death");
        music.add("battle_2.mid", "battleMusic");
        music.add("TitleScreen.mid", "intro");
        music.add("win.mid", "win");
        music.add("shoot.wav", "shoot");
     
        
        
        // Initialise game state
        int game_state = 0;
        OptionsInfo myOptions = new OptionsInfo();
        while (game_state != -1) {
            switch (game_state) {
                case 0: // Main Menu
                    // Add main menu to frame
                    music.play("intro");
                    MainMenu = new mainMenu(windowSize);
                    MainMenu.setBackground(Color.white);
                    gameWindow.add(MainMenu);
                    gameWindow.validate();
                    MainMenu.repaint();
                    // MainMenu loop Waits for a getSelection to be made
                    while (MainMenu.getSelection() == 0) {
                        Thread.sleep(1);
                    }
                    // Removes MainMenu 
                    music.stop("intro");
                    gameWindow.remove(MainMenu);
                    game_state = MainMenu.getSelection();
                    break;

                case 1: // Play Screen
                    // Plays sound
                    music.play("battleMusic");
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
                    music.stop("battleMusic");
                    game_state = playScreen.getSelection();
                    playScreen.restart();
                    gameWindow.remove(playScreen);

                    break;

                case 2: // Win Screen
                    music.play("win");
                    win = new Winnerr(new IntVector2D(windowSize.getX(), windowSize.getY()));
                    win.setBackground(backgroundColor);
                    gameWindow.add(win);
                    gameWindow.validate();
                    while (win.getSelection() == 0) {
                        Thread.sleep(1);
                    }
                    game_state = win.getSelection();
                    gameWindow.remove(win);
                    music.stop("win");
                    break;

                case 3: // Lose Screen
                    music.play("death");
                    loseWin = new Loser(new IntVector2D(windowSize.getX(), windowSize.getY()));
                    loseWin.setBackground(backgroundColor);
                    gameWindow.add(loseWin);
                    gameWindow.validate();
                    while (loseWin.getSelection() == 0) {
                        Thread.sleep(1);
                    }
                    game_state = loseWin.getSelection();
                    gameWindow.remove(loseWin);
                    music.stop("death");

                    break;
                case 4: // Options
                    // Add options to frame
                    //music.play("intro");
                    optionsScreen = new /**/ OptionsScreen(windowSize, myOptions);
                    optionsScreen.setBackground(Color.white);
                    gameWindow.add(optionsScreen);
                    gameWindow.validate();
                    optionsScreen.repaint();
                    // Options loop Waits for a getSelection to be made
                    while (optionsScreen.getSelection() == 4) {
                        Thread.sleep(1);
                    }
                    // Removes options
                    myOptions = optionsScreen.workingOptions;
                    AudioPlayer.canPlay = myOptions.soundData;
                    BasicPlayer.skinCode = myOptions.shooterData;
                    //music.stop("intro");
                    gameWindow.remove(optionsScreen);
                    game_state = optionsScreen.getSelection();
                    break;
            }
        }
        //Clears JFrame
        gameWindow.dispose();
    }
}
