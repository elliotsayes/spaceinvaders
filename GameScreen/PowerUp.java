
package GameScreen;

import java.awt.Color;


public class PowerUp extends Bullet {

    public PowerUp(int x, int y, int verticalVelocity) {
        super(x, y, verticalVelocity);
        color = Color.BLUE;
        
    }
    
    @Override
    public void upgrade(GamePanel g){
        g.shooter.health += 5;
    }
 
}
