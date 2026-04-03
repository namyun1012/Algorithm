// 벽 부수고 이동하기 2
// 나중에 뚫는 횟수가 필요한 경우가 있을 수 있음.
// 3차원 배열 처럼 생각 및 visited 잘 고려 해야 함.
import java.util.*;
import java.io.*;
import java.math.*;
public class Q14442 {
    static char[][] board;
    static int[][][] visited;
    static int[][] distance;
    static int N, M, K;
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {1,-1,0,0};



    public static boolean OOP(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= M)
            return true;
        return false;
    }

    public static class Coord {
        int x;
        int y;
        int k;
        public Coord(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    public static int bfs() {
        Queue<Coord> queue = new ArrayDeque<Coord>();

        queue.add(new Coord(0,0, 0));
        visited[0][0][0] = 1;
        distance[0][0] = 1;

        while (!queue.isEmpty()) {
            Coord cur = queue.remove();
            int cur_x = cur.x;
            int cur_y = cur.y;
            int cur_k = cur.k;

            for (int i = 0; i < 4; i++) {
                int nxt_x = cur_x + dx[i];
                int nxt_y = cur_y + dy[i];
                int nxt_k = cur_k;
                if(OOP(nxt_x, nxt_y)) continue;


                if(visited[nxt_x][nxt_y][cur_k] == 1) continue;

                // 벽인 경우
                if(board[nxt_x][nxt_y] == '1') {
                    // 아직 다 안 쓴 경우
                    if (cur_k < K && visited[nxt_x][nxt_y][cur_k + 1] == 0)
                        nxt_k = cur_k + 1;
                    // 다 쓴 경우
                    else
                        continue;
                }

                visited[nxt_x][nxt_y][nxt_k] = 1;
                distance[nxt_x][nxt_y] = distance[cur_x][cur_y] + 1;
                
                // 찾았으면 바로 return 함
                if (nxt_x == N - 1 && nxt_y == M - 1) {
                    return distance[nxt_x][nxt_y];
                }
                queue.add(new Coord(nxt_x, nxt_y, nxt_k));
            }
        }

        return distance[N-1][M-1];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        int result;

        board = new char[N][M];
        visited = new int[N][M][K+1];
        distance = new int[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M ; j++) {
                board[i][j] = input.charAt(j);
                distance[i][j] = -1;
            }
        }

        result = bfs();

        System.out.println(result);

        // bw.flush();
        // bw.close();
        br.close();
    }
}
