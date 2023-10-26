import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class Main {
    public static double fractionalKnapsack(int capacity, List<Item> items) {
        Collections.sort(items, new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                double ratio1 = (double) item1.value / item1.weight;
                double ratio2 = (double) item2.value / item2.weight;
                return Double.compare(ratio2, ratio1); // Sort in descending order by value-to-weight ratio
            }
        });

        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remainingCapacity = capacity - currentWeight;
                totalValue += (double) item.value * remainingCapacity / item.weight;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));

        int capacity = 50;

        double maxValue = fractionalKnapsack(capacity, items);

        System.out.println("Maximum value that can be obtained = " + maxValue);
    }
}






/*

  Output:


  Maximum value that can be obtained = 240.0

*/


