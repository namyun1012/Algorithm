import java.io.*;
import java.util.*;

// 역시 단순 DFS 로는 처리 불가
// 시간 제한 1초임
// 현재 59% 에서 틀림 -> 최소값 문제는 아닌듯


public class Q2169 {

    static int N;
    static int M;
    static int[][] board;
    static int[][] visited;
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];
        visited = new int[N + 1][M + 1];
        distance = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= M; j++) {
                distance[i][j] = Integer.MIN_VALUE;
            }
        }


        distance[1][1] = board[1][1];

        for (int j = 2; j <= M; j++) {
            distance[1][j] = board[1][j] + distance[1][j - 1];
        }

        for (int i = 2; i <= N; i++) {

            int[][] temp = new int[M + 2][2];
            // 초기값 문제

            temp[1][0] = distance[i-1][1] + board[i][1];

            // 왼쪽 or 위쪽 중에 선정
            for (int j = 2; j <= M; j++) {
                temp[j][0] = Math.max(distance[i-1][j], temp[j - 1][0]) + board[i][j];
            }
            
            // 오른쪽 or 위쪽 중에 선정함 (역순 진행)
            temp[M][1] = distance[i-1][M] + board[i][M];
            for (int j = M - 1; j >= 1; j--) {
                temp[j][1] = Math.max(distance[i-1][j], temp[j + 1][1]) + board[i][j];
            }

            for (int j = 1; j <= M; j++) {
                distance[i][j] = Math.max(temp[j][0], temp[j][1]);
            }
        }

        System.out.println(distance[N][M]);

        br.close();
    }


}
