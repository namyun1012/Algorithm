import java.io.*;
import java.util.*;
// 좌표 등이 너무 헷갈린 문제,
// 각 구역을 5개의 선거구로 배분
// 선거구는 1개 이상의 구역 가짐
// 4중 for 문? 400 * 400 == 160000 가능 할 듯
// y, x 가 너무 혼동 되어 나타나 있는 듯하다.


public class Q17779 {
    static int N;
    static int[][] board;
    static int result = Integer.MAX_VALUE;
    
    // 5번을 미리 처리 해 놓는 것이 필요
    public static void execute(int x, int y, int d1, int d2) {
        int[] value = new int[5];

        int[][] line = new int[N+1][N+1];

        for(int i = 0; i <= d1; i++)
            line[x + i][y - i] = 5;

        for(int i = 0; i <= d2; i++)
            line[x + i][y + i] = 5;

        for(int i = 0; i <= d2; i++)
            line[x + d1 + i][y - d1+ i] = 5;

        for(int i = 0; i <= d1; i++)
            line[x + d2 + i][y + d2 - i] = 5;


        for (int r = 1; r < x + d1; r++) {
            for(int c = 1; c <= y; c++) {
                if(line[r][c] != 0) break;
                value[0] += board[r][c];
                line[r][c] = 1;
            }
        }

        for (int r = 1; r <= x + d2; r++) {
            for(int c = N; c > y; c--) {
                if(line[r][c] != 0) break;
                value[1] += board[r][c];
                line[r][c] = 2;
            }
        }

        for (int r = x + d1; r <= N; r++) {
            for(int c = 1; c < y-d1+d2; c++) {
                if(line[r][c] != 0) break;
                value[2] += board[r][c];
                line[r][c] = 3;
            }
        }

        for (int r = x + d2; r <= N; r++) {
            for(int c = N; c >= y-d1+d2; c--) {
                if(line[r][c] != 0) break;
                value[3] += board[r][c];
                line[r][c] = 4;
            }
        }

        for(int r = 1; r <= N; r++) {
            for(int c = 1; c <= N; c++) {
                if(line[r][c] == 5 || line[r][c] == 0)
                    value[4] += board[r][c];
            }
        }


        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 5; i++) {
            if (value[i] == 0) return;

            min = Math.min(value[i], min);
            max = Math.max(value[i], max);
        }

        result = Math.min(result, (max - min));

    }

    public static void main(String[] args) throws IOException {
        // Initialize
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];

        StringTokenizer st;

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                board[i][j] = cur;
            }
        }

        // 4중 for 문으로 구하기 ?
        for (int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                for(int d1 = 1; d1 <= N; d1++) {
                    for(int d2 = 1; d2 <= N; d2++) {
                        if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N)
                            execute(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(result);
        br.close();
    }

}
