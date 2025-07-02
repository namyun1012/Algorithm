import java.io.*;
import java.util.*;

// 모든 수는 자신의 한 칸 위에 있는 수보다 큼
// 바로 정답
public class Q2075 {

    static int N;
    static int[][] board;
    static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        index = new int[N];

        Arrays.fill(index, N - 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N^2 의 알고리즘이면 가능한가?
        // index 에서 가장 작은 원소 찾기
        int temp = 0;


        for (int j = 0; j < N; j++) {
            temp = Integer.MIN_VALUE;
            int cur = -1;

            for(int i = 0; i < N; i++) {
                if (temp < board[index[i]][i]) {
                    temp = board[index[i]][i];
                    cur = i;
                }
            }

            index[cur] -= 1;
        }

        System.out.println(temp);
        br.close();
    }

}
