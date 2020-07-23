import java.util.LinkedList;
import java.util.List;

public class MathUtils {


    //retuen the mean of list of integers

    public static <E> double getMean(List<E> l) {
        double sum = 0;
        for (E i: l){
            sum +=  Double.parseDouble(i.toString());
        }
        return sum/l.size();
    }
    //return the std of list of integers
    public static <E> double getSTD(List<E> l) {
        double mean = getMean(l);
        double diffSum = 0;
        for (E i:l) {
            diffSum += Math.pow(Double.parseDouble(i.toString())-mean,2);
        }
        return Math.sqrt(diffSum/l.size());


    }
}
