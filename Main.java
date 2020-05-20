import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {



        var a=LoggerParser.GetReports("test2.xml");
        var b = new InteresPointFinder(a);
        var s = b.getInteresPoints();

        for (Report r:a) {
            System.out.println("Agent Loc: ("+r.getAgentLocation().getX()
                    +","+r.getAgentLocation().getY()+")");
            System.out.println("Points chacked:");
            for (Point p:r.GetPointsChecked()){
                System.out.println(p);
            }
            System.out.println("Points compared:");
            for (Pair<Point,Point> p:r.GetPointsCompared()){
                System.out.println(p.getLeft()+" "+p.getRight());
            }

        }

    }
}
