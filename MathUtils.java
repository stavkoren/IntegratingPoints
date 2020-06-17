import java.util.LinkedList;
import java.util.List;

public class MathUtils {


    //retuen the mean of list of integers
    public static float getMean(List<Integer> l) {
        float sum = 0;
        for (int i: l){
            sum += i;
        }
        return sum/l.size();
    }
    //return the std of list of integers
    public static double getSTD(List<Integer> l) {
        float mean = getMean(l);
        double diffSum = 0;
        for (int i:l) {
            diffSum += Math.pow(i-mean,2);
        }
        return Math.sqrt(diffSum/l.size());


    }
}
