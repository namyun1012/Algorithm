package Programmers;

import java.io.*;
import java.util.*;

public class Q42895 {

    class Solution {

        public int solution(int N, int number) {

            Set<Integer>[] dp = new Set[9];

            for (int i = 1; i <= 8; i++) dp[i] = new HashSet<Integer>();

            // 1번 사용
            dp[1].add(N);
            if(dp[1].contains(number)) return 1;

            // 2번 사용
            dp[2].add(N + N);
            dp[2].add(N - N);
            dp[2].add(N * N);
            dp[2].add(N / N);
            dp[2].add(N * 10 + N);
            if(dp[2].contains(number)) return 2;


            // dp[2] 에는 이미 들감.
            int repeatN = N * 10 + N;

            // i번 만큼 사용
            for (int i = 3; i <= 8; i++) {

                repeatN = repeatN * 10 + N;
                dp[i].add(repeatN);


                for (int j = 1; j < i; j++) {
                    int k = i - j;

                    // J, K 에 있는 것들을
                    for (int a : dp[j]) {
                        for (int b : dp[k]) {
                            dp[i].add(a + b);
                            dp[i].add(a - b);
                            dp[i].add(a * b);
                            if (b != 0) dp[i].add(a / b);
                        }
                    }
                }

                if(dp[i].contains(number)) return i;
            }


            return -1;
        }
    }
}
