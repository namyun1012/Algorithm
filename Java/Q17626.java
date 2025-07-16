import java.io.*;
import java.util.*;

// 실버 수준 DP 문제, 원트

public class Q17626 {

    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int len = (int) Math.sqrt(N);

        for (int i = 1; i <= len; i++) {
            dp[i * i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            if (dp[i] == 1) continue;

            int cur_len = (int) Math.sqrt(i);

            for (int j = 1; j <= cur_len; j++) {
                // 검증은 진행
                int prev = i - j*j;

                if(prev >= 1) {
                    dp[i] = Math.min(dp[prev] + 1, dp[i]);
                }

            }
        }

        System.out.println(dp[N]);
        br.close();
    }
}
