
// Non-Recursive

public class Main {
    public static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        long[] fib = new long[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

    public static void main(String[] args) {
        int n = 20; // Change n to calculate different Fibonacci numbers
        long result = calculateFibonacci(n);
        System.out.println("Fibonacci(" + n + ") = " + result);
    }
}




// Recursive

public class Main {
    public static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = 20; // Change n to calculate different Fibonacci numbers
        long result = calculateFibonacci(n);
        System.out.println("Fibonacci(" + n + ") = " + result);
    }
}




/*
  Output:

    Fibonacci(20) = 6765

    Fibonacci(33) = 3524578
*/


/*
Non-Recursive Approach:

Time Complexity: O(n) - Linear time complexity because it calculates Fibonacci numbers iteratively.
Space Complexity: O(n) - Requires an array to store the calculated values.
Recursive Approach:

Time Complexity: O(2^n) - Exponential time complexity because it recalculates the same values multiple times.
Space Complexity: O(n) - Space complexity for the recursive call stack.


*/
