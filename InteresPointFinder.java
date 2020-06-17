import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class InteresPointFinder {
    private List<InteresMethod> methods = new LinkedList<>();

    //create all methods to check which are interesting points
    public InteresPointFinder(List<Report> r) {
        this.methods.add(new InteresByChecks(r));
        this.methods.add(new InteresByCompares(r));
        this.methods.add(new InteresByTendency(r));
    }

    public Set<Point> getInteresPoints() {
        Set<Point> pointSet = new HashSet<>();
        for (InteresMethod m : this.methods)
            pointSet.addAll(m.getInterestPoints());
        return pointSet;
    }
}
