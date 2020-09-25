import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Report object for keeping location info
public class Report {
    private Point agentLocation;
    private List<Pair<Point,Point>> pointsCompared;
    private List<Point> pointsChecked;
    private boolean isInterestingPoint;
    private double score = 0;

    public Report(Point agentLocation){
        this.agentLocation=agentLocation;
        pointsChecked=new LinkedList<>();
        pointsCompared= new LinkedList<>();
        isInterestingPoint =false;
    }
    public Report(){

    }
    public void addPointCompared(Pair<Point,Point> p){
        this.pointsCompared.add(p);
    }

    public void addPointChecked(Point p){
        this.pointsChecked.add(p);
    }
    public List<Point> getPointsChecked(){
        return this.pointsChecked;
    }
    public void setPointsChecked(java.util.List<Point> pointsChecked) {
        this.pointsChecked = pointsChecked;
    }

    public List<Pair<Point,Point>> getPointsCompared(){
        return this.pointsCompared;
    }
    public void setPointsCompared(List<Pair<Point,Point>> pointsCompared){
        this.pointsCompared=pointsCompared;
    }
    public Point getAgentLocation(){return this.agentLocation;}
    public void setAgentLocation(Point loc){
        this.agentLocation=loc;
    }

    public void IsInterestingPoint(){
     this.isInterestingPoint =true;
    }
    public void setScore(double score) {this.score = score;}
    public double getScore() {return this.score;}
    public Boolean getInterestingPoint(){

        return this.isInterestingPoint;
    }

    public void setInterestingPoint(boolean interestingPoint) {
        isInterestingPoint = interestingPoint;
    }

    @Override
    public String toString() {
        return "Report ["+ "location="+ agentLocation.toString()+
                ", isInteresting="+ isInterestingPoint +
                ", pointsChecked="+ Arrays.toString(pointsChecked.toArray())+
                ", pointsCompared="+ Arrays.toString(pointsCompared.toArray())+
                "]";
    }
}
