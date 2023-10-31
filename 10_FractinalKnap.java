//Fractional Knapsack

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Item {
    int profit;
    int weight;

    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
    }
}

public class Main{
    static class Pair {
        double first;
        double second;

        Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }
    }

    static boolean compare(Pair p1, Pair p2) {
        return p1.first > p2.first;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        System.out.print("Enter Number of Objects: ");
        n = scanner.nextInt();

        System.out.println("Enter Profit and Weight (P W): ");
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int profit = scanner.nextInt();
            int weight = scanner.nextInt();
            items.add(new Item(profit, weight));
        }

        System.out.print("Enter the Capacity: ");
        int w = scanner.nextInt();

        List<Pair> a = new ArrayList<>();

        for (Item item : items) {
            a.add(new Pair((double) item.profit / item.weight, (double) item.weight));
        }

        Collections.sort(a, (p1, p2) -> Double.compare(p2.first, p1.first));

        List<Double> solution = new ArrayList(Collections.nCopies(n, 0.0));
        double ans = 0.0;

        for (int i = 0; i < n; i++) {
            if (w >= a.get(i).second) {
                ans += items.get(i).profit;
                w -= a.get(i).second;
                solution.set(i, 1.0);
            } else {
                double vw = (double) items.get(i).profit / items.get(i).weight;
                ans += vw * w;
                solution.set(i, (double) w / items.get(i).weight);
                w = 0;
                break;
            }
        }

        System.out.println("\nOptimal Solution Vector: (" + solution + ")");
        System.out.println("\nOptimal Value: " + ans);
        scanner.close();
    }
}



/*

Enter Number of Objects: 5
Enter Profit and Weight (P W): 
60 10
100 20
120 30
90 5
50 15
Enter the Capacity: 50

Optimal Solution Vector: ([1.0, 1.0, 1.0, 3.0, 0.0])

Optimal Value: 550.0

*/
