import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class InteresByCompares implements InteresMethod {
    private List<Report> report;
    private double threshold;

    public InteresByCompares(List<Report> r) {
        this.report = r;
        this.setThreshold();
    }

    public Set<Point> getInterestPoints() {
        Set<Point> pointList = new HashSet<>();
        for (var r : this.report) {
            if(r.GetPointsCompared().size() > this.threshold)
                pointList.add(new Point(r.getAgentLocation()));
        }
        return pointList;
    }

    private void setThreshold(){
        List<Integer> valuesList = new LinkedList<>();
        for (var r : this.report) {
            valuesList.add(r.GetPointsCompared().size());
        }
        this.threshold = MathUtils.getMean(valuesList) + 2*MathUtils.getSTD(valuesList);
    }

}
