/*
 * Main menu 
 */
package MainMenu;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class mainMenu extends JPanel {
    final int paintUpdateRate = 1;
    Timer paintTimer;
    
    IntVector2D menuSize;
    ImageIcon BackgroundImage, BannerImage;
    ArrayList<Button> buttons;
    
    int selection = 0, selectionCandidate = 0, state = 0;
    boolean hovered;
    
    public mainMenu(IntVector2D windowSize, OptionsInfo inputOptions) {
        this(windowSize);
    }
    
    public mainMenu(IntVector2D windowSize) {
        // Adds mouse listener and overides methods
        addMouseListener(new MouseListener() {

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseClicked(MouseEvent e) {
                // Sends key position to be checked
                actuateMouse(new IntVector2D(e.getX(),e.getY()),true);
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override 
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseMoved(MouseEvent e) {
                actuateMouse(new IntVector2D(e.getX(),e.getY()),false);
                repaint();
            }
        });
        menuSize = windowSize;
        
        BackgroundImage = new ImageIcon(getClass().getResource("background.png"));
        BannerImage = new ImageIcon(getClass().getResource("logo.png"));
        
        makeMenuButtons();
    }

    // Overrides paint method of JPanel, this will control look of panel. Called by repaint method
    @Override
    public void paint(Graphics win) {
        // Clears window
        super.paint(win);
        
        Graphics2D window = (Graphics2D) win;
        
        // draw background
        window.drawImage(BackgroundImage.getImage(), 0, 0, 800, 600, null);
        window.drawImage(BannerImage.getImage(), 100, 50, BannerImage.getIconWidth(), BannerImage.getIconHeight(), null);
        
        drawButtons(window);
    }
    
    public void makeMenuButtons() {
        buttons = new ArrayList<>();
        ImageIcon unselectedImg = new ImageIcon(getClass().getResource("unselected.png"));
        ImageIcon selectedImg = new ImageIcon(getClass().getResource("selected.png"));
        ImageIcon hoveredImg = new ImageIcon(getClass().getResource("hovered.png"));
        
        buttons.add(new Button("START", new IntVector2D(290, 240), new IntVector2D(77, 45), new IntVector2D(195, 90),  1, unselectedImg, selectedImg, hoveredImg, 0));
        buttons.add(new Button("Options", new IntVector2D(300, 330), new IntVector2D(65, 40), new IntVector2D(175, 80),  4, unselectedImg, selectedImg, hoveredImg, 0));
        buttons.add(new Button("Exit" , new IntVector2D(300, 410), new IntVector2D(75, 40), new IntVector2D(175, 80), -1, unselectedImg, selectedImg, hoveredImg, 0));
    }
    
    public void actuateMouse(IntVector2D mouseLocation, boolean clicked) {
        int i;
        hovered = false;
        for(i=0;i<buttons.size();i++) {    
            if (    mouseLocation.getX() >= buttons.get(i).boxCoordinates.getX()
                 && mouseLocation.getX() <= (buttons.get(i).boxCoordinates.getX() + buttons.get(i).buttonSize.getX()) 
                 && mouseLocation.getY() >= buttons.get(i).boxCoordinates.getY()
                 && mouseLocation.getY() <= (buttons.get(i).boxCoordinates.getY() + buttons.get(i).buttonSize.getY())) {
                hovered = true;
                if (clicked) {
                    executeClick(0,buttons.get(i).selection);
                } else {
                    selectionCandidate = i;
                }
            }
        }
    }
    
    public int getSelection() {
        return selection;
    }
    
    public void executeClick(int selectionType, int buttonSelection) {
        this.selection = buttonSelection;
    }
    
    public void drawButtons(Graphics2D window) {
        int i;
        for(i=0;i<buttons.size();i++) {
            if(selectionCandidate == i) {
                if(hovered) {
                    state = 2; // selectedImg and hoveredImg
                } else {
                    state = 1; // selectedImg but not hoveredImg
                }
            } else {
                state = 0; // not selectedImg 
            }
            buttons.get(i).paint(window,state);
        }
    }

}
