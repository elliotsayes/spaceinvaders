/*
 * Main menu 
 */
package MainMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainMenu extends JPanel {

    int PLAY_X_START = 300, PLAY_X_END = 500, PLAY_Y_START = 250, PLAY_Y_END = 350;
    String Message = "START";
    int selection = 0;
    JButton start;
    
    //text position
    int x;
    int y;
    
    ImageIcon pic = new ImageIcon(getClass().getResource("loading.gif"));
    JLabel label = new JLabel(pic, JLabel.CENTER);

    public mainMenu(int x, int y) {
        
        start = new JButton(Message);
        
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
                update(e.getX(), e.getY());
            }
        });
        this.x = x;
        this.y = y;
        //this.add(label);
        //label.setVerticalAlignment(JLabel.BOTTOM);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = 1;
               
            }
        });
        //start.setLayout(null);
        //this.setLayout(null);
        this.add(start);
        start.setLocation(400, 300);
        
        
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
        //window.drawString(Message, x, y);

    }

    public void update(int x, int y) {
        if (x >= PLAY_X_START && x <= PLAY_X_END && y >= PLAY_Y_START && y <= PLAY_Y_END) {
            selection = 1;
        }
    }

    public int getSelection() {
        return selection;
    }

}
