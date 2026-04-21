package Programmers;
import java.io.*;
import java.util.*;

public class Q169199 {

// BFS 좀 변형 -> Move 할 때 끝까지 움직이도록함.

    class Solution {

        static int N;
        static int M;

        static char[][] map;

        static int[] dx = {0,1,0,-1};
        static int[] dy = {1,0,-1,0};

        public int solution(String[] board) {

            N = board.length;
            M = board[0].length();

            map = new char[N][M];
            int[][] visited = new int[N][M];

            int[] start = new int[2];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = board[i].charAt(j);

                    if (map[i][j] == 'R') {
                        start[0] = i;
                        start[1] = j;
                    }

                }
            }


            Queue<int[]> queue = new ArrayDeque<int[]>();

            queue.add(start);
            visited[start[0]][start[1]] = 1;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                if (map[cur[0]][cur[1]] == 'G') return visited[cur[0]][cur[1]] - 1; // 보정용으로 -1 해줌

                for (int dir = 0; dir < 4; dir++) {
                    int[] nxt = move(cur[0], cur[1], dir);

                    if (visited[nxt[0]][nxt[1]] > 0) continue;

                    queue.add(nxt);
                    visited[nxt[0]][nxt[1]] = visited[cur[0]][cur[1]] + 1;

                }
            }

            return -1;
        }

        // dir 에 따른 움직인 후의 nxt 값 보여줌.
        // map 기준
        public int[] move(int y, int x, int dir) {
            int nxt_y = y;
            int nxt_x = x;

            while (true) {
                if (nxt_y +  dy[dir] < 0 || nxt_y + dy[dir] >= N
                        || nxt_x + dx[dir] < 0 || nxt_x + dx[dir] >= M) break;
                if (map[nxt_y + dy[dir]][nxt_x + dx[dir]] == 'D') break;

                nxt_y += dy[dir];
                nxt_x += dx[dir];
            }


            return new int[]{nxt_y, nxt_x};
        }
    }
}
