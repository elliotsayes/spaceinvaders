/*
 Handles the Barriers that allow for the multiple paints. 
 */
package GameScreen;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class BarrierHandler {
   ArrayList<Barrier> arrayOfBarriers = new ArrayList();
   IntVector2D coordinates; 
   

    BarrierHandler() {
       for (int i = 0 ; i < 800; i = i + 200){
           arrayOfBarriers.add(new Barrier(400,50+i));
           
       }
    }
 public void piecePaint(Graphics2D barrier) {
        
        for (int i = 0 ; i < arrayOfBarriers.size(); i++  ){
            arrayOfBarriers.get(i).piecePaint(barrier);
        }
        
    
    }

    public ArrayList<Barrier> getArrayOfBarriers() {
        return arrayOfBarriers;
    }
   

   

    
    
  
    
}
