
package killscreen;

import GameEngine.IntVector2D;
import MainMenu.Button;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Loser extends JPanel {
    // initialising the timers
    Timer paint_timer, firework_rate,Button_timer,paintTimer;
    
    int x = 500, selection = 0, selectionCandidate = 0, state = 0, i= -1,paint_updatePS = 60;
    
    final int paintUpdateRate = 1;
 
    boolean showButton = false;
    boolean hovered;
    
    IntVector2D menuSize;
    ArrayList<Button> buttons = new ArrayList<>();;
    Button tempButton;
     //initialising the images to be used in the paint
    ImageIcon Background = new ImageIcon(getClass().getResource("stars.gif"));
    ImageIcon Explosion  = new ImageIcon(getClass().getResource("TESTGIF.gif"));
    ImageIcon Banner     = new ImageIcon(getClass().getResource("DIED_1.PNG"));
    ImageIcon Banner2    = new ImageIcon(getClass().getResource("Game Over_1.PNG"));
 
    
    
  
    FireWorkHandler firework = new FireWorkHandler(); 
    FireWorkHandler  firework2 = new FireWorkHandler();


    public Loser(IntVector2D windowSize) {
//         buttons = new ArrayList<>();
            addMouseListener(new MouseListener() {

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseClicked(MouseEvent e) {
                // Sends key position to be checked
                actuateMouse(new IntVector2D(e.getX(),e.getY()),true);
            }
        });
            addMouseMotionListener(new MouseMotionListener() {
            @Override 
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseMoved(MouseEvent e) {
                actuateMouse(new IntVector2D(e.getX(),e.getY()),false);
                repaint();
            }
        });
        menuSize = windowSize;
    

        
        this.Button_timer = new Timer(500, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                showButton = true;
               
                ImageIcon unselected = new ImageIcon(getClass().getResource("unselected.png"));
                ImageIcon selected = new ImageIcon(getClass().getResource("selected.png"));
                ImageIcon hovered = new ImageIcon(getClass().getResource("hovered.png"));
                buttons.add(new Button("EXIT", new IntVector2D(400, 480), new IntVector2D(70, 40), new IntVector2D(175, 80),  -1, unselected, selected, hovered,0));
                buttons.add(new Button("PLAY AGAIN" , new IntVector2D(200, 480), new IntVector2D(55, 40), new IntVector2D(175, 80), 1, unselected, selected, hovered,0));
                
               // repaint();
            }
        }));
        Button_timer.start();
     }

    @Override
     public void paint(Graphics win) {
        // Clears window
        super.paint(win);
        Graphics2D window = (Graphics2D) win;
        window.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        win.drawImage(Background.getImage(), -4, -5, 805, 600, this);
        win.drawImage(Explosion.getImage(), 200, 130, this);
        win.drawImage(Banner.getImage(), 75, 0, this);
        win.drawImage(Banner2.getImage(), 150, 100, this);
     
    
        if (showButton == true){
          for( int j=0;j<buttons.size();j++) {
            if(selectionCandidate == j) {
                if(hovered) {
                    state = 2; // selected and hovered
                    }
                else {
                    state = 1; // selected but not hovered
                    }
                }
                else {
                    state = 0; // not selected 
                }
            buttons.get(j).paint(window,state);
            }     
        }
    } 
     
      public void actuateMouse(IntVector2D mouseLocation, boolean clicked) {
        int i;
        hovered = false;
        for(i=0;i<buttons.size();i++) {    
            if (    mouseLocation.getX() >= buttons.get(i).boxCoordinates.getX()
                 && mouseLocation.getX() <= (buttons.get(i).boxCoordinates.getX() + buttons.get(i).buttonSize.getX()) 
                 && mouseLocation.getY() >= buttons.get(i).boxCoordinates.getY()
                 && mouseLocation.getY() <= (buttons.get(i).boxCoordinates.getY() + buttons.get(i).buttonSize.getY())) {
                hovered = true;
                if(clicked){selection = buttons.get(i).selection;} else {selectionCandidate = i;}
            }
        }
    }
     public int getSelection() {
        return selection;
    }
}
