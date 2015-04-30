/*
 Handles the Barriers that allow for the multiple paints. 
 */
package GameScreen;

import GameEngine.IntVector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BarrierHandler {
   ArrayList<Barrier> arrayOfBarriers = new ArrayList();
   //IntVector2D coordinates; 
   
int X = 50;
int Y = 400;       
int width = 70;
int height = 40;
        
      
    BarrierHandler() {
       for (int i = 0 ; i < 800; i = i + 200){
           arrayOfBarriers.add(new Barrier(new IntVector2D(X+i,Y), new IntVector2D(width , height)));
           
       }
    }
 public void piecePaint(Graphics2D barrier) {
        barrier.setColor(Color.GREEN);
        for (int i = 0 ; i < arrayOfBarriers.size(); i++  ){
            arrayOfBarriers.get(i).piecePaint(barrier);
        }
        
    
    }

    public ArrayList<Barrier> getArrayOfBarriers() {
        return arrayOfBarriers;
    }

    public int getArraySize(){
        return arrayOfBarriers.size();
    }
    
    public int getPieceArraySize(int i){
        return arrayOfBarriers.get(i).getNoOfPieces();
    }
    
    public IntVector2D getbarriercoordinates(int i) {
        return (arrayOfBarriers.get(i).getCoordinates());
       
    }
   public IntVector2D getBarrierSize(int i){
       return (arrayOfBarriers.get(i).getSize());
   }
   
   public IntVector2D getPiececoordinates(int i,int j){
       return (arrayOfBarriers.get(i).getPieceCoordinates(j));
   }
   public void pieceRemove(int i, int j){
   arrayOfBarriers.get(i).removePiece(j);   
   }
   

    
    
    
}
