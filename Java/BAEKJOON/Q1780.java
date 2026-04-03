import java.io.*;
import java.util.*;

// 무난한 재귀 문제


public class Q1780 {
    static int N;
    static int[][] board;


    static int result1 = 0, result2 = 0, result3 = 0;

    // 시작점, size
    public static void solution(int y, int x, int size) {

        // first check
        boolean check = true;
        int first = board[y][x];

        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[y + i][j + x] != first) {
                    check = false;
                    break;
                }
            }
        }


        if(check) {
            if(first == -1) result1 += 1;
            else if(first == 0) result2 += 1;
            else if(first == 1) result3 += 1;
        }

        else {
            int nxt_size = size / 3;
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    solution(y + i * nxt_size, x + j * nxt_size, nxt_size);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0,0 , N);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        br.close();
    }
}
