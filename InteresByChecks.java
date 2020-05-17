import java.util.LinkedList;
import java.util.List;

public class InteresByChecks {
    private List<Report> report;
    private double threshold;

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
        List<Integer> valuesList = new LinkedList<>();
        for (var r : this.report) {
            valuesList.add(r.GetPointsChecked().size());
        }
        this.threshold = MathUtils.getMean(valuesList) + MathUtils.getSTD(valuesList);
    }
}
