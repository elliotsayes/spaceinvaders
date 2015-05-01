
package GameScreen;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;


public class HealthPowerUp extends Bullet {
     ImageIcon Health          = new ImageIcon(getClass().getResource("HealthImage.png"));

    public HealthPowerUp(int x, int y, int verticalVelocity) {
        super(x, y, verticalVelocity, Color.BLUE, 3);
        color = Color.BLUE;   
        size = 20;
    }
    
    @Override
    public void upgrade(GamePanel g){
        if (g.shooter.health >= 5){return;}
      
        g.shooter.health += 1;
        //g.shooter.width += 30;
    }
    @Override
    public void paint(Graphics2D win){
        
        win.drawImage(Health.getImage(), coordinates.getX(), coordinates.getY(), 20, 20, null);
               
    }
}
