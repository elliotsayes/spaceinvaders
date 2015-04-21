
package GameEngine;

/**
 *
 * @author myn
 */
// vector class for use in co-ordinates and velocity
public class IntVector2D {
    private int x;
    private int y;

    public IntVector2D() {
        this(0,0);
    }

    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXandY (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addVector(IntVector2D vector2) {
        x += vector2.getX();
        y += vector2.getY();
    }

    public void scaleVector(int factor) {
        x *= factor;
        y *= factor;
    }
}

