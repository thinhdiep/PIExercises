import java.util.Scanner;


/**
 * Created by blacktiger on 12/19/2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int threads = 0;
        int processors = Runtime.getRuntime().availableProcessors();
        int choice;
        int maximumNumber = 0;
        double precision;


        System.out.println("Welcome to PI calculator Program ");
        System.out.println("Please choose number of threads to be executed ");
        System.out.println("The acceptable number is from 1 to " + processors + " : ");

        while ((threads > processors) || (threads < 1)) {
            System.out.println("Please enter an invalid threads number : ");
            Scanner threadsScanner = new Scanner(System.in);
            threads = threadsScanner.nextInt();
        }

        System.out.println("Type 1 to calculate PI with custom precision automatically");
        System.out.println("Type 2 to calculate PI with n number");
        System.out.println("Your Choice : ");
        Scanner choiceScanner = new Scanner(System.in);
        choice = choiceScanner.nextInt();
        Thread interruptThread = new Thread(new InterruptThread());
        switch (choice) {
            case 1:
                System.out.println("Input the precision : ");
                Scanner precisionScanner = new Scanner(System.in);
                precision = precisionScanner.nextDouble();
                CalculatorController firstController = new CalculatorController(precision);
                System.out.println("Press Enter To Exit ");
                interruptThread.start();
                System.out.print("Calculating...");
                firstController.calculate(threads);
                break;
            case 2:
                System.out.println("Input the maximum number : ");
                while (maximumNumber < 1000 || maximumNumber > 1000000000) {
                    Scanner maxScanner = new Scanner(System.in);
                    maximumNumber = maxScanner.nextInt();
                    System.out.println("Maximum number must be from 1000 to 1000000000 ");
                }
                CalculatorController secondController = new CalculatorController();
                System.out.println("Press Enter To Exit ");
                interruptThread.start();
                System.out.print("Calculating...");
                secondController.calculate(maximumNumber,processors);
        }
    }

}
