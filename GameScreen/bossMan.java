/*
 * Handles the Bosses movement and images
 * Extends the enemyHandler
 */
package GameScreen;

import GameEngine.AudioHandler;
import GameEngine.AudioPlayer;
import GameEngine.IntVector2D;
import MainMenu.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import static java.nio.file.Files.size;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import GameScreen.spriteHandler;
import java.awt.Image;

/**
 *
 * @author Braeden
 * 
 * Handles the Bosses movement and images
 * Extends the enemyHandler
 */
public class bossMan extends EnemyHandler {
    // Initialising the boss Music
    AudioPlayer music = new AudioPlayer("boss.mid", "BossMusic") ;
    // Initialising the Timers 
    Timer BossMusic_timer, Health_timer, Fade_timer;
    
    //Initialising the Boss components to Enemys
    BasicEnemy Boss;
    BasicEnemy Bossleg1;
    BasicEnemy Bossleg2;
    BasicEnemy Bossleg3;
    BasicEnemy Bossleg4;

    IntVector2D velocity;
    IntVector2D velocity1            = new IntVector2D(0,3);
    IntVector2D velocity2            = new IntVector2D(1,0);
    IntVector2D velocity3            = new IntVector2D(-1,0);
    double k = 0;
    int size =200;
    int r = 0 ;
    int HorizontalSpeed = 1;
    //Adding the image for the health bar
    ImageIcon CthulhuLogo            = new ImageIcon(getClass().getResource("Cthulhu logo.png"));  
    // Adding each image with different opacities
    ImageIcon CthulhuTitle0          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu0.png"));
    ImageIcon CthulhuTitle1          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu1.png"));
    ImageIcon CthulhuTitle2          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu2.png"));
    ImageIcon CthulhuTitle3          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu3.png"));
    ImageIcon CthulhuTitle4          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu4.png"));
    ImageIcon CthulhuTitle5          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu5.png"));
    ImageIcon CthulhuTitle6          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu6.png"));
    ImageIcon CthulhuTitle7          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu7.png"));
    ImageIcon CthulhuTitle8          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu8.png"));
    ImageIcon CthulhuTitle9          = new ImageIcon(getClass().getResource("TheDarkLordCthulhu9.png"));
    IntVector2D BOSSstartCoordinates = new IntVector2D(250,-295);
    IntVector2D Tenticles            = new IntVector2D(205,-280);
    
    // Adding each gif name into a string to use in the Basic Enemy contructor
    static String CthuluDarkLordLeg1 = "Tenticle 1 move.gif";
    static String CthuluDarkLord     = "BossManFinal.png";
    static String CthuluDarkLordLeg2 = "Tentacle2_move.gif";
    static String CthuluDarkLordLeg3 = "Tenticle3_fast.gif";
    static String CthuluDarkLordLeg4 = "Tenticle4.gif";
    

    public bossMan() throws IOException, URISyntaxException {
       
       super(); 
       enemyArray = new ArrayList<>();
    
       this.Boss = new BasicEnemy(BOSSstartCoordinates.getX(),BOSSstartCoordinates.getY(),100,size,CthuluDarkLord);
       this.Bossleg1 = new BasicEnemy(Tenticles.getX()+50,Tenticles.getY()+155,10,70,CthuluDarkLordLeg1);
       this.Bossleg2 = new BasicEnemy(Tenticles.getX()+85,Tenticles.getY()+160,10,80,CthuluDarkLordLeg2);
       this.Bossleg3 = new BasicEnemy(Tenticles.getX()+ 135,Tenticles.getY()+159,10,70,CthuluDarkLordLeg3);
       this.Bossleg4 = new BasicEnemy(Tenticles.getX()+ 170,Tenticles.getY()+160,10,70,CthuluDarkLordLeg4);
       enemyArray.add(Boss);
       enemyArray.add(Bossleg1);
       enemyArray.add(Bossleg2);
       enemyArray.add(Bossleg3);
       enemyArray.add(Bossleg4);
            this.Health_timer = new Timer(10,(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(k < 5){
                             k = k + 0.01;
                        }
                    }
                    
                }));
        //This Timer determines when the boss music is played
       this.BossMusic_timer = new Timer(5000, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               music.playSound();
               BossMusic_timer.stop();
               
               
               
                
                } 
            }));
       BossMusic_timer.start();
      
     //This Timer determines when to switch the image for the title
            this.Fade_timer = new Timer(150, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r < 8){
                    r++;
                    }
                else{
                    r = 9;
                    Fade_timer.stop();
                    }
                             
            }
            }));
            
      
    }
    // moveArmy is from the Enemy Handler, but Overwritten here for the boss movment 
    @Override
    public void moveArmy(JPanel win, BulletHandler bullets){
    if (enemyArray.size() != 0 ){
        if (enemyArray.get(0).getY() < 25){
            velocity = velocity1;
         }
        
        else{ //Fade_timer.start();
            
            if(HorizontalSpeed == 1){
            velocity = velocity2;}
            else{
                velocity = velocity3;
                }   
        }
        
          
        for (int k = 0; k < enemyArray.size();k++){
            enemyArray.get(k).coordinates.addVector(velocity);
        }
       
            
        if(enemyArray.get(0).getX()<=0){
            HorizontalSpeed = 1;
        }
        if (enemyArray.get(0).getX() >= 500){
            HorizontalSpeed = -1;
        }
    }
    }
    
  
// The hit function determines if the boss is below a certain y coordinate it is invulnerable to hits, 
// It then checks whether the tenticles of the boss have been destroyed before the boss can be hit
    
@Override
void hit(int temp) {           
            System.out.print("Enemy : ");
            System.out.print(temp);
            System.out.print(" Health: ");
            System.out.print(enemyArray.get(temp).health);
            System.out.print("\n");
        if(Boss.coordinates.getY()<25){return;}    
        if((temp == 0) & enemyArray.size() != 1 ){return;}    
        if(enemyArray.get(temp).hit(1)){
           enemyArray.remove(temp); 
        }       
    
}
// Paints everything on ghte scneen
 public void paint(Graphics2D win) {
        win.setColor(Color.green);
        
//        Another_timer.start();
        if(enemyArray.get(0).getY() < 25){
//            for (int r = 3; r >= 0 ; r--){
                
                   //String test = new String("CthulhuTitle"+Integer.toString(r)+".png");
                   ImageIcon DrawImage = new ImageIcon(getClass().getResource("TheDarkLordCthulhu"+Integer.toString(r)+".png"));
                   Fade_timer.start();
                   win.drawImage(DrawImage.getImage(), 100, 300, null);
                   
//            }
        }
        win.drawImage(CthulhuLogo.getImage(), 20, 40, null);
       // This determines whether the health of a certain figure is below a certain point - after that turns the
        // health bar red. But is otherwise green
        for (int i = 0; i<enemyArray.size();i++){
           
                enemyArray.get(i).paint(win);
            if ((int) (enemyArray.get(i).health*k) < enemyArray.get(i).health*2){
                win.setColor(Color.red); 
                }
                // (double)enemyArray.get(i).health; 
             if(enemyArray.get(i).health < 5){win.setColor(Color.red);}
                     win.fill3DRect(80, 40 + (i*20),(int) (enemyArray.get(i).health*k), 10,true);
                     win.setColor(Color.green);
                     // Starting the timer for updated paints on the health bar so it is a smooth opening
                     Health_timer.start();
          
            
        
        }
    }
    
}
