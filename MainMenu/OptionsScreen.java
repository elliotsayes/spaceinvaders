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
    private final int numShooters = 3;
    public OptionsInfo workingOptions;
    public ArrayList<ImageIcon> shooterPreviews;
    
    public OptionsScreen(IntVector2D windowSize, OptionsInfo inputOptions) {
        this(windowSize);
        workingOptions = inputOptions;
    }
    
    public OptionsScreen(IntVector2D windowSize) {
        super(windowSize);
        selection = 4;
        workingOptions = new OptionsInfo(true,0);
        
        // populate ShooterPreviewImages
        ImageIcon shooter0 = new ImageIcon(getClass().getResource("shooter0.png"));
        ImageIcon shooter1 = new ImageIcon(getClass().getResource("shooter1.png"));
        ImageIcon shooter2 = new ImageIcon(getClass().getResource("shooter2.png"));
        shooterPreviews = new ArrayList<ImageIcon>() {{
            add(shooter0);
            add(shooter1);
            add(shooter2);
        }};
        
        updateButtons();
        repaint();
    }
    
    @Override
    public void paint(Graphics win) {
        super.paint(win);
        win.drawImage(shooterPreviews.get(workingOptions.shooterData).getImage(), 360, 340, shooterPreviews.get(workingOptions.shooterData).getIconWidth(), shooterPreviews.get(workingOptions.shooterData).getIconHeight(), null);
    }
    
    @Override
    protected void makeMenuButtons() {
        buttons = new ArrayList<>();
        ImageIcon unselectedImg = new ImageIcon(getClass().getResource("unselected.png"));
        ImageIcon selectedImg = new ImageIcon(getClass().getResource("selected.png"));
        ImageIcon hoveredImg = new ImageIcon(getClass().getResource("hovered.png"));
        
        buttons.add(new Button( "Placeholder" , new IntVector2D(290, 240), new IntVector2D(68, 45), new IntVector2D(195, 90),  0, unselectedImg, selectedImg, hoveredImg, 1));
        buttons.add(new Button("<", new IntVector2D(275, 340), new IntVector2D(22, 22), new IntVector2D(60, 60),  0, unselectedImg, selectedImg, hoveredImg, 2));
        buttons.add(new Button(">" , new IntVector2D(445, 340), new IntVector2D(22, 22), new IntVector2D(60, 60), 0, unselectedImg, selectedImg, hoveredImg, 2));
        buttons.add(new Button("Back" , new IntVector2D(300, 410), new IntVector2D(72, 42), new IntVector2D(175, 80), 0, unselectedImg, selectedImg, hoveredImg, 0));
    }
    
    @Override
    protected void executeClick(int selectionType, int buttonSelection) {
        switch(selectionType) {
            case 0:
                this.selection = buttonSelection;
                break;
            case 1:
                this.workingOptions.soundData = (buttonSelection==1);
                break;
            case 2:
                this.workingOptions.shooterData = buttonSelection;
        }
        updateButtons();
        repaint();
    }
    
    private void updateButtons() {
        if(workingOptions.soundData){
            buttons.get(0).buttonText = "Music: On";
            buttons.get(0).selection = 0;
        } else {
            buttons.get(0).buttonText = "Music: Off";
            buttons.get(0).selection = 1;
        }
        buttons.get(1).selection = (workingOptions.shooterData-1)%shooterPreviews.size();
        if(buttons.get(1).selection<0)buttons.get(1).selection=shooterPreviews.size()-1;
        buttons.get(2).selection = (workingOptions.shooterData+1)%shooterPreviews.size();
    }
}
