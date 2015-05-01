package GameScreen;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    JPanel temp_this = this;
    int selection;
    boolean pause = false;
    int velocity = 300;
    int level = 1;
    ImageIcon pauseImage = new ImageIcon(getClass().getResource("Paused.png"));
    ImageIcon helpPage = new ImageIcon(getClass().getResource("startMenu.png"));
    ImageIcon nextLevel = new ImageIcon(getClass().getResource("nextLevel.png"));
    
    // Menu Numbers
    int mainMenu = 0;
    int winScreen = 2;
    int loseScreen = 3;
    int options = 4;
    
    // Game Screen Entities
    EnemyHandler invaders = new EnemyHandler();
    BasicPlayer shooter = new BasicPlayer();
    BarrierHandler barriers = new BarrierHandler();
    BulletHandler bullets = new BulletHandler(velocity);
    
    // Game timer for repaint
    Timer paint_timer, player_timer, enemy_timer, powerUpTimer;
    int paint_updateInterval = 300;
    int player_updateInterval = 300;
    int enemy_updateInterval = 200;
    
    // gameScreen Constructor
    public GamePanel() throws IOException, URISyntaxException {
        // ActionListener for time, what happens when timer executes
        this.paint_timer = new Timer(1000/paint_updateInterval, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                try {    
                    checkCollision();
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                    selection = mainMenu;
                }    
                
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    try {
                        restart();
                    } catch (IOException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                    selection = winScreen;
                }
                if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
                    selection = loseScreen;
                }
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    try {
                        shooter = new PlayerMarlo();
                        invaders.enemyArray.clear();
                        invaders.enemyArray.add(new MarloEnemy(80,80));
                    } catch (IOException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        window.setColor(Color.white);
        window.drawString("Level:", 500, 20);
        window.drawString(String.valueOf(level), 580, 20);
        if (pause){window.drawImage(pauseImage.getImage(), 200, 150, 400, 300, null);}
        if (!pause & !enemy_timer.isRunning() & level == 1){window.drawImage(helpPage.getImage(), 200, 150, 400, 300, null);}
        if(!pause & !enemy_timer.isRunning() & level != 1){window.drawImage(nextLevel.getImage(), 200, 150, 400, 300, null);}
    }

    // Moves Entities and passes panel information
    public void move() {
        invaders.moveArmy(this,bullets);
        shooter.move(this,bullets);
    }

    public void checkCollision() throws IOException, URISyntaxException {
        
        for (int i = 0; i < bullets.getbullets().size(); i++) {
            if(barrierHit(bullets.getbullets().get(i).getX(),bullets.getbullets().get(i).getY(),bullets.getbullets().get(i).size)){
                bullets.getbullets().remove(i);
            }else if(bullets.getbullets().get(i).velocity.getY() > 0){
                if(hitBox(shooter.getX(),shooter.getY(),shooter.width,bullets.getbullets().get(i).getX(),bullets.getbullets().get(i).getY(),bullets.getbullets().get(i).size)){
                    //if(bullets.getbullets().get(i).color != Color.RED){
                        bullets.getbullets().get(i).upgrade(this);
                        bullets.getbullets().remove(i);
                    //}
                    //else {
                    //shooter.playerHit();
                    //bullets.getbullets().get(i).upgrade(this); 
                    //bullets.getbullets().remove(i);
                    //}
                    // Changes selection to lose state
                    if(shooter.getHealth() == 0){selection = loseScreen;}
                    System.out.print("DEBUG - 3 \n");
                }
            }else{
                for (int j = 0; j < invaders.enemyArray.size(); j++) {
                    if(hitBox(invaders.enemyArray.get(j).getX(),invaders.enemyArray.get(j).getY(),invaders.enemyArray.get(j).size,bullets.getbullets().get(i).getX(),bullets.getbullets().get(i).getY(),bullets.getbullets().get(i).size)){
                    invaders.hit(j);
                    // Changes selection to win state
                    if(invaders.enemyArray.isEmpty()){
                        if (shooter.score > 20000){
                            selection = winScreen;
                        }else{
                            restart();
                            return;
                        }
                    }
                    shooter.hit();
                    bullets.getbullets().remove(i);
                    j = invaders.enemyArray.size();
                    }
                    
                        
                    
                }
            }
        }
        for (int j = 0; j < invaders.enemyArray.size(); j++) {
        boolean temp = barrierHit(invaders.enemyArray.get(j).getX(),invaders.enemyArray.get(j).getY(),invaders.enemyArray.get(j).size);}
    }
   
    public void restart() throws IOException, URISyntaxException {
        level += 1;
        if(level < 6){invaders = new EnemyHandler(level);}else{
            invaders = new bossMan();
        }
        bullets = new BulletHandler(velocity);
        //barriers = new BarrierHandler();
        enemy_timer.stop();
        player_timer.stop();
        shooter.locationRespawn();
        shooter.reset();
        
    }
    
    public int getSelection(){
        return selection;
    }
    
    public boolean hitBox(int x, int y, int size, int u, int v, int size2) {    
            if (u < x + size & u > x) {
                if ((v < (y + size)) & (v > y)) {
                    System.out.print("DEBUG - hit 1 \n");
                    return true;
                }else if (((v + size2) < (y + size)) & (v + size2 > y)) {
                    System.out.print("DEBUG - hit 2 \n");
                    return true;
                }
            } else if (u + size2 < x + size & u + size2 > x){
                if ((v < (y + size)) & (v > y)) {
                    System.out.print("DEBUG - hit 3 \n");
                    return true;
                }else if (((v + size2) < (y + size2)) & (v + size2 > y)) {
                    System.out.print("DEBUG - hit 4 \n");
                    return true;
                }
            }
            return false;
        }
    
    public boolean barrierHit(int x, int y, int size){
        for (int k = 0; k < barriers.arrayOfBarriers.size();k++){
            Barrier temp = barriers.arrayOfBarriers.get(k);
            if (hitBox(temp.coordinates.getX(),temp.coordinates.getY(),100,x,y,size)){
                System.out.print("DEBUG - 2 \n");
                for(int v = 0; v < temp.barrierArray.size(); v++){
                    BarrierPiece temp2 = temp.barrierArray.get(v);
                    if (hitBox(temp2.coordinates.getX(),temp2.coordinates.getY(), 10,x,y,size)){
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
