import java.util.LinkedList;
import java.util.List;

public class InteresByChecks {
    private List<Report> report;
    private int threshold;

    public InteresByChecks(List<Report> r) {
        this.report = r;
        this.setThreshold();
    }

    public List<Point> getInterestPoints() {
        List<Point> pointList = new LinkedList<>();
        for (var r : this.report) {
            if(r.GetPointsChecked().size() > this.threshold)
                pointList.add(new Point(r.getAgentLocation()));
        }
        return pointList;
    }

    //by average
    private void setThreshold(){
        int sum = 0;
        for (var r : this.report) {
            sum += r.GetPointsChecked().size();
        }
        this.threshold = sum/this.report.size();
    }
}
