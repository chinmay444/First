import java.util.*;

public class Main {
    static boolean[] col, d1, d2;
    static boolean[][] board;

    public static void solve(int r, int n) {
        if (r == n) {
            // Solution found, print the board
            printBoard(board);
            return;
        }

        for (int c = 0; c < n; c++) {
            if (!col[c] && !d1[r - c + n - 1] && !d2[r + c]) {
                col[c] = d1[r - c + n - 1] = d2[r + c] = true;
                board[r][c] = true;
                solve(r + 1, n);
                col[c] = d1[r - c + n - 1] = d2[r + c] = false;
                board[r][c] = false;
            }
        }
    }

    public static void printBoard(boolean[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        col = new boolean[n];
        d1 = new boolean[2 * n - 1];
        d2 = new boolean[2 * n - 1];
        board = new boolean[n][n];

        solve(0, n);
    }
}






/*
output:

4

. Q . . 
. . . Q 
Q . . . 
. . Q . 

. . Q . 
Q . . . 
. . . Q 
. Q . . 



*/
