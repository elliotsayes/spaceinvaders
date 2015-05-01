
package GameScreen;

import GameEngine.AudioPlayer;
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
        int movePos2 = 1;
        int enemyArraySize = 15;
        int fireDelay = 5;
        int verticleMovement  = 10;
        ArrayList<BasicEnemy> enemyArray;
        //BulletHandler bullets = new BulletHandler(1 , fireDelay);
        Random ran = new Random();
        IntVector2D tempVector = new IntVector2D(1,0);
        
        // EnemyHandler Constructor initialises array setting enemy types and layout
        public EnemyHandler() throws IOException, URISyntaxException{
            this(1);
        }
        public EnemyHandler(int level) throws IOException, URISyntaxException{
             // Initialises enemys
            
            enemyArray = new ArrayList<>();
            int x = 20;
            int y = 30;
            for(int temp1 = 0;temp1<=3;temp1++){
             int hp = level * ran.nextInt(level);
             if(hp == 0){hp = level*level;}
             x = (30+(5*hp))/2;
             if((x >= 30)&(temp1 == 0)){
                 enemyArray.add(new AdvancedEnemy(300,y));
                 y+= 70;
                 //hp = 1;
                 //level = 1;
                 continue;
             }
             if(hp > 10){hp = ran.nextInt(8);}
             x = (30+(5*hp))/2;
                for(int temp = 0;temp<=400/(30+(5*hp));temp++){
                  enemyArray.add(new BasicEnemy(x,y,hp,(30+(5*hp)),"Basic_Enemy_Sprite.gif",ran.nextInt(5*hp),ran.nextInt(5*hp),ran.nextInt(5*hp)));
                  //enemyArray.add(new beyonceEnemy(x,y));
                x = x + ((30+(5*hp)));
                }
            y += (30+(5*hp)) + (30+(5*hp))/2;
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
                if(enemyArray.get(temp1).type == 0){
                if(((enemyArray.get(temp1).getY() > win.getHeight() - enemyArray.get(temp1).size)& verticleMovement == 10) | ((enemyArray.get(temp1).getY() < 20)& verticleMovement == -10) ){
                    verticleMovement = verticleMovement * -1;
                }
                if ((enemyArray.get(temp1).getX() == win.getWidth() - enemyArray.get(temp1).size) | (enemyArray.get(temp1).getX() == 0)) {
                    movePos = movePos * -1;
                    temp1 = sze + 1;
                    tempVector.setY(verticleMovement);
                }
                
                }else{
                if (enemyArray.get(temp1).getX() == win.getWidth() - enemyArray.get(temp1).size){
                    movePos2 = -1;
                }else if(enemyArray.get(temp1).getX() == 0){
                    movePos2 = 1;                  
                }else{
                    movePos2 = 0;
                }
                tempVector.setX(movePos2);
                enemyArray.get(temp1).move(tempVector,bullets);
                    
                
                     
                }
                }
                
                
                for(int temp = 0;temp!=enemyArray.size();temp++){
                    if( enemyArray.get(temp).type == 0){
                    tempVector.setX(movePos);
                    enemyArray.get(temp).move(tempVector,bullets);
                    }
                        
                    
                    
                    
                }
                tempVector.setY(0);
  
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
