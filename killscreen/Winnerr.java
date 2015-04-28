
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

public class Winnerr extends JPanel {
    Timer paint_timer, firework_rate,test;
    int paint_updatePS = 60;
    int x = 500, selection = 0, selectionCandidate = 0, state = 0, i= -1, j=0;
    boolean hovered;
    final int paintUpdateRate = 1;
    Timer paintTimer;
    boolean temp = false;
    
    IntVector2D menuSize;
    ArrayList<Button> buttons = new ArrayList();
    Button tempButton;
    
 
    
    
  
    FireWorkHandler firework = new FireWorkHandler(); 
    FireWorkHandler  firework2 = new FireWorkHandler();


    public Winnerr(IntVector2D windowSize) {
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
        ImageIcon pic = new ImageIcon(getClass().getResource("Winner.PNG"));
        JLabel label = new JLabel(pic, JLabel.CENTER);
        this.add(label);
        this.paint_timer = new Timer(1000/paint_updatePS, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                firework.move();
                firework2.move();
                
                
                repaint();   
            }
        }));
        
        this.firework_rate = new Timer(2000, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i == 1){
                    firework = new FireWorkHandler();
                }
                else{
                    firework2 = new FireWorkHandler();
                    
                }
                i = i*-1;
                
            }
        }));
       
        paint_timer.start();
        firework_rate.start();
       // paint_timer.setRepeats(true);
        
        this.test = new Timer(5000, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                temp = true;
                buttons = new ArrayList<>();
                ImageIcon unselected = new ImageIcon(getClass().getResource("unselected.png"));
                ImageIcon selected = new ImageIcon(getClass().getResource("selected.png"));
                ImageIcon hovered = new ImageIcon(getClass().getResource("hovered.png"));
                buttons.add(new Button("EXIT", new IntVector2D(300, 480), new IntVector2D(60, 40), new IntVector2D(175, 80),  -1, unselected, selected, hovered));
                buttons.add(new Button("PLAY AGAIN" , new IntVector2D(300, 390), new IntVector2D(75, 40), new IntVector2D(175, 80), 1, unselected, selected, hovered));
                
            }
        }));
        test.start();
     }

    @Override
     public void paint(Graphics win) {
        // Clears window
        super.paint(win);
        Graphics2D window = (Graphics2D) win;
        window.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        firework.Paint(window);
        firework2.Paint(window);
    
        if (temp == true){
          for( j=0;j<buttons.size();j++) {
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
        hovered = false;
        for(j=0;j<buttons.size();j++) {    
            if (    mouseLocation.getX() >= buttons.get(j).boxCoordinates.getX()
                 && mouseLocation.getX() <= (buttons.get(j).boxCoordinates.getX() + buttons.get(j).buttonSize.getX()) 
                 && mouseLocation.getY() >= buttons.get(j).boxCoordinates.getY()
                 && mouseLocation.getY() <= (buttons.get(j).boxCoordinates.getY() + buttons.get(j).buttonSize.getY())) {
                hovered = true;
                if(clicked){selection = buttons.get(j).selection;} else {selectionCandidate = j;}
            }
        }
    }
     public int getSelection() {
        return selection;
    }
}
