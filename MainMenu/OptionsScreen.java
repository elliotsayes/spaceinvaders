/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import GameEngine.IntVector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author myn
 */
public class OptionsScreen extends mainMenu {
    public OptionsInfo workingOptions;
    
    public OptionsScreen(IntVector2D windowSize, OptionsInfo inputOptions) {
        this(windowSize);
        workingOptions = inputOptions;
    }
    
    public OptionsScreen(IntVector2D windowSize) {
        super(windowSize);
        workingOptions = new OptionsInfo(0,0);
    }
    
    @Override
    public void paint(Graphics win) {
        super.paint(win);
        Graphics2D win2d = (Graphics2D)win;
        //drawButtons();
    }
    
    @Override
    public void makeMenuButtons() {
        buttons = new ArrayList<>();
        ImageIcon unselectedImg = new ImageIcon(getClass().getResource("unselected.png"));
        ImageIcon selectedImg = new ImageIcon(getClass().getResource("selected.png"));
        ImageIcon hoveredImg = new ImageIcon(getClass().getResource("hovered.png"));
        
        buttons.add(new Button( "Placeholder" , new IntVector2D(290, 240), new IntVector2D(77, 45), new IntVector2D(195, 90),  1, unselectedImg, selectedImg, hoveredImg, 0));
        buttons.add(new Button(">", new IntVector2D(300, 330), new IntVector2D(65, 40), new IntVector2D(175, 80),  4, unselectedImg, selectedImg, hoveredImg, 0));
        buttons.add(new Button("<" , new IntVector2D(300, 410), new IntVector2D(75, 40), new IntVector2D(175, 80), -1, unselectedImg, selectedImg, hoveredImg, 0));
    } 
}
