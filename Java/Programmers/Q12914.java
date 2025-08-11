package Programmers;

import java.io.*;
import java.util.*;

public class Q12914 {

// 첫 번째에서 런타임 에러?

    class Solution {

        static long[] dp;
        static long divide = 1234567;

        public long solution(int n) {

            dp = new long[n + 1];
            long answer = 0;
            dp[0] = 0;
            dp[1] = 1;

            if (n >= 2)
                dp[2] = 2;

            answer = topdown(n);


            return answer;
        }

        public long topdown(int n) {
            if (dp[n] != 0) return dp[n];
            if (n == 0) return 0;

            long result = ((topdown(n - 1) % divide) + (topdown(n - 2) % divide)) % divide;
            dp[n] = result;

            return result;
        }

    }
}
