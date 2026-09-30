import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        Stack<Character> deleted = new Stack<>();
        int q = sc.nextInt();
        while(q-- > 0) {
            int type = sc.nextInt();
            if(type == 1) {
                String s = sc.next();
                for(char c : s.toCharArray()) {
                    st2.push(c);
                }
                st1.push(s.length());
            } else if (type == 2) {
                int k = sc.nextInt();
                st1.push(-k);
                while(k-- > 0) {
                    deleted.push(st2.pop());
                }
            } else if(type == 3) {
                int k = sc.nextInt();
                System.out.println(st2.get(k-1));
            } else if(type == 4) {
                int k = st1.pop();
                if(k > 0) {
                    while(k-- > 0) {
                        st2.pop();
                    }
                } else {
                    k = -k;
                    while(k-- > 0) {
                        st2.push(deleted.pop());
                    }
                }
            }
        }
    }
}
