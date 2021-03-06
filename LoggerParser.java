import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//parse XML file to Report Object
public class LoggerParser {
    private static final String CHECK="Check";
    private static final String COMPARE ="Compare";
    private static final String POINT="Point";
    private static final String LOCATION="location";

    //Read all reports from XML
    public static List<Report> GetReports(String path){
        try {
            List<Report> reports = new LinkedList<>();
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(POINT);
            //read each node separately
            for (int temp=0;temp<nList.getLength();temp++){
                    Element eElement=(Element) nList.item(temp);
                    Report report=new Report(parsePoint(eElement.getAttribute(LOCATION)));
                    NodeList pointsChecks=eElement.getElementsByTagName(CHECK);
                    for (int i=0;i<pointsChecks.getLength();i++){
                        Point p =parsePoint(pointsChecks.item(i).getTextContent());
                        report.addPointChecked(p);
                    }
                    NodeList pointsCompared=eElement.getElementsByTagName(COMPARE);
                    for (int i=0;i<pointsCompared.getLength();i++){
                        var p=parseTwoPoints(pointsCompared.item(i).getTextContent());
                        report.addPointCompared(p);
                    }
                    reports.add(report);
            }
            return reports;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Point parsePoint(String s){
        //parse (x,y) to Point
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        m.find();
        int x=Integer.parseInt(m.group());
        m.find();
        int y=Integer.parseInt(m.group());
        return  new Point(x,y);
    }
    //parse string contains two points into Pair<Point,Point>
    private static Pair<Point, Point> parseTwoPoints(String s){
        String[] splitCoordinates=s.split("\\),\\(",2);
        Point p1=parsePoint(splitCoordinates[0]);
        Point p2=parsePoint(splitCoordinates[1]);
        return new Pair<>(p1,p2);

    }
}
