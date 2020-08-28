import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteReports {
    public static void serializeToXML (List<Report> reports) throws IOException
    {
        FileOutputStream fos = new FileOutputStream("log.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :"+e.toString());
            }
        });
        for (Report r: reports) {
            encoder.writeObject(r);
        }
        encoder.close();
        fos.close();
    }

    }
