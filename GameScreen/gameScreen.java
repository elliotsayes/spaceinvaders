
package GameScreen;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;


public class gameScreen extends JPanel {
    
    // Game Screen Entities
    troops invaders = new troops();
    player shooter = new player();
    
    // gameScreen Constructor
    public gameScreen() {
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
                            shooter.keyPressed(e);
			}
		});
                this.setFocusable(true);
                this.requestFocus();
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
    public void collision(){
        ArrayList<enemy> badies = invaders.getArmy();
        ArrayList<bullet> pewpew = shooter.getAmo();
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
    
        
   
    
        
    
}
    
    // Checks if any enemys are left
    public boolean winner(){
       return invaders.getArmy().isEmpty();
   }
}
