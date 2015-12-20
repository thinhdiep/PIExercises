import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by blacktiger on 12/19/2015.
 */
public class CalculatorController {
    private double precision;
    private ExecutorService executorService;
    private List<Future<Double>> resultList;
    private boolean isFound;

    public CalculatorController(double precision) {
        this.precision = precision;
    }

    public CalculatorController() {
    }

    public boolean compareValue(double expected, double actual, double precision){
        if (Math.abs(expected - actual) <= precision) {
            return true;
        }
        return false;
    }

    public void calculate(int threads) {
        executorService = Executors.newFixedThreadPool(threads);
        resultList = new ArrayList<Future<Double>>();
        isFound = false;
        double finalResult = 0;
        int listPosition = 0;
        int from = 0;
        int to = from + 9999999;
        long startTime = System.currentTimeMillis();
        while (!isFound) {
            System.out.print(".");
            for (int i = 0; i <  threads; i++) {
                Callable<Double> calculator = new CalculatorThread(from, to);
                resultList.add(executorService.submit(calculator));
                from = to + 1;
                to = from + 9999999;
            }

            for (int j = 0; j < threads; j++){
                try {
                    finalResult+= resultList.get(listPosition).get();
                    listPosition++;
                    if (compareValue(Math.PI, finalResult*4, precision)) {
                        isFound = true;
                        System.out.println("");
                        System.out.println("The accepted result is " + finalResult * 4);

                        long elapsedTime = System.currentTimeMillis() - startTime;
                        System.out.println("It Took " + elapsedTime +" ms To Finish The Task");
                        for (Future<Double> task : resultList){
                            task.cancel(true);
                        }
                        executorService.shutdownNow();
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public double calculate(int n, int threads) {
        executorService = Executors.newFixedThreadPool(threads);
        resultList = new ArrayList<Future<Double>>();
        double finalResult = 0;
        int listPosition = 0;
        int from = 0;
        int to = n/threads;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads; i++) {
            Callable<Double> calculator = new CalculatorThread(from, to);
            resultList.add(executorService.submit(calculator));
            from = to + 1;
            to = ((from + n/threads) > n) ? n : (from + n/threads);
        }

        for (int j = 0; j < threads; j++){
            try {
                finalResult+= resultList.get(listPosition).get();
                listPosition++;
                System.out.println("The accepted result is : " + finalResult * 4);
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("It tooked " + elapsedTime + " ms To Finish The Task");
                for (Future<Double> task : resultList){
                    task.cancel(true);
                }
                executorService.shutdown();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return finalResult;
    }
}
