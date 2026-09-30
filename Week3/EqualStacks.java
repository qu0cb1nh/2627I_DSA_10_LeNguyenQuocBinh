import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'equalStacks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY h1
     *  2. INTEGER_ARRAY h2
     *  3. INTEGER_ARRAY h3
     */

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
    // Write your code here
        int s1 = 0, s2 = 0, s3 = 0;
        Collections.reverse(h1);
        Collections.reverse(h2);
        Collections.reverse(h3);
        for(int x : h1) {
            s1 += x;
        }
        for(int x : h2) {
            s2 += x;
        }
        for(int x : h3) {
            s3 += x;
        }
        while(!h1.isEmpty() && !h2.isEmpty() && !h3.isEmpty()) {
            if(s1 == s2 && s2 == s3) return s1;
            int x = Math.min(s1, Math.min(s2, s3));
            if(s1 > x) {
                s1 -= h1.get(h1.size() - 1);
                h1.remove(h1.size() - 1);
            }
            if(s2 > x) {
                s2 -= h2.get(h2.size() - 1);
                h2.remove(h2.size() - 1);
            }
            if(s3 > x) {
                s3 -= h3.get(h3.size() - 1);
                h3.remove(h3.size() - 1);
            }
        }
        return 0;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
