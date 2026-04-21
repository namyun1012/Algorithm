package Programmers;

import java.io.*;
import java.util.*;

public class Q133500 {


// Tree DP 문제 => 아쉽...
// 연결된 부분.. Greedy 로 한번? ㄱ? 일단
// n 은 10만 => n^2 안됨.


    class Solution {

        List<Integer>[] graphs;

        // 0 -> i 번째 끌 떄, 1 -> i 번째 킬 때
        int[][] dp;
        int[] visited;

        public void DFS(int cur) {

            if (visited[cur] == 1) return;

            dp[cur][1] = 1;
            visited[cur] = 1;


            // 0
            for (int nxt : graphs[cur]) {
                if (visited[nxt] == 1) continue;


                DFS(nxt);
                dp[cur][0] += dp[nxt][1];
                dp[cur][1] += Math.min(dp[nxt][1], dp[nxt][0]);
            }
        }

        public int solution(int n, int[][] lighthouse) {

            graphs = new List[n + 1];
            dp = new int[n + 1][2];
            visited = new int[n + 1];

            for (int i = 1; i <= n; i++)
                graphs[i] = new ArrayList<Integer>();

            for (int i = 0; i < lighthouse.length; i++) {
                int start = lighthouse[i][0];
                int end = lighthouse[i][1];

                graphs[start].add(end);
                graphs[end].add(start);
            }

            int start = 1;
            DFS(start);

            return Math.min(dp[start][0], dp[start][1]);
        }
    }
}
