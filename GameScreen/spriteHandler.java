
package GameScreen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class spriteHandler {
    static String imageName = "test.png";
    BufferedImage image;
    int height, width;
    
    public spriteHandler(String x, int w, int h){
        try {
            image = ImageIO.read(new File(getClass().getResource(x).toURI()));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(spriteHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        width = w;
        height = h;
        
    }

    
    public Image getImage(int t){
        
        return image.getSubimage(t*width, 0, width, height);
    }
}
