import java.io.*;
import java.util.*;


// 0 ~ 255 까지 가능한 지 확인하기
// backtracking 은 아닌 듯
// 68% 가량에서 틀림, 거의 원트라고 봐도 될 듯?

public class Q18111 {
    static int N, M, B;
    static int result = Integer.MAX_VALUE;
    static int result_height = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int h = 256; h >= 0; h--) {
            int cur_items = B;
            int cur_t = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    int diff = Math.abs(board[i][j] - h);
                    
                    // 가장 위에 있는 block 제거 필요
                    if (board[i][j] > h) {
                        cur_t += (2 * diff);
                        cur_items += diff;
                    }

                    else if(board[i][j] < h) {
                        cur_t += diff;
                        cur_items -= diff;
                    }
                }
            }

            if(cur_items >= 0 && cur_t < result) {
                result = cur_t;
                result_height = h;
            }
        }

        System.out.println(result + " " + result_height);
        br.close();
    }
}
