
package killscreen;

import GameEngine.IntVector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;

public class Winnerr extends JPanel {
    Timer paint_timer;
    int paint_updatePS = 60;
    int x = 500;
    
   
    FireWorkHandler firework = new FireWorkHandler();
    public Winnerr() {
        ImageIcon pic = new ImageIcon(getClass().getResource("test.gif"));
        JLabel label = new JLabel(pic, JLabel.CENTER);
        this.add(label);
       
        this.paint_timer = new Timer(1000/paint_updatePS, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                firework.move();
                
                
                repaint();   
            }
        }));
       
        paint_timer.start();
    }

    @Override
     public void paint(Graphics win) {
        // Clears window
        super.paint(win);
        Graphics2D window = (Graphics2D) win;
        window.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
      // particles.PaintParticle(window);
        firework.Paint(window);
    
}
}
