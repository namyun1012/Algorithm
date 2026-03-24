package Programmers;
import java.io.*;
import java.util.*;

public class Q42839 {

    class Solution {

        public static final int MAX = 10_000_000;
        public static int[] prime;
        public static int size;
        public static Set<Integer> set;
        public static int[] digits;


        public int solution(String numbers) {
            // INIT
            size = numbers.length();
            prime = new int[MAX];
            digits = new int[8];
            set = new HashSet<Integer>();


            for (int i = 0; i < size; i++) {
                digits[i] =  (numbers.charAt(i) - '0');
            }

            CalculatePrime();
            backtracking(0, new int[10], 0);

            return set.size();
        }


        // Permunation
        // in Cur -> Number
        public void backtracking(int cur, int[] visited, int idx) {
            if (idx >= 1) {
                // System.out.println(cur);
                if (prime[cur] == 0) set.add(cur);
                if (idx == size) return;
            }

            // Select One and x10 and Add
            for (int i = 0; i < size; i++) {
                if (visited[i] == 1) continue;

                visited[i] = 1;
                int nxt = (cur * 10) + digits[i];
                backtracking(nxt, visited, idx + 1);

                visited[i] = 0;
            }
        }



        public void CalculatePrime() {
            prime[0] = 1;
            prime[1] = 1;
            int MAX_SQUARE = (int) Math.sqrt(MAX);


            for (int i = 2; i <= MAX_SQUARE; i++) {

                if (prime[i] == 1) continue;

                for (int j = i * 2; j < MAX; j += i) {
                    prime[j] = 1;
                }
            }
        }


    }
}
