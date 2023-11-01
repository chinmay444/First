import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows for matrix A: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter the number of columns for matrix A: ");
        int colsA = scanner.nextInt();
        System.out.print("Enter the number of rows for matrix B: ");
        int rowsB = scanner.nextInt();
        System.out.print("Enter the number of columns for matrix B: ");
        int colsB = scanner.nextInt();

        if (colsA != rowsB) {
            System.out.println("Matrix multiplication is not possible with these dimensions.");
            return;
        }

        int[][] matrixA = new int[rowsA][colsA];
        int[][] matrixB = new int[rowsB][colsB];

        System.out.println("Enter elements for matrix A:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                matrixA[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter elements for matrix B:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                matrixB[i][j] = scanner.nextInt();
            }
        }

        int[][] result = new int[rowsA][colsB];

        // Perform matrix multiplication without threads
        matrixMultiply(matrixA, matrixB, result);
        System.out.println("Matrix multiplication without threads:");
        printMatrix(result);

        // Perform matrix multiplication with one thread per row
        matrixMultiplyMultithreadedRow(matrixA, matrixB, result);
        System.out.println("Matrix multiplication with one thread per row:");
        printMatrix(result);

        // Perform matrix multiplication with one thread per cell
        matrixMultiplyMultithreadedCell(matrixA, matrixB, result);
        System.out.println("Matrix multiplication with one thread per cell:");
        printMatrix(result);

        // Measure performance of matrix multiplication without threads
        long startTime = System.nanoTime();
        matrixMultiply(matrixA, matrixB, result);
        long endTime = System.nanoTime();
        long singleThreadTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Matrix multiplication without threads took " + singleThreadTime + " ms");

        // Reset the result matrix
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                result[i][j] = 0;
            }
        }

        // Measure performance of matrix multiplication with one thread per row
        startTime = System.nanoTime();
        matrixMultiplyMultithreadedRow(matrixA, matrixB, result);
        endTime = System.nanoTime();
        long rowThreadTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Matrix multiplication with one thread per row took " + rowThreadTime + " ms");

        // Reset the result matrix
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                result[i][j] = 0;
            }
        }

        // Measure performance of matrix multiplication with one thread per cell
        startTime = System.nanoTime();
        matrixMultiplyMultithreadedCell(matrixA, matrixB, result);
        endTime = System.nanoTime();
        long cellThreadTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Matrix multiplication with one thread per cell took " + cellThreadTime + " ms");

        // Compare the execution times
        System.out.println("Performance comparison:");
        System.out.println("Single Thread: " + singleThreadTime + " ms");
        System.out.println("Thread per Row: " + rowThreadTime + " ms");
        System.out.println("Thread per Cell: " + cellThreadTime + " ms");
    }

    public static void matrixMultiply(int[][] A, int[][] B, int[][] result) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    public static void matrixMultiplyMultithreadedRow(int[][] A, int[][] B, int[][] result) {
        int rowsA = A.length;
        int colsB = B[0].length;

        ExecutorService executor = Executors.newFixedThreadPool(rowsA);

        for (int i = 0; i < rowsA; i++) {
            final int row = i;
            executor.submit(() -> {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < A[0].length; k++) {
                        result[row][j] += A[row][k] * B[k][j];
                    }
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all threads to finish
        }
    }

    public static void matrixMultiplyMultithreadedCell(int[][] A, int[][] B, int[][] result) {
        int rowsA = A.length;
        int colsB = B[0].length;

        ExecutorService executor = Executors.newFixedThreadPool(rowsA * colsB);

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                final int row = i;
                final int col = j;
                executor.submit(() -> {
                    for (int k = 0; k < A[0].length; k++) {
                        result[row][col] += A[row][k] * B[k][col];
                    }
                });
            }
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all threads to finish
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}




/*
Output:

Enter the number of rows for matrix A: 2
Enter the number of columns for matrix A: 2
Enter the number of rows for matrix B: 2
Enter the number of columns for matrix B: 2
Enter elements for matrix A:
1 2
3 4
Enter elements for matrix B:
5 6
7 8
Matrix multiplication without threads:
19 22 
43 50 
Matrix multiplication with one thread per row:
38 44 
86 100 
Matrix multiplication with one thread per cell:
57 66 
129 150 
Matrix multiplication without threads took 0 ms
Matrix multiplication with one thread per row took 12 ms
Matrix multiplication with one thread per cell took 12 ms
Performance comparison:
Single Thread: 0 ms
Thread per Row: 12 ms
Thread per Cell: 12 ms



*/
