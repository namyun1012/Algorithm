import java.io.*;
import java.util.*;
// 그냥 무난한 BFS 문제

public class Q10026 {

    static int N;
    static char[][] board;
    static int[][] visited;

    static int result1 = 0;
    static int result2 = 0;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        visited = new int[N][N];


        for (int i = 0 ; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = temp.charAt(j);
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j] == 0) {
                    bfs(i, j);
                    result1++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 'G') board[i][j] = 'R';
            }
        }

        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j] == 0) {
                    bfs(i, j);
                    result2++;
                }
            }
        }

        System.out.println(result1 + " " + result2);
        br.close();
    }


    public static void bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {y, x});

        while(!queue.isEmpty()) {
            int[] cur = queue.remove();
            int cur_y = cur[0];
            int cur_x = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nxt_y = cur_y + dy[dir];
                int nxt_x = cur_x + dx[dir];

                if(OOP(nxt_y, nxt_x)) continue;
                if(board[cur_y][cur_x] != board[nxt_y][nxt_x]) continue;


                queue.add(new int[] {nxt_y, nxt_x});
                visited[nxt_y][nxt_x] = 1;
            }
        }
    }


    public static boolean OOP(int y, int x) {
        if (x < 0 || y < 0 || x >= N || y >= N) return true;
        if (visited[y][x] == 1) return  true;
        return false;
    }

}
