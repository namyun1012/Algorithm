package Programmers;

import java.io.*;
import java.util.*;
public class Q12940_공약수_공배수 {
    class Solution {
        public int[] solution(int n, int m) {

            int GCD = getGCD(Math.max(n, m), Math.min(n, m));

            int GCP = n * m / GCD;


            int[] answer = new int[2];
            answer[0] = GCD;
            answer[1] = GCP;

            return answer;
        }

        public int getGCD(int a, int b) {
            while (b != 0) {
                int r = a % b;
                a = b;
                b = r;
            }

            return a;
        }


    }
}
