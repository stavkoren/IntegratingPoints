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

    //check which points are interesting (by compares)
    public List<Report> updateReports() {
        for (var r : this.report) {
            if(r.getPointsCompared().size() > this.threshold)
                r.IsInterestingPoint();
                r.setScore(r.getScore() + r.getPointsCompared().size() - this.threshold);
        }
        return this.report;
    }

    //threshold
    private void setThreshold(){
        List<Integer> valuesList = new LinkedList<>();
        for (var r : this.report) {
            valuesList.add(r.getPointsCompared().size());
        }
        this.threshold = MathUtils.getMean(valuesList) + 2*MathUtils.getSTD(valuesList); //mean + 2std
    }

}
