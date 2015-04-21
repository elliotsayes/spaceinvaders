package GameScreen;

import GameEngine.AudioHandler;
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
    int selection;
    int velocity = 300;
    boolean pause = false;
    // Game Screen Entities
    EnemyHandler invaders = new EnemyHandler();
    BasicPlayer shooter = new BasicPlayer();
    BarrierHandler barriers = new BarrierHandler();
    BulletHandler bullets = new BulletHandler(velocity);
    // Game timer for repaint
    Timer paint_timer, player_timer, enemy_timer;
    int paint_updateInterval = 300;
    int player_updateInterval = 250;
    int enemy_updateInterval = 200;
    
    
    
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
                shooter.move(temp_this, bullets);
                
            }
        }));

        this.enemy_timer = new Timer(1000/enemy_updateInterval, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invaders.moveArmy(temp_this, bullets);
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
                    if (!(enemy_timer.isRunning()) && e.getKeyCode() == KeyEvent.VK_SPACE) {
                        enemy_timer.start();
                        player_timer.start();
                        bullets.start();
                    } else {
                        shooter.keyPressed(e);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if(pause == false){
                        enemy_timer.stop();
                        player_timer.stop();
                        bullets.stop();
                        pause = true;
                    }
                    else{
                        enemy_timer.start();
                        player_timer.start();
                        bullets.start();
                        pause = false;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    selection = 0;
                }    
                
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    restart();
                }
                if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                    selection = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
                    selection = 3;
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
        barriers.piecePaint(window);
        bullets.paint(window);
    }

    // Moves Entities and passes panel information
    public void move() {
        invaders.moveArmy(this,bullets);
        shooter.move(this,bullets);
    }

    // Needs to be re-written, messy
    public void checkCollision() {
        
        for (int i = 0; i < bullets.getbullets().size(); i++) {
            if(barrierHit(bullets.getbullets().get(i))){
                bullets.getbullets().remove(i);
            }else if(bullets.getbullets().get(i).velocity.getY() > 0){
                if(hitBox(shooter.getX(),shooter.getY(),shooter.width,bullets.getbullets().get(i).getX(),bullets.getbullets().get(i).getY())){
                    if(bullets.getbullets().get(i).color == Color.BLUE){
                        bullets.getbullets().get(i).upgrade(this);
                    }
                    bullets.getbullets().remove(i);
                    shooter.playerHit();
                    // Changes selection to lose state
                    if(shooter.getHealth() == 0){selection = 3;}
                    System.out.print("DEBUG - 3 \n");
                }
            }else{
                for (int j = 0; j < invaders.enemyArray.size(); j++) {
                    if(hitBox(invaders.enemyArray.get(j).getX(),invaders.enemyArray.get(j).getY(),30,bullets.getbullets().get(i).getX(),bullets.getbullets().get(i).getY())){
                    invaders.hit(j);
                    // Changes selection to win state
                    if(invaders.enemyArray.isEmpty()){selection = 2;}
                    shooter.hit();
                    bullets.getbullets().remove(i);
                    j = invaders.enemyArray.size();
                    }
                }
            }
        }
    }
   

    public void restart() {
        velocity += 100;
        invaders = new EnemyHandler();
        bullets = new BulletHandler(velocity);
        enemy_timer.stop();
        player_timer.stop();
        shooter.locationRespawn();
    }
    
    public int getSelection(){
        return selection;
    }
    
    public boolean hitBox(int x, int y, int size, int u, int v){
        if (u < x+size & u > x){
            if (v < y+size & v > y){
                return true;
            }
        }
        return false;
    }
    
    public boolean barrierHit(Bullet bull){
        
        for (int k = 0; k < barriers.arrayOfBarriers.size();k++){
            Barrier temp = barriers.arrayOfBarriers.get(k);
            if (hitBox(temp.coordinates.getX(),temp.coordinates.getY(),100,bull.getX(),bull.getY())){
                System.out.print("DEBUG - 2 \n");
                for(int v = 0; v < temp.barrierArray.size(); v++){
                    BarrierPiece temp2 = temp.barrierArray.get(v);
                    if (hitBox(temp2.coordinates.getX(),temp2.coordinates.getY(), 10,bull.getX(),bull.getY())){
                        temp.removePiece(v);
                        System.out.print("DEBUG - 1 \n");
                        return true;
                    }
                }
            }
           
        }
         return false;
    }
}
