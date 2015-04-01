/*
 Organises the Barrier peices to form the barrier that is the required shape.
 */
package GameScreen;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Barrier {

    //IntVector2D coordinates;
    ArrayList<BarrierPiece> barrierArray = new ArrayList();

   
    Barrier(int StartnY, int StartnX ) {
       int x, y;
        int startnumberY = StartnY;
        int startnumberX = StartnX;
        for (x = startnumberX; x < startnumberX + 70; x += 2) {
            for (y = startnumberY; y < startnumberY + 40; y += 2) {
                if ((x + y > (startnumberY + startnumberX + 10)) && (y + (( startnumberX +70 ) - ( x + startnumberY +2 )) > 10) && (!(x > 18 + (startnumberX) && x < (50+startnumberX) && y > (10+ startnumberY)))) {
                    barrierArray.add(new BarrierPiece(x, y));
                }
            }
       }
    }
    //calls the paint method for the individual pieces of the barrier
    public void piecePaint(Graphics2D barrier) {
        
        for (int i = 0 ; i < barrierArray.size(); i++  ){
            barrierArray.get(i).piecePaint(barrier);
        }
        
    
    }
    //checking if the barrier peice has been hit.
    void hit(int temp){
        if(barrierArray.get(temp).getHit(temp)){
            barrierArray.remove(temp);
        }
            
        
    }

   

    public ArrayList<BarrierPiece> getBarrierArray() {
        return barrierArray;
    }
}
