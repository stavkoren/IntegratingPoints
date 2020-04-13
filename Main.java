import sun.rmi.runtime.Log;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        var a=LoggerParser.GetReports("test2.xml");
        for (Report r:a) {
            System.out.println("Agent Loc: ("+r.getAgentLocation().getX()
                    +","+r.getAgentLocation().getY()+")");
            System.out.println("Checked points: "+r.GetPointsChecked().size());
            System.out.println("Compared points: "+r.GetPointsCompared().size());
        }

    }
}
