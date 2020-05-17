import java.util.LinkedList;
import java.util.List;

public class InteresByTendency {
    static private final int NORTH = 1;
    static private final int NORTH_EAST = 2;
    static private final int EAST = 3;
    static private final int EAST_SOUTH = 4;
    static private final int SOUTH = 5;
    static private final int SOUTH_WEST = 6;
    static private final int WEST = 7;
    static private final int WEST_NORTH = 8;
    static private final int IN_PLACE = 9;

    private List<Point> pointsList = new LinkedList<>();
    private int trend = IN_PLACE;
    private int steps = 0;
    private int threshold = 3;

    public InteresByTendency(List<Report> r) {
        Point first = new Point(r.get(0).getAgentLocation());
        first.setInfo(IN_PLACE);
        this.pointsList.add(first);
        Point prev = first;

        for (int i = 1; i < r.size(); ++i) {
            Point newPoint = new Point(r.get(i).getAgentLocation());
            newPoint.setInfo(from(prev, newPoint));
            this.pointsList.add(newPoint);
            prev = newPoint;
        }
    }


    public List<Point> getInterestPoints() {
        List<Point> list = new LinkedList<>();
        for (Point p : this.pointsList) {
            if (p.getInfo() != this.trend) {
                if (this.trend > this.threshold)
                    list.add(new Point(p));
                this.steps = 0;
                this.trend = p.getInfo();
            } else {
                this.steps += 1;
            }

        }
        return list;
    }


    private int from(Point fromPoint, Point toPoint) {
        int x = toPoint.getX() - fromPoint.getX();
        int y = toPoint.getY() - fromPoint.getY();
        if (x > 0) { //going forward
            if (y > 0)
                return EAST_SOUTH;
            if (y < 0)
                return NORTH_EAST;
            return EAST; //for y = 0
        }
        if (x == 0) {
            if (y > 0)
                return SOUTH;
            if (y < 0)
                return NORTH;
        }
        if (x < 0) { //going back
            if (y > 0)
                return SOUTH_WEST;
            if (y < 0)
                return WEST_NORTH;
            return WEST;
        }
        return IN_PLACE;
    }


}
