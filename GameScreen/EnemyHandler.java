
package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class EnemyHandler {
        
        // Enemy config
        int movePos = 1;
        int enemyArraySize = 15;
        int fireDelay = 5;
        int horizontalDirection = 10;
        ArrayList<BasicEnemy> enemyArray;
        //BulletHandler bullets = new BulletHandler(1 , fireDelay);
        Random ran = new Random();
        
        // EnemyHandler Constructor initialises array setting enemy types and layout 
        public EnemyHandler() throws IOException, URISyntaxException{
             // Initialises enemys
            enemyArray = new ArrayList<>();
            int x = 20;
            int y = 30;
            for(int temp1 = 0;temp1!=3;temp1++){
                for(int temp = 0;temp!=enemyArraySize;temp++){
                  enemyArray.add(new BasicEnemy(x,y));
                  //enemyArray.add(new beyonceEnemy(x,y));
                x = x + 40;
                }
            y += 60;
            x = 20;
            }
            
        }
        
        
        // Updates enemy position, controls movement pattern
	void moveArmy(JPanel win, BulletHandler bullets) {
                int sze = enemyArray.size() - 1;
                int shift = 0;
                // Checks there are enemys and then updates there postion
                //if (sze == 0){return;}
                // Checks if they have reached end of screen and inverses movement direction
                for(int temp1 = 0;temp1 <= sze;temp1++){
                if(((enemyArray.get(temp1).getY() > win.getHeight() - 30)& horizontalDirection == 10) | ((enemyArray.get(temp1).getY() < 20)& horizontalDirection == -10) ){
                    horizontalDirection = horizontalDirection * -1;
                }
                if ((enemyArray.get(temp1).getX() == win.getWidth() - 30) | (enemyArray.get(temp1).getX() == 0)) {
                    movePos = movePos * -1;
                    temp1 = sze + 1;
                    shift = horizontalDirection;
                }
                
                }
                for(int temp = 0;temp!=enemyArray.size();temp++){
                    IntVector2D tempVector = new IntVector2D(movePos, shift);
                    enemyArray.get(temp).move(tempVector,bullets);

                    // Spawns enemy bullets
                    /*int num = ran.nextInt(50000);
                    if (num<= 20 & num>= 10 ){
                        bullets.spawnMissile(enemyArray.get(temp).getX(), enemyArray.get(temp).getY(), 1);
                    }
                    // Temp way to add different bullet types
                    if (num<= 1){
                        bullets.bullets.add(new HealthPowerUp(enemyArray.get(temp).getX(), enemyArray.get(temp).getY(), 1));
                    }*/
                }
            //bullets.move();
            //bullets.kill();
	}
        
        // Calls paint method for each enemy
        public void paint(Graphics2D win){
             for(int temp = 0;temp!=enemyArray.size();temp++){
                    enemyArray.get(temp).paint(win);
            }
            //bullets.paint(win);
        }
        
        // Returns enemy array
        public ArrayList<BasicEnemy> getArmy(){
            return enemyArray;
        }
        
        //public ArrayList<Bullet> getAmmo(){
        //    return bullets.getbullets();
        //}
        
        // Hits the enemy then checks for life..
        void hit(int temp) {
            System.out.print("Enemy : ");
            System.out.print(temp);
            System.out.print(" Health: ");
            System.out.print(enemyArray.get(temp).health);
            System.out.print("\n");
        if(enemyArray.get(temp).hit(1)){
           enemyArray.remove(temp); 
        }
        
    }
          
}
