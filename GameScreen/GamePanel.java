
package GameScreen;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel {
    
    JPanel temp_this = this;
    // Game Screen Entities
    EnemyHandler invaders = new EnemyHandler();
    BasicPlayer shooter = new BasicPlayer();
    // Game timer for repaint
    Timer paint_timer, player_timer, enemy_timer;
    int paint_updateInterval = 1;
    int player_updateInterval = 5;
    int enemy_updateInterval = 10;
    
    // gameScreen Constructor
    public GamePanel() {
                
                // ActionListener for time, what happens when timer executes
                this.paint_timer = new Timer(paint_updateInterval,(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
                }));
                
                this.player_timer = new Timer(player_updateInterval,(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    shooter.move(temp_this);
                }
                }));
                
                this.enemy_timer = new Timer(enemy_updateInterval,(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    invaders.moveArmy(temp_this);
                    checkCollision();
                }
                }));
                
                
                // Gets User Input
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
                            shooter.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
                            if (!enemy_timer.isRunning()){
                                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                                    enemy_timer.start();
                                    player_timer.start();
                                }
                            }
                            if(e.getKeyCode() == KeyEvent.VK_P){
                                 enemy_timer.stop();
                                 player_timer.stop();
                             }
                            if(e.getKeyCode() == KeyEvent.VK_R){
                                restart();
                             }
                            shooter.keyPressed(e);
			}
		});
                this.setFocusable(true);
                this.requestFocus();
                paint_timer.start();
	}
    
    @Override
    public void paint(Graphics win) {
        // Clears window
	super.paint(win);
        Graphics2D window = (Graphics2D)win;
        window.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        invaders.paint(window);
        shooter.paint(window);
	}
    
    // Moves Entities and passes panel information
    public void move(){
        invaders.moveArmy(this);
        shooter.move(this);
    }
    
    // Needs to be re-written, messy
    public void checkCollision(){
        ArrayList<BasicEnemy> badies = invaders.getArmy();
        ArrayList<Bullet> pewpew = shooter.getAmo();
        int temp, temp2;
        for(temp = 0;temp!=badies.size();++temp){
            if (temp >= badies.size()){return;}
            for(temp2 = 0; temp2 !=pewpew.size();++temp2){
                if (temp2 >= pewpew.size()){return;}
                    if (((badies.get(temp).getx()-30) <= pewpew.get(temp2).getx() )&((badies.get(temp).getx() + 30 )>= (pewpew.get(temp2).getx() + 2))){
                      if (((badies.get(temp).gety()-30) <= pewpew.get(temp2).gety() )&((badies.get(temp).gety() + 30) >= (pewpew.get(temp2).gety() + 2))){ 
                          invaders.hit(temp);
                          shooter.hit(temp2);
                          temp -= 1;
                          temp2 -= 1;
                          if (temp == -1 | temp2 == -1){return;}
            }
            }
            
        }
        }
        pewpew = invaders.getAmmo();
        int x = shooter.getx() + 30;
        int y = shooter.gety() + 5;
        for(int k = 0;k != pewpew.size();k++ ){
            if (((x-30) <= pewpew.get(k).getx() )&((x + 30 )>= (pewpew.get(k).getx() + 2))){
                      if (((y) <= pewpew.get(k).gety() )&((y + 30) >= (pewpew.get(k).gety() + 2))){ 
                          shooter.playerHit();
                          pewpew.remove(k);
                      }
            }
        }
        }
    
    public void restart(){
        invaders = new EnemyHandler();
        shooter.reset();
    }
             
    // Checks if any enemys are left
    public boolean winner(){
       return invaders.getArmy().isEmpty();
   }

    public boolean looser() {
        return (shooter.health() == 0);
    }
}
