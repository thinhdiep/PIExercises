import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by blacktiger on 12/19/2015.
 */
public class CalculatorThread implements Callable<Double>{
    private int from;
    private int to;

    public CalculatorThread(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Double call() throws Exception {
        double result = 0;

        for (int i = from; i <= to; i++) {
            result+= (i%2 == 0) ? (double)1/(2 * i + 1) : (double)(-1)/(2 * i + 1);
        }
        return result;
    }
}
