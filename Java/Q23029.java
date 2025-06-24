import java.io.*;
import java.util.*;

// DP 일 듯?
// 2번 연속 시 다음 못 먹음
// 연속해서 진행시 /2 만큼 먹기 가능
// ArrayIndexOutofBounds? 문제? 왜 => br.close? or N + 1?


public class Q23029 {

    static int N;
    static int[] arr;
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new int[N + 1][2]; // 0 안먹음, 1 먹음
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0][0] = 0;
        dp[0][1] = arr[0];
        
        dp[1][0] = arr[0];
        dp[1][1] = Math.max(arr[0] + arr[1] / 2, arr[1]);

        for (int i = 2; i < N; i++) {
            
            // 이번에는 안 먹음
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);

            // 이번에 먹을 것임 선정하기
            dp[i][1] = Math.max(dp[i-2][0] + arr[i-1] + arr[i] / 2, dp[i-1][0] + arr[i]);

        }

        System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));

        br.close();
    }

}
