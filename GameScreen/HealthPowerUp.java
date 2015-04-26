
package GameScreen;

import java.awt.Color;


public class HealthPowerUp extends Bullet {

    public HealthPowerUp(int x, int y, int verticalVelocity) {
        super(x, y, verticalVelocity, Color.BLUE, 3);
        color = Color.BLUE;   
    }
    
    @Override
    public void upgrade(GamePanel g){
        g.shooter.health += 1;
        //g.shooter.width += 30;
    }
}
