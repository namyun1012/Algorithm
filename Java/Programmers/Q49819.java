package Programmers;
import java.io.*;
import java.util.*;

// 비교적 간단한 BFS 문제

public class Q49819 {

    class Solution {

        static List<Integer>[] graph;
        static int m;
        static int[] visited;
        static int max_distance = 0;

        public int solution(int n, int[][] edge) {
            m = edge.length;

            graph = new List[n + 1];
            visited = new int[n + 1];


            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < m; i++) {
                int[] cur = edge[i];

                int start = cur[0];
                int end = cur[1];

                graph[start].add(end);
                graph[end].add(start);
            }

            Queue<Integer> queue = new ArrayDeque<>();

            queue.add(1);
            visited[1] = 1;

            while (!queue.isEmpty()) {
                int cur = queue.remove();

                for (int nxt : graph[cur]) {
                    if (visited[nxt] > 0) continue;

                    queue.add(nxt);
                    visited[nxt] = visited[cur] + 1;
                    max_distance = Math.max(visited[nxt], max_distance);
                }
            }

            int answer = 0;

            for (int i = 1; i <= n; i++) {
                if (visited[i] == max_distance) answer++;
            }

            return answer;
        }
    }
}
