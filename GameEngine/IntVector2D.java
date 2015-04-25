
package GameEngine;

/**
 *
 * @author myn
 */
// vector class for use in co-ordinates and velocity
public class IntVector2D {
    private float x, y; // x precise, y precise;
    
    // Constructors
    
    public IntVector2D() {
        this(0,0);
    }
    
    public IntVector2D(int x, int y) {
        this.x = (float)x;
        this.y = (float)y;
    }

    public IntVector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    // Getters
    
    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }
    
    public float getXPrecise() {
        return x;
    }
    
    public float getYPrecise() {
        return y;
    }
    
    // Setters
    
    public void setX(int x) {
        this.x = (float)x;
    }

    public void setY(int y) {
        this.y = (float)y;
    }

    public void setXPrecise(float x) {
        this.x = x;
    }

    public void setYPrecise(float y) {
        this.y = y;
    }
    
    // Operations
    
    public void addVector(IntVector2D vector2) {
        x += vector2.getXPrecise();
        y += vector2.getYPrecise();
    }

    public void scaleVector(int factor) {
        x *= factor;
        y *= factor;
    }
}

