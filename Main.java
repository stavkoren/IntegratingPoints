import sun.rmi.runtime.Log;

import java.util.List;

public class Main {
    public static void main(String[] args) {



        var a=LoggerParser.GetReports("test2.xml");
        var b = new InteresByCompares(a);
        var s = b.getInterestPoints();
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
