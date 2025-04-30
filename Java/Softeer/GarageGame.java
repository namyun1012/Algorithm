package Softeer;
import java.util.*;
import java.io.*;

// 시간 초과 나서 도저히 못풀겠다.
// 현재 시간 초과 발생 중, 내리는 알고리즘 수정 필요
// 그냥 모르겠다.
// 3N 에서 N 으로 줄여보기 =>  통과함

public class GarageGame {

    public static int N;

    public static int[][] board;
    public static int result = 0;
    public static int[] drops;
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {1,-1,0,0};

    public static class Coord {
        int x, y;

        public Coord(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public static void solution(int index, int score, int[][] cur_board, int[] cur_drop) {

        if (index == 3) {
            result = Math.max(result, score);
            return;
        }
        
        int[][] visited = new int[N][N];
        int[][] new_board = new int[N][N];
        int[] new_drop = Arrays.copyOf(cur_drop, N);

        for (int y = 0; y < N; y++) {
            new_board[y] = Arrays.copyOf(cur_board[y], N);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j] == 1 || cur_board[i][j] == 0)
                    continue;

                // 새로운 board 만들어 주기
                int[] new_result = game(new Coord(i , j), new_board, score, visited, index, new_drop);
                int new_score = new_result[0];
                int new_max_y = new_result[1];
                int new_min_x = new_result[2];
                int new_max_x = new_result[3];

                solution(index + 1, new_score, new_board, new_drop);
                
                // board 원상 복구가 안되는 것 같은데
                // 원상 복구 좀 해결하기

                for(int x = new_min_x; x <= new_max_x; x++) {
                    new_drop[x] = cur_drop[x];
                }

                for(int y = new_max_y; y >= 0; y--) {
                    for(int x = new_min_x; x <= new_max_x; x++) {
                        new_board[y][x] = cur_board[y][x];
                    }
                }

            }
        }
    }

    public static int[] game(Coord select, int[][] cur_board, int score, int[][] solution_visited, int index, int[] cur_drops) {

        int min_x = N - 1;
        int min_y = N - 1;

        int max_x = 0;
        int max_y = 0;
        int count = 0;

        int color = cur_board[select.y][select.x];


        Queue<Coord> queue = new ArrayDeque<Coord>();
        queue.add(select);
        solution_visited[select.y][select.x] = 1;


        cur_board[select.y][select.x] = 0;

        while(!queue.isEmpty()) {
            Coord cur = queue.remove();
            min_x = Math.min(cur.x, min_x);
            min_y = Math.min(cur.y, min_y);
            max_x = Math.max(cur.x, max_x);
            max_y = Math.max(cur.y, max_y);
            count += 1;


            for (int dir = 0; dir < 4; dir++) {
                int nxt_x = cur.x + dx[dir];
                int nxt_y = cur.y + dy[dir];

                if(nxt_x < 0 || nxt_x >= N || nxt_y < 0 || nxt_y >= N || solution_visited[nxt_y][nxt_x] == 1 || color != cur_board[nxt_y][nxt_x])
                    continue;

                solution_visited[nxt_y][nxt_x] = 1;
                cur_board[nxt_y][nxt_x] = 0;
                queue.add(new Coord(nxt_y, nxt_x));
            }
        }

        score += (max_x - min_x + 1) * (max_y - min_y + 1) + count;
        
        // 채우는 과정
        if(index < 2) {
//            for (int i = 0; i < N; i++) {
//                for(int j = 0; j < N; j++) {
//                    System.out.print(cur_board[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();


            for (int x = min_x; x <= max_x; x++) {
                int cur_drop = cur_drops[x];

                for (int y = max_y; y >= 0; y--) {
                    int k = y;

                    if(cur_board[k][x] == 0) {
                        int check = 1;
                        
                        // 칸 내에서 존재하는 지 찾기
                        while (k >= 0 && cur_board[k][x] == 0) {
                            k--;
                        }

                        if (k >= 0) {
                            cur_board[y][x] = cur_board[k][x];
                            cur_board[k][x] = 0;
                        }

                        else {
                            cur_board[y][x] = board[cur_drop][x];
                            cur_drop -= 1;
                        }

                    }
                }


                cur_drops[x] = cur_drop;

            }
//
//            for (int i = 0; i < N; i++) {
//                for(int j = 0; j < N; j++) {
//                    System.out.print(cur_board[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        return new int[] {score, max_y, min_x, max_x};
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[3 * N][N];

        for (int i = 0; i < 3 * N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                board[i][j] = cur;
            }
        }

        drops = new int[N];
        for (int i = 0; i < N; i++)
            drops[i] = 2 * N - 1;


        int[][] cur_board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                cur_board[i][j] = board[2*N +i][j];
        }


        solution(0, 0, cur_board, drops);

        System.out.println(result);
    }
}
