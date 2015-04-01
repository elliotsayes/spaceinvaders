/*
 Organises the Barrier peices to form the barrier that is the required shape.
 */
package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Barrier {

    int startnumberX,startnumberY,width,height;
    ArrayList<BarrierPiece> barrierArray = new ArrayList();
    IntVector2D coordinates, size;

   
    Barrier(IntVector2D coordinates, IntVector2D size ) {
        this.coordinates = coordinates;
        this.size = size;
       int x, y;
       
        for (x = coordinates.getX(); x < coordinates.getX() + size.getX(); x += 2) {
            for (y = coordinates.getY(); y < coordinates.getY() + size.getY(); y += 2) {
                if ((x + y > (coordinates.getY() + coordinates.getX() + 10)) && 
                        
                        (y + (( coordinates.getX() + size.getX() ) - ( x + coordinates.getY() +2 )) > 10) && 
                        
                        (!(x > 18 + (coordinates.getX()) && x < (50+ coordinates.getX()) && y > (10+ coordinates.getY())))) {
                    
                    
                    barrierArray.add(new BarrierPiece(new IntVector2D (x,y)));
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

  

    public IntVector2D getCoordinates() {
        return coordinates;
    }
    
    public IntVector2D getPieceCoordinates(int i){
        return barrierArray.get(i).getCoordinates();
    }

    public IntVector2D getSize() {
        return size;
    }

    public ArrayList<BarrierPiece> getBarrierArray() {
        return barrierArray;
    }
    public int getNoOfPieces(){
        return barrierArray.size();
    }
    
    public void removePiece(int i){
        barrierArray.remove(i);
    }
} 

