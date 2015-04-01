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
    
    int selection = 0, hoverSelection = 0;
    
    int i;
    
    ImageIcon pic = new ImageIcon(getClass().getResource("loading.gif"));
    JLabel label = new JLabel(pic, JLabel.CENTER);

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
        buttons.add(new Button("START", new IntVector2D(300, 250), new IntVector2D(70, 50), new IntVector2D(175, 100),  1));
        buttons.add(new Button("Exit" , new IntVector2D(300, 350), new IntVector2D(75, 50), new IntVector2D(175, 100), -1));
                
        // set up background image
        this.add(label);
        label.setVerticalAlignment(JLabel.BOTTOM);
    }

    // Overrides paint method of JPanel, this will control look of panel. Called by repaint method
    @Override
    public void paint(Graphics win) {
        // Clears window
        super.paint(win);
        
        Graphics2D window = (Graphics2D) win;
        // Text color
        
        
        // Main menu message
        window.setColor(Color.cyan);
        window.fillRect(buttons.get(hoverSelection).boxCoordinates.getX(), 
                        buttons.get(hoverSelection).boxCoordinates.getY(), 
                        buttons.get(hoverSelection).buttonSize.getX(), 
                        buttons.get(hoverSelection).buttonSize.getY());
        
        for(i=0;i<buttons.size();i++) {
            buttons.get(i).paint(window);
        }
    }

    public void applyClick(IntVector2D clickLocation) {
        for(i=0;i<buttons.size();i++) {
            if (    clickLocation.getX() >= buttons.get(i).boxCoordinates.getX()
                 && clickLocation.getX() <= (buttons.get(i).boxCoordinates.getX() + buttons.get(i).buttonSize.getX()) 
                 && clickLocation.getY() >= buttons.get(i).boxCoordinates.getY() 
                 && clickLocation.getY() <= (buttons.get(i).boxCoordinates.getY() + buttons.get(i).buttonSize.getY())) {
                    selection = buttons.get(i).selection;
            }
        }
    }
    
    public void actuateMouse(IntVector2D mouseLocation, boolean clicked) {
        for(i=0;i<buttons.size();i++) {    
            if (    mouseLocation.getX() >= buttons.get(i).boxCoordinates.getX()
                 && mouseLocation.getX() <= (buttons.get(i).boxCoordinates.getX() + buttons.get(i).buttonSize.getX()) 
                 && mouseLocation.getY() >= buttons.get(i).boxCoordinates.getY()
                 && mouseLocation.getY() <= (buttons.get(i).boxCoordinates.getY() + buttons.get(i).buttonSize.getY())) {
                if(clicked){selection = buttons.get(i).selection;} else {hoverSelection = i;}
            }
        }
    }
    
    public int getSelection() {
        return selection;
    }

}
