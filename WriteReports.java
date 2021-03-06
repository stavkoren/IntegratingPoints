import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteReports {
    Element rootElement;
    Document doc;

    public WriteReports() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.newDocument();

            //root element
            this.rootElement = this.doc.createElement("path");
            doc.appendChild(this.rootElement);

        } catch (Exception e) {
            System.err.println("Error while create xml");
        }
    }

    public void write(List<Report> reportList) {
        //for each report (point), will create an element
        for (Report r : reportList) {
            Element currentPoint = this.doc.createElement("Point");
            this.rootElement.appendChild(currentPoint);
            //point location
            Attr loc = doc.createAttribute("location");
            loc.setValue(getPointAsString(r.getAgentLocation()));
            currentPoint.setAttributeNode(loc);
            //this point is interesting?
            Attr isInters = doc.createAttribute("interesting");
            isInters.setValue(r.getInterestingPoint().toString());
            currentPoint.setAttributeNode(isInters);
            //adding score attribute
            Attr score = doc.createAttribute("score");
            score.setValue(String.valueOf(r.getScore()));
            currentPoint.setAttributeNode(score);
        }


    }

    //saving the xml. must call this function
    void save(Result result) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(this.doc);
            transformer.transform(source, result);
        } catch (Exception e) {
            System.err.println("Error while saving XML file");
        }

    }

    //get string of point from point object
    String getPointAsString(Point p) {
        return ("(" + String.valueOf(p.getX()) + "," + String.valueOf(p.getY()) + ")");
    }

}


