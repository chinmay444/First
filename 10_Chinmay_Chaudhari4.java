import java.util.Scanner;

public class Main {
    public static int knapsack(int[] values, int[] weights, int capacity, int[] solution) {
        int n = values.length;

        // Create a 2D table to store the results of subproblems.
        // dp[i][w] represents the maximum value that can be obtained with i items and a knapsack of capacity w.
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill in the table using dynamic programming.
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Trace the solution to get the included items.
        int i = n, w = capacity;
        while (i > 0 && w > 0) {
            if (dp[i][w] != dp[i - 1][w]) {
                solution[i - 1] = 1; // Item is included
                w -= weights[i - 1];
            }
            i--;
        }

        // The value in the bottom-right corner of the table represents the maximum value that can be obtained.
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] values = new int[n];
        int[] weights = new int[n];

        System.out.print("Enter the values of items: ");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter the weights of items: ");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        int[] solution = new int[n];

        // Measure the time taken by the algorithm
        long startTime = System.currentTimeMillis();
        int maxValue = knapsack(values, weights, capacity, solution);
        long endTime = System.currentTimeMillis();

        System.out.println("\nMaximum value: " + maxValue);
        System.out.print("\nSolution vector (1 for included, 0 for not included): ");
        for (int item : solution) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
    }
}



/*

Output:

Enter the number of items: 3
Enter the values of items: 60 100 120
Enter the weights of items: 10 20 30
Enter the knapsack capacity: 50

Maximum value: 220

Solution vector (1 for included, 0 for not included): 1 1 1 

Time taken: 0 milliseconds



*/
