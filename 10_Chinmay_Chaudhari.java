import java.util.Scanner;

public class Main {
    static int rSteps = 0;
    static int iSteps = 0;

    public static int rStepFibonacci(int n) {
        rSteps++;
        if (n < 0)
            return 0;
        if (n == 1 || n == 0)
            return 1;
        return rStepFibonacci(n - 1) + rStepFibonacci(n - 2);
    }

    public static void iStepFibonacci(int n) {
        int[] f = new int[n];
        f[0] = 0;
        f[1] = 1;
        iSteps = 1;
        System.out.print("Fibonacci Series (Iterative): ");
        for (int i = 2; i < n; i++) {
            iSteps++;
            f[i] = f[i - 1] + f[i - 2];
        }
        for (int i = 0; i < n; i++) {
            System.out.print(f[i] + " ");
        }
        System.out.println();
    }

    public static void printFibonacciSeries(int n) {
        System.out.print("Fibonacci Series (Recursive): ");
        for (int i = 0; i < n; i++) {
            System.out.print(rStepFibonacci(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int choice;
        int n;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Fibonacci Series Menu:");
            System.out.println("1. Calculate Iteratively");
            System.out.println("2. Calculate Recursively");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                System.out.print("Enter the number of Fibonacci numbers to generate: ");
                n = scanner.nextInt();

                if (n <= 0) {
                    System.out.println("Invalid input. Please enter a positive number.");
                    continue;
                }

                if (choice == 1) {
                    iSteps = 0;
                    long startTime = System.currentTimeMillis();
                    iStepFibonacci(n);
                    long endTime = System.currentTimeMillis();
                    double elapsedTime = endTime - startTime;

                    System.out.println("Iterative function called " + iSteps + " times.");
                    System.out.println("Time taken: " + elapsedTime + " milliseconds");
                } else if (choice == 2) {
                    rSteps = 0;
                    long startTime = System.currentTimeMillis();
                    printFibonacciSeries(n);
                    long endTime = System.currentTimeMillis();
                    double elapsedTime = endTime - startTime;

                    System.out.println("Recursive function called " + rSteps + " times.");
                    System.out.println("Time taken: " + elapsedTime + " milliseconds");
                }
            } else if (choice == 3) {
                System.out.println("Successfully Exited.");
                break;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        scanner.close();
    }
}




/*

output:

Fibonacci Series Menu:
1. Calculate Iteratively
2. Calculate Recursively
3. Quit
Enter your choice: 1
Enter the number of Fibonacci numbers to generate: 5
Fibonacci Series (Iterative): 0 1 1 2 3 
Iterative function called 4 times.
Time taken: 17.0 milliseconds
Fibonacci Series Menu:
1. Calculate Iteratively
2. Calculate Recursively
3. Quit
Enter your choice: 2
Enter the number of Fibonacci numbers to generate: 6
Fibonacci Series (Recursive): 1 1 2 3 5 8 
Recursive function called 34 times.
Time taken: 1.0 milliseconds
Fibonacci Series Menu:
1. Calculate Iteratively
2. Calculate Recursively
3. Quit
Enter your choice: 3
Successfully Exited.



*/
