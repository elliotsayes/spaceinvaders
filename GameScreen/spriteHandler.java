
package GameScreen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;


public class spriteHandler {
    static String imageName = "test.png";
    BufferedImage image;
    
    public spriteHandler(String x) throws IOException, URISyntaxException{
        image = ImageIO.read(new File(getClass().getResource(x).toURI()));
        
    }
    
    public Image getImage(int t){
        
        return image.getSubimage(t*200, 0, 200, 200);
    }
}
