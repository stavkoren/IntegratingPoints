import java.util.LinkedList;
import java.util.List;

public class InteresByChecks implements InteresMethod {
    private List<Report> report;
    private double threshold;

    public InteresByChecks(List<Report> r) {
        this.report = r;
        this.setThreshold();
    }

    //check which points are interesting (by checks)
    public List<Report> updateReports() {
        for (var r : this.report) {
            if (r.getPointsChecked().size() > this.threshold) {
                r.IsInterestingPoint();
                r.setScore(r.getScore() + r.getPointsChecked().size()/this.threshold);
            }
        }
        return this.report;
    }

    //threshold
    private void setThreshold() {
        List<Integer> valuesList = new LinkedList<>();
        for (var r : this.report) {
            valuesList.add(r.getPointsChecked().size());
        }
        this.threshold = MathUtils.getMean(valuesList) + 2 * MathUtils.getSTD(valuesList); //mean + 2std
    }
}
