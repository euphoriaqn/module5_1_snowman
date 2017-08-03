package star;

/**
 * Created by user22 on 03.08.2017.
 */
public class Point {
    public static final Point ZERO = new Point(0, 0);

    private double x,y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
