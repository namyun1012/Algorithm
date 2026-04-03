import java.io.*;
import java.util.*;

public class Q10384 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String sentence = br.readLine();

            int[] map = new int[26];

            for (int j = 0; j < sentence.length(); j++) {
                char cur = sentence.charAt(j);

                if (cur >= 'A' && cur <= 'Z') {
                    map[cur - 'A'] += 1;
                }

                else if (cur >= 'a' && cur <= 'z') {
                    map[cur - 'a'] += 1;
                }
            }


            boolean check_1 = true;
            boolean check_2 = true;
            boolean check_3 = true;


            for (int j = 0; j < 26; j++) {
                if (map[j] < 3) check_3 = false;
                if (map[j] < 2) check_2 = false;
                if (map[j] < 1) check_1 = false;
            }

            System.out.print("Case " + (i + 1) + ": ");
            if (check_3) System.out.println("Triple pangram!!!");
            else if(check_2) System.out.println("Double pangram!!");
            else if(check_1) System.out.println("Pangram!");
            else System.out.println("Not a pangram");
        }




    }
}
