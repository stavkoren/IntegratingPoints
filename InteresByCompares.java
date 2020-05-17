import java.util.LinkedList;
import java.util.List;

public class InteresByCompares {
    private List<Report> report;
    private int threshold;

    public InteresByCompares(List<Report> r) {
        this.report = r;
        this.setThreshold();
    }

    public List<Point> getInterestPoints() {
        List<Point> pointList = new LinkedList<>();
        for (var r : this.report) {
            if(r.GetPointsCompared().size() > this.threshold)
                pointList.add(new Point(r.getAgentLocation()));
        }
        return pointList;
    }

    //by average
    private void setThreshold(){
        int sum = 0;
        for (var r : this.report) {
            sum += r.GetPointsCompared().size();
        }
        this.threshold = sum/this.report.size();
    }

}
