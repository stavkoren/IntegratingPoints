import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class InteresByTendency implements InteresMethod {
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
    private double threshold;

    public InteresByTendency(List<Report> r) {
        Point first = new Point(r.get(0).getAgentLocation());
        first.setInfo(IN_PLACE);
        this.pointsList.add(first);
        Point prev = first;

        //set from where came
        for (int i = 1; i < r.size(); ++i) {
            Point newPoint = new Point(r.get(i).getAgentLocation());
            newPoint.setInfo(from(prev, newPoint));
            this.pointsList.add(newPoint);
            prev = newPoint;
        }
        this.setThreashold();
    }

    private void setThreashold() {
        List<Integer> valuesList = new LinkedList<>();
        int step = -1;
        int trend = IN_PLACE;
        for (Point p : this.pointsList) {
            if (p.getInfo() != trend) {
                step = 0;
                trend = p.getInfo();
            } else {
                step += 1;
            }
            p.setValue(step);
            valuesList.add(step);
        }
        this.threshold = Math.ceil(MathUtils.getMean(valuesList) + 1*MathUtils.getSTD(valuesList)); //mean + 1std and than round it up
    }


    //check if the tendency is more than threshold. if yes - mark as interesting point
    public Set<Point> getInterestPoints() {
        Set<Point> list = new HashSet<>();
        for (Point p : this.pointsList) {
            if (p.getValue() > this.threshold)
                list.add(p);
        }
        return list;
    }


    //check what the tendency
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
