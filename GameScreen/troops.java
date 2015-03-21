
package GameScreen;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class troops {
        
        int movePos = 1;
        int troopSize = 15;
        ArrayList<enemy> army;
        
        // troops Constructor initialises array setting enemy layout 
        public troops(){
            
             // Initialises enemys
            army = new ArrayList<>();
            int x = 20;
            int y = 20;
            for(int temp = 0;temp!=troopSize;temp++){
                army.add(new enemy(x,y));
                x = x + 40;
            }
        }
        
        // Updates enemy position, controls movement pattern
	void moveArmy(JPanel win) {
                int y = 0;
                int sze = army.size();
                // Checks there are enemys and then updates there postion
                if (sze == 0){return;}
                // Checks if they have reached end of screen and inverses movement direction
                if ((army.get(sze - 1).getx() == win.getWidth() - 30) | (army.get(0).getx() == 0)) {
                    movePos = movePos * -1;
                    y = 10;
                } 
                for(int temp = 0;temp!=army.size();temp++){
                army.get(temp).move(movePos, y);     
            }
                
	}
        
        // Calls paint method for each enemy
        public void paint(Graphics2D win){
             for(int temp = 0;temp!=army.size();temp++){
                    army.get(temp).paint(win);
            }
        }
        
        // Returns enemy array
        public ArrayList<enemy> getArmy(){
            return army;
        }
        
        // Hits the enemy then checks for life..
        void hit(int temp) {
        if(army.get(temp).hit()){
           army.remove(temp); 
        }
        
    }
          
}
