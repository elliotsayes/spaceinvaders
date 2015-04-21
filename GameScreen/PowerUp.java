
package GameScreen;

import java.awt.Color;


public class PowerUp extends Bullet {

    public PowerUp(int x, int y, int verticalVelocity) {
        super(x, y, verticalVelocity, Color.BLUE, 10);
        color = Color.BLUE;
        
    }
    
    @Override
    public void upgrade(GamePanel g){
        g.shooter.health += 1;
        //g.shooter.width += 30;
    }
 
}
