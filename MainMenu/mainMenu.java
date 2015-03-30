/*
 * Main menu 
 */
package MainMenu;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainMenu extends JPanel {   
    IntVector2D menuSize;
    
    String playText;
    IntVector2D playBoxCoordinates, playTextOffset, playButtonSize;
    
    String exitText;
    IntVector2D exitBoxCoordinates, exitTextOffset, exitButtonSize;
    
    
    int selection = 0;

    ImageIcon pic = new ImageIcon(getClass().getResource("loading.gif"));
    JLabel label = new JLabel(pic, JLabel.CENTER);

    public mainMenu(IntVector2D windowSize) {
        // Adds mouse listener and overides methods
        addMouseListener(new MouseListener() {

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Sends key position to be checked
                getClick(e.getX(), e.getY());
            }
        });
        
        menuSize = windowSize;
        
        // set up buttons
        playText = "START";
        playBoxCoordinates = new IntVector2D(300, 250); playTextOffset = new IntVector2D(70, 50); playButtonSize = new IntVector2D(200, 100);
        
        exitText = "Exit";
        exitBoxCoordinates = new IntVector2D(300, 350); exitTextOffset = new IntVector2D(70, 50); exitButtonSize = new IntVector2D(200, 100);
        
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
        window.setColor(Color.black);
        // Main menu message
        window.drawString(playText, 
                playBoxCoordinates.getX()+playTextOffset.getX(), // x coordinate of text
                playBoxCoordinates.getY()+playTextOffset.getY());// y coordinate of text
        window.drawString(exitText, 
                exitBoxCoordinates.getX()+exitTextOffset.getX(), // x coordinate of text
                exitBoxCoordinates.getY()+exitTextOffset.getY());// y coordinate of text

    }

    public void getClick(int x, int y) {
        if (x >= playBoxCoordinates.getX() && x <= (playBoxCoordinates.getX() + playButtonSize.getX()) && y >= playBoxCoordinates.getY() && y <= (playBoxCoordinates.getY() + playButtonSize.getY())) {
            selection = 1;
        }else if (x >= exitBoxCoordinates.getX() && x <= (exitBoxCoordinates.getX() + exitButtonSize.getX()) && y >= exitBoxCoordinates.getY() && y <= (exitBoxCoordinates.getY() + exitButtonSize.getY())) {
            selection = -1;
        }
    }

    public int getSelection() {
        return selection;
    }

}
