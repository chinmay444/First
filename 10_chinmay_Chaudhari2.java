import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode {
    char data;
    int frequency;
    HuffmanNode left, right;

    HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

public class Main {
    public static void printCodes(HuffmanNode root, String code) {
        if (root == null) return;
        if (root.data != '\0') {
            System.out.println(root.data + ": " + code);
        }
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void buildHuffmanTree(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new MyComparator());
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            queue.add(newNode);
        }

        HuffmanNode root = queue.peek();
        printCodes(root, "");
    }

    public static void main(String[] args) {
        String text = "this is an example for huffman encoding";
        System.out.println("Huffman Codes:");
        buildHuffmanTree(text);
    }
}






/*
  output:
   Huffman Codes:
d: 00000
t: 00001
h: 0001
s: 0010
c: 00110
x: 00111
m: 0100
o: 0101
n: 011
u: 10000
l: 10001
a: 1001
r: 10100
g: 101010
p: 101011
e: 1011
i: 1100
f: 1101
 : 111

  */
