package killscreen;

import GameEngine.IntVector2D;
import MainMenu.Button;
import java.awt.Graphics2D;
import static java.awt.SystemColor.window;
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



public class Loser extends JPanel{
    IntVector2D menuSize;
    ArrayList<Button> buttons;
    Button tempButton;
    Timer test;
    int j = 0,selection = 0, selectionCandidate = 0, state = 0, i= -1;
    boolean temp = false, hovered;
      public Loser(IntVector2D windowsize) {
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
        menuSize = windowsize; 
        ImageIcon pic = new ImageIcon(getClass().getResource("DIED.PNG"));
        JLabel label = new JLabel(pic, JLabel.CENTER);
        ImageIcon otherpic = new ImageIcon(getClass().getResource("Game Over.PNG"));
        JLabel otherLabel = new JLabel(otherpic, JLabel.CENTER);
        this.add(label);
        this.add(otherLabel);
        
        
        
    
//         test = new Timer(5000, (new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
                
                
                temp = true;
                buttons = new ArrayList<>();
                ImageIcon unselected = new ImageIcon(getClass().getResource("unselected.png"));
                ImageIcon selected = new ImageIcon(getClass().getResource("selected.png"));
                ImageIcon hovered = new ImageIcon(getClass().getResource("hovered.png"));
                buttons.add(new Button("EXIT", new IntVector2D(200, 250), new IntVector2D(60, 40), new IntVector2D(175, 80),  1, unselected, selected, hovered,0));
                buttons.add(new Button("PLAY AGAIN" , new IntVector2D(400, 250), new IntVector2D(75, 40), new IntVector2D(175, 80), -1, unselected, selected, hovered,0));
      }
//            }
//        }));
//        test.start();
//      }

          void paint(Graphics2D loser){
          super.paint(loser);
          Graphics2D window = (Graphics2D) loser;
          //if (temp == true){
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
            buttons.get(j).paint(window
                    ,state);
       //     }     
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
