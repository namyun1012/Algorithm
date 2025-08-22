package Programmers;
import java.io.*;
import java.util.*;

public class Q12952 {

    /*
     마지막에서 시간 초과 자꾸 발생함
     행 하나 당 한 개니까 for 문 굳이 2개 필요 없음
    */
    class Solution {

        static int[] board_x;
        static int[] board_y;
        static int[] board_xy; // x + y;
        static int[] board_yx; // n + x - y;
        static int answer = 0;

        public int solution(int n) {
            board_x = new int[n + 1];
            board_y = new int[n + 1];
            board_xy = new int[2 * n + 1];
            board_yx = new int[2 * n + 1];

            backtracking(0, n, 0, 0);

            return answer;
        }

        public static void backtracking(int idx, int n, int last_x, int last_y) {
            if (idx == n) {
                answer++;
                return ;
            }

            for (int i = last_x + 1; i <= last_x + 1; i++) {
                for (int j = 1; j <= n; j++) {
                    int x = i;
                    int y = j;
                    int xy = i + j;
                    int yx = n + i - j;

                    if(board_y[y] == 1 || board_xy[xy] == 1 || board_yx[yx] == 1) continue;

                    board_y[y] = 1;
                    board_xy[xy] = 1;
                    board_yx[yx] = 1;

                    backtracking(idx + 1, n, i, j);

                    board_y[y] = 0;
                    board_xy[xy] = 0;
                    board_yx[yx] = 0;

                }
            }


        }
    }
}
