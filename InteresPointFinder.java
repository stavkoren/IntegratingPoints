import java.util.*;

public class InteresPointFinder {
    private List<InteresMethod> methods = new LinkedList<>();

    //create all methods to check which are interesting points
    public InteresPointFinder(List<Report> r) {
        this.methods.add(new InteresByChecks(r));
        this.methods.add(new InteresByCompares(r));
        this.methods.add(new InteresByTendency(r));
    }

    public List<Report> updateReports() {
        List<Report> reports = new ArrayList<>();
        for (int i=0;i<this.methods.size(); i++){
            reports= this.methods.get(i).updateReports();
        }
        return reports;
    }


}
