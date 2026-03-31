package Programmers;
import java.io.*;
import java.util.*;
public class Q92343 {


// 백트래킹 전체 탐색 가능할 듯
// 루트 노드 등 재 방문이 가능한 듯함..
// 그래프를 잘 그리는 것이 중요할 듯 함..
// Parent 를 방문했으면 해당 Node 는 언제든지 방문 가능 이러는 식이 나을 듯 함..

    class Solution {

        static int N;
        static int result;
        static int[] parents;

        public int solution(int[] info, int[][] edges) {
            N = info.length;
            result = 0;
            parents = new int[N];

            for (int i = 0; i < edges.length; i++) {
                int start = edges[i][0];
                int end = edges[i][1];

                parents[end] = start;
            }

            int[] visited = new int[N];
            visited[0] = 1;
            parents[0] = 0; // 그냥 Null 방지용, 어처피 접근 불가

            // for (int i = 0; i < N; i++) {
            //     System.out.print(parents[i] + " ");
            // }

            backtracking(0, 0, 0, info, visited);
            return result;
        }

        void backtracking(int cur, int wolfs, int sheeps, int[] infos, int[] visited)
        {

            if (infos[cur] == 0) sheeps += 1;
            else wolfs += 1;

            if (sheeps <= wolfs) return;

            result = Math.max(sheeps, result);

            for (int i = 0; i < N; i++) {
                if (visited[i] == 1) continue;
                if (visited[parents[i]] == 0) continue; // 부모가 방문되어야 방문 가능함

                visited[i] = 1;
                backtracking(i, wolfs, sheeps, infos, visited);
                visited[i] = 0;
            }


        }

    }
}
