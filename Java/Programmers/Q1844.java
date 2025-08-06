package Programmers;
import java.io.*;
import java.util.*;
public class Q1844 {


    class Solution {

        static int N;
        static int M;

        static int[] dx = {0,0,1,-1};
        static int[] dy = {1,-1,0,0};

        static int[][] visited;

        public int solution(int[][] maps) {
            N = maps.length;
            M = maps[0].length;

            visited = new int[N][M];

            Queue<int[]> queue = new ArrayDeque<>();

            visited[0][0] = 1;
            queue.add(new int[] {0, 0});

            while(!queue.isEmpty()) {
                int[] cur = queue.poll();
                int cur_y = cur[0];
                int cur_x = cur[1];
                // System.out.println(cur_y + " " + cur_x);
                for (int dir = 0; dir < 4; dir++) {
                    int nxt_y = cur_y + dy[dir];
                    int nxt_x = cur_x + dx[dir];

                    if(nxt_y < 0 || nxt_x < 0 || nxt_y >= N || nxt_x >= M) continue;
                    if(maps[nxt_y][nxt_x] == 0) continue;
                    if(visited[nxt_y][nxt_x] > 0) continue;

                    visited[nxt_y][nxt_x] = visited[cur_y][cur_x] + 1;
                    queue.add(new int[] {nxt_y, nxt_x});

                }


            }

            if (visited[N - 1][M - 1] == 0) return -1;
            else return visited[N - 1][M - 1];
        }
    }
}
