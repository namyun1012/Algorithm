import java.io.*;
import java.util.*;

// 알고리즘테스트 실습 예비용 몸풀기
// 가벼운 원트

public class Q2468 {

    static int N;
    static int[][] board;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};


    static class Coord {
        int x;
        int y;


        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        board = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        // h 까지는 다 잠김
        for (int h = 0; h <= 100; h++) {
            int cur_result = 0;
            int[][] visited = new int[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    
                    // 이미 방문 했거나, 아니면 h 보다 같거나 낮아 갈 수가 없는 경우
                    if(visited[i][j] == 1 || board[i][j] <= h) continue;

                    // BFS 시작
                    Queue<Coord> q = new ArrayDeque<Coord>();
                    q.add(new Coord(i, j));
                    visited[i][j] = 1;

                    while(!q.isEmpty()) {
                        Coord cur = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nxt_x = cur.x + dx[dir];
                            int nxt_y = cur.y + dy[dir];


                            if(nxt_x < 0 || nxt_x >= N || nxt_y < 0 || nxt_y >= N) continue;
                            if(visited[nxt_x][nxt_y] == 1) continue;
                            if(board[nxt_x][nxt_y] <= h) continue;

                            visited[nxt_x][nxt_y] = 1;
                            q.add(new Coord(nxt_x, nxt_y));

                        }
                    }

                    cur_result++;
                }
            }

            result = Math.max(cur_result, result);

        }


        System.out.println(result);

    }

}
