import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.HashMap;

class TreeNode {
    char data;
    float freq;
    TreeNode left;
    TreeNode right;

    public TreeNode(char data, float freq) {
        this.data = data;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    static int n = 0;
    static char[] alphabets = new char[30];
    static String[] codes = new String[30];

    static TreeNode createHuffmanTree() {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = null;
        PriorityQueue<TreeNode> minHeap = new PriorityQueue<>((a, b) -> Float.compare(a.freq, b.freq));

        System.out.print("\nEnter No. of alphabets: ");
        int numAlphabets = scanner.nextInt();

        for (int i = 0; i < numAlphabets; i++) {
            System.out.print("\nEnter alphabet: ");
            char alphabet = scanner.next().charAt(0);
            System.out.print("\nEnter frequency: ");
            float frequency = scanner.nextFloat();
            TreeNode node = new TreeNode(alphabet, frequency);
            minHeap.add(node);
        }

        while (minHeap.size() > 1) {
            TreeNode left = minHeap.poll();
            TreeNode right = minHeap.poll();
            TreeNode newNode = new TreeNode('\0', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            minHeap.add(newNode);
        }

        root = minHeap.poll();
        return root;
    }

    static void encode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a Message: ");
        String message = scanner.next();
        StringBuilder encodedMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            int index = findCharIndex(c);
            if (index >= 0) {
                encodedMessage.append(codes[index]);
            }
        }

        System.out.println("\nEncoded Message = " + encodedMessage);
    }

    static void decode(TreeNode root) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter an Encoded message: ");
        String encodedMessage = scanner.next();
        TreeNode current = root;

        StringBuilder decodedMessage = new StringBuilder();

        for (char bit : encodedMessage.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedMessage.append(current.data);
                current = root;
            }
        }

        System.out.println("\nDecoded Message = " + decodedMessage);
    }

    static int findCharIndex(char c) {
        for (int i = 0; i < n; i++) {
            if (alphabets[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = null;

        while (true) {
            System.out.println("\nHuffman Coding Menu:");
            System.out.println("1. Create Huffman Tree");
            System.out.println("2. Encode a Message");
            System.out.println("3. Decode a Message");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    root = createHuffmanTree();
                    System.out.println("\nPrefix codes:");
                    n = 0;
                    preorder(root, 0, new char[30]);
                    break;
                case 2:
                    encode();
                    break;
                case 3:
                    decode(root);
                    break;
                case 4:
                    System.out.println("Successfully Exited.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    static void preorder(TreeNode p, int i, char[] word) {
        if (p != null) {
            if (p.left == null && p.right == null) {
                word[i] = 0;
                System.out.println(p.data + " --- " + new String(word));
                alphabets[n] = p.data;
                codes[n++] = new String(word);
            }
            word[i] = '0';
            preorder(p.left, i + 1, word);
            word[i] = '1';
            preorder(p.right, i + 1, word);
        }
    }
}



/*
output:

Huffman Coding Menu:
1. Create Huffman Tree
2. Encode a Message
3. Decode a Message
4. Exit
Enter Your Choice: 1

Enter No. of alphabets: 6
Enter alphabet: A
Enter frequency: 0.2
Enter alphabet: B
Enter frequency: 0.15
Enter alphabet: C
Enter frequency: 0.25
Enter alphabet: D
Enter frequency: 0.1
Enter alphabet: E
Enter frequency: 0.3
Enter alphabet: F
Enter frequency: 0.05

Huffman Coding Menu:
1. Create Huffman Tree
2. Encode a Message
3. Decode a Message
4. Exit
Enter Your Choice: 2

Enter a Message: CAFE

Encoded Message = 11100

Huffman Coding Menu:
1. Create Huffman Tree
2. Encode a Message
3. Decode a Message
4. Exit
Enter Your Choice: 3

Enter an Encoded message: 11100

Decoded Message = CAFE

Huffman Coding Menu:
1. Create Huffman Tree
2. Encode a Message
3. Decode a Message
4. Exit
Enter Your Choice: 4

Successfully Exited.


*/
