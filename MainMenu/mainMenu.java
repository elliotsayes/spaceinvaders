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
    ArrayList<Button> buttons;
    Button tempButton;
    
    int selection = 0, selectionCandidate = 0, state = 0;
    boolean hovered;
    
    int i;
    
    //ImageIcon pic = new ImageIcon(getClass().getResource("loading.gif"));
    //JLabel label = new JLabel(pic, JLabel.CENTER);

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
        this.paintTimer = new Timer(1000/paintUpdateRate, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }));
        menuSize = windowSize;
        
        buttons = new ArrayList<>();
        ImageIcon unselected = new ImageIcon(getClass().getResource("unselected.png"));
        ImageIcon selected = new ImageIcon(getClass().getResource("selected.png"));
        ImageIcon hovered = new ImageIcon(getClass().getResource("hovered.png"));
        buttons.add(new Button("START", new IntVector2D(300, 250), new IntVector2D(67, 40), new IntVector2D(175, 80),  1, unselected, selected, hovered));
        buttons.add(new Button("Options", new IntVector2D(300, 330), new IntVector2D(65, 40), new IntVector2D(175, 80),  1, unselected, selected, hovered));
        buttons.add(new Button("Exit" , new IntVector2D(300, 410), new IntVector2D(75, 40), new IntVector2D(175, 80), -1, unselected, selected, hovered));
                
        // set up background image
        //this.add(label);
        //label.setVerticalAlignment(JLabel.BOTTOM);
    }

    // Overrides paint method of JPanel, this will control look of panel. Called by repaint method
    @Override
    public void paint(Graphics win) {
        // Clears window
        super.paint(win);
        
        Graphics2D window = (Graphics2D) win;
        
        for(i=0;i<buttons.size();i++) {
            if(selectionCandidate == i) {
                if(hovered) {
                    state = 2; // selected and hovered
                } else {
                    state = 1; // selected but not hovered
                }
            } else {
                state = 0; // not selected 
            }
            buttons.get(i).paint(window,state);
        }
    }

    
    public void actuateMouse(IntVector2D mouseLocation, boolean clicked) {
        hovered = false;
        for(i=0;i<buttons.size();i++) {    
            if (    mouseLocation.getX() >= buttons.get(i).boxCoordinates.getX()
                 && mouseLocation.getX() <= (buttons.get(i).boxCoordinates.getX() + buttons.get(i).buttonSize.getX()) 
                 && mouseLocation.getY() >= buttons.get(i).boxCoordinates.getY()
                 && mouseLocation.getY() <= (buttons.get(i).boxCoordinates.getY() + buttons.get(i).buttonSize.getY())) {
                hovered = true;
                if(clicked){selection = buttons.get(i).selection;} else {selectionCandidate = i;}
            }
        }
    }
    
    public int getSelection() {
        return selection;
    }

}
