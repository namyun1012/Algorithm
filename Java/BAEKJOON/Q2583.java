package BAEKJOON;

import java.io.*;
import java.util.*;

public class Q2583 {

    static int M;
    static int N;
    static int K;

    static int[][] board;
    static int[][] visited;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[M][N];
        ArrayList<Integer> results = new ArrayList<Integer>();

        // Draw
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            
            // 따로 구별은 필요 없는 듯함
            // 점 좌표이므로
            for (int i = y1 ; i < y2; i++) {
                for (int j = x1 ; j < x2; j++) {
                    board[i][j] = 1;
                }
            }
        }

        visited = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) continue;
                if (visited[i][j] == 1) continue;

                int area = BFS(i, j);
                results.add(area);
            }
        }

        System.out.println(results.size());

        Collections.sort(results, (a, b) -> (a - b));

        for (int result : results) {
            System.out.print(result + " ");
        }

    }

    public static boolean OOP(int y, int x){
        if (x < 0 || x >= N || y < 0 || y >= M) return false;
        return true;
    }

    public static int BFS(int y, int x) {
        int area = 0;

        Queue<int[]> queue = new ArrayDeque<int[]>();

        int[] start = new int[] {y, x};
        queue.add(start);
        visited[y][x] = 1;
        area++;


        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int[] nxt = new int[]{cur[0] + dy[dir], cur[1] + dx[dir]};

                if (!OOP(nxt[0], nxt[1])) continue;
                if (board[nxt[0]][nxt[1]] == 1) continue;
                if (visited[nxt[0]][nxt[1]] == 1) continue;

                queue.add(nxt);
                visited[nxt[0]][nxt[1]] = 1;
                area++;
            }
        }

        return area;
    }
}
