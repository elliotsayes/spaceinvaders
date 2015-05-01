package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class SpeedPowerUp extends Bullet {
ImageIcon Speed          = new ImageIcon(getClass().getResource("SpeedPowerUp.png"));
    public SpeedPowerUp(int x, int y, int verticalVelocity) {
        super(new IntVector2D(x,y), new IntVector2D(0,verticalVelocity), new IntVector2D(), Color.GREEN, 3);
        color = Color.GREEN;   
        size = 20;
    }
    
    @Override
    public void upgrade(GamePanel g) {
        g.shooter.color = Color.RED;
        g.player_timer.stop();
        g.player_timer = new Timer(1000 / (g.player_updateInterval * 2), (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.shooter.move(g.temp_this, g.bullets);

            }
        }));
        g.player_timer.start();

        g.powerUpTimer = new Timer(3000, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.player_timer.stop();
                g.player_timer = new Timer(1000 / g.player_updateInterval , (new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.shooter.move(g.temp_this, g.bullets);
                            
                    }
                }));
                g.player_timer.start();
                g.powerUpTimer.stop();
                g.shooter.color = Color.GREEN;
            }
        }));
        g.powerUpTimer.start();
    }
    @Override
    public void paint (Graphics2D win){
        win.drawImage(Speed.getImage(), coordinates.getX(), coordinates.getY(), size, size + 10, null);
    }
            
}
