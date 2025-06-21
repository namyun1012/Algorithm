package Softeer;
// O ( Q * H * W)

import java.io.*;
import java.util.*;

public class ImageProcessing {
    static int H, W, Q;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] board;
    static int[][] visited;


    static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new int[H+1][W+1];

        for(int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Q = Integer.parseInt(br.readLine());

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            visited = new int[H+1][W+1];

            // BFS 로 탐색하기
            Queue<Coord> queue = new ArrayDeque<Coord>();
            int cur_color = board[i][j];

            queue.add(new Coord(i, j));
            visited[i][j] = 1;
            board[i][j] = c;


            while (!queue.isEmpty()) {
                Coord cur = queue.remove();

                for (int dir = 0; dir < 4; dir++) {
                    int nxt_x = cur.x + dx[dir];
                    int nxt_y = cur.y + dy[dir];

                    if(nxt_x < 1 || nxt_x > H || nxt_y < 1 || nxt_y > W) continue;
                    if(board[nxt_x][nxt_y] != cur_color) continue;
                    if(visited[nxt_x][nxt_y] == 1) continue;

                    board[nxt_x][nxt_y] = c;
                    visited[nxt_x][nxt_y] = 1;
                    queue.add(new Coord(nxt_x, nxt_y));
                }
            }
        }

        for (int i = 1; i <= H; i++) {
            for(int j = 1; j <= W; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        br.close();
    }
}
