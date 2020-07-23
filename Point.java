import java.util.Objects;

//save location on map
public class Point {
    private int x;
    private int y;
    private float value;
    private int beenHereCounter = 0;


    public static Integer NO_INFO = -1;
    private Integer info = NO_INFO;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    public Point(float value) {
        this(0, 0);
        this.value = value;
    }
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
        this.value = p.getValue();
        this.info = p.getInfo();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void here() {
        this.beenHereCounter++;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    //compare points
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;
        if(this.getX() == point.getX() && this.getY() == point.getY())
            return true;
        return false;
    }
    public Integer getInfo() {
        return info;
    }

    public Point setInfo(Integer info) {
        this.info = info;
        return this;
    }

    @Override
    public String toString() {
        return "("+getX()+","+getY()+")";
    }
}
