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
        ArrayList<Report> reports = new ArrayList<>();
        reports.addAll(this.methods.get(0).updateReports());
        for (int i=1;i<this.methods.size(); i++){
            List<Report> newReports=methods.get(i).updateReports();
            for (int r=0; r<reports.size();r++){
                if (reports.get(i).getInterestingPoint()==false
                && newReports.get(i).getInterestingPoint()==true){
                    reports.set(i,newReports.get(i));
                }
            }
        }
        return reports;
    }


}
