import java.util.Scanner;

/**
 * Created by blacktiger on 12/20/2015.
 */
public class InterruptThread implements Runnable{
    public void run() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().isEmpty()) {
            System.exit(0);
        }
    }
}
