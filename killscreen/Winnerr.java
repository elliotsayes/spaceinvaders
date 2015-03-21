
package killscreen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Winnerr extends JPanel {
    
    public Winnerr() {
        ImageIcon pic = new ImageIcon(getClass().getResource("test.gif"));
        JLabel label = new JLabel(pic, JLabel.CENTER);
        this.add(label);
        
    }
    
}
