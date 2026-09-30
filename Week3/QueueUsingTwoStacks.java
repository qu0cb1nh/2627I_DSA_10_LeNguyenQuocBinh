import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> inStack = new Stack<>();
        Stack<Integer> outStack = new Stack<>();
        int q = scanner.nextInt();
        
        for (int i = 0; i < q; i++) {
            int type = scanner.nextInt();
            if (type == 1) {
                int x = scanner.nextInt();
                inStack.push(x);
            } else {
                if (outStack.isEmpty()) {
                    while (!inStack.isEmpty()) {
                        outStack.push(inStack.pop());
                    }
                }
                if (type == 2) {
                    outStack.pop();
                } else if (type == 3) {
                    System.out.println(outStack.peek());
                }
            }
        }
        
        scanner.close();
    }
}