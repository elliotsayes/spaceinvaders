
package killscreen;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Loser extends JPanel{
      public Loser() {
        ImageIcon pic = new ImageIcon(getClass().getResource("loser.gif"));
        JLabel label = new JLabel(pic, JLabel.CENTER);
        this.add(label);
    }
}
