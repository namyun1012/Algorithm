package Programmers;
import java.io.*;
import java.util.*;

public class Q87946 {

// 최소, 소모 피로도 =>
// 최대한 많은 피로도.
// 던전 수는 최대 8개 => 그냥 전체 Backtracking 가능할 듯.
// 순서가 중요하므로 순열

    class Solution {

        // 제일 많이 도는 던전 수
        static int result = 0;
        static int N = 0;
        static int[] visited;
        public int solution(int k, int[][] dungeons) {

            result = 0;
            N = dungeons.length;
            visited = new int[N];

            backtracking(k, dungeons, 0);

            return result;
        }

        public void backtracking(int k, int[][] dungeons, int traveled) {

            result = Math.max(result, traveled);

            for (int i = 0; i < N; i++) {
                if (visited[i] == 1) continue;

                int require = dungeons[i][0];
                int cost = dungeons[i][1];

                if (k < require) continue;

                visited[i] = 1;
                backtracking(k - cost, dungeons, traveled + 1);
                visited[i] = 0;
            }
        }
    }
}
