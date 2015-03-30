package GameScreen;

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
    int selection;
    boolean pause = false;
    // Game Screen Entities
    EnemyHandler invaders = new EnemyHandler();
    BasicPlayer shooter = new BasicPlayer();
    // Game timer for repaint
    Timer paint_timer, player_timer, enemy_timer;
    int paint_updateInterval = 300;
    int player_updateInterval = 250;
    int enemy_updateInterval = 100;

    // gameScreen Constructor
    public GamePanel() {

        // ActionListener for time, what happens when timer executes
        this.paint_timer = new Timer(1000/paint_updateInterval, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                checkCollision();
            }
        }));

        this.player_timer = new Timer(1000/player_updateInterval, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shooter.move(temp_this);
            }
        }));

        this.enemy_timer = new Timer(1000/enemy_updateInterval, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invaders.moveArmy(temp_this);
                
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
                
                if (pause == false) {
                    shooter.keyPressed(e);
                    if (!(enemy_timer.isRunning()) && e.getKeyCode() == KeyEvent.VK_SPACE) {
                        enemy_timer.start();
                        player_timer.start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if(pause == false){
                        enemy_timer.stop();
                        player_timer.stop();
                        pause = true;
                    }
                    else{
                        enemy_timer.start();
                        player_timer.start();
                        pause = false;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    selection = 0;
                }    
                
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    restart();
                }
                
            }
        });
        this.setFocusable(true);
        this.requestFocus();
        paint_timer.start();
        selection = 1;
    }

    @Override
    public void paint(Graphics win) {
        // Clears window
        super.paint(win);
        Graphics2D window = (Graphics2D) win;
        window.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        invaders.paint(window);
        shooter.paint(window);
    }

    // Moves Entities and passes panel information
    public void move() {
        invaders.moveArmy(this);
        shooter.move(this);
    }

    // Needs to be re-written, messy
    public void checkCollision() {
        ArrayList<BasicEnemy> badies = invaders.getArmy();
        ArrayList<Bullet> pewpew = shooter.getAmo();
        int temp, temp2;
        for (temp = 0; temp != badies.size(); ++temp) {
            if (temp >= badies.size()) {
                return;
            }
            for (temp2 = 0; temp2 != pewpew.size(); ++temp2) {
                if (temp2 >= pewpew.size()) {
                    return;
                }
                if (((badies.get(temp).getX() - 30) <= pewpew.get(temp2).getX()) & ((badies.get(temp).getX() + 30) >= (pewpew.get(temp2).getX() + 2))) {
                    if (((badies.get(temp).getY() - 30) <= pewpew.get(temp2).getY()) & ((badies.get(temp).getY() + 30) >= (pewpew.get(temp2).getY() + 2))) {
                        invaders.hit(temp);
                        shooter.hit(temp2);
                        if (invaders.getArmy().isEmpty()){
                            selection = 2;
                        }
                        temp -= 1;
                        temp2 -= 1;
                        if (temp == -1 | temp2 == -1) {
                            return;
                        }
                    }
                }
            }
        }
        pewpew = invaders.getAmmo();
        int x = shooter.getX() + shooter.getWidth()/2;
        int y = shooter.getY() + shooter.getHeight()/2;
        
        for (int k = 0; k < pewpew.size(); ++k) {
            if (((x - shooter.getWidth()/2) <= pewpew.get(k).getX()) & ((x + shooter.getWidth()/2) >= (pewpew.get(k).getX() + 2))) {
                if ((y <= pewpew.get(k).getY()) & ((y + shooter.getHeight()/2) >= (pewpew.get(k).getY() + 2))) {
                    shooter.playerHit();
                    if (shooter.getHealth() == 0){
                        selection = 0;
                    }
                    pewpew.remove(k);
                    k--;
                }
            }
        }
    }

    public void restart() {
        invaders = new EnemyHandler();
        enemy_timer.stop();
        player_timer.stop();
        shooter.locationRespawn();
    }
    
    public int getSelection(){
        return selection;
    }
}
