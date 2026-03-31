package Programmers;

import java.io.*;
import java.util.*;

public class Q86971 {


// 전선 중 1개만 끊으면 된다고 함.
// 절대값 차이의 최소 만 찾으면 되는 듯

    class Solution {
        public int solution(int n, int[][] wires) {
            int size = wires.length;
            int result = Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                List<Integer>[] graphs = new List[n + 1];

                for (int j = 1; j <= n; j++) {
                    graphs[j] = new ArrayList<Integer>();
                }

                for (int j = 0; j < size; j++) {
                    if (i == j) continue; // 1개 끊음

                    int start = wires[j][0];
                    int end = wires[j][1];

                    graphs[end].add(start);
                    graphs[start].add(end);
                }

                // 방문
                int[] visited = new int[n + 1];

                // 1번 방문

                Queue<Integer> queue = new ArrayDeque<Integer>();
                int count = 0;


                queue.add(1);
                visited[1] = 1;
                count++;

                while(!queue.isEmpty()) {
                    int cur = queue.poll();

                    for (int nxt : graphs[cur]) {
                        if (visited[nxt] == 1) continue;
                        visited[nxt] = 1;
                        queue.add(nxt);
                        count++;
                    }
                }

                // 절댓값 격차 구하기
                result = Math.min(result, Math.abs(n - count * 2));
            }

            return result;
        }
    }
}
