package Programmers;
import java.io.*;
import java.util.*;
public class Q42898 {


// 원트, 이거 level 2인 것 같은데;;

    class Solution {

        static int DIVIDE = 1_000_000_007;
        static int[][] board;
        static int[] dx = {0, 1};
        static int[] dy = {1, 0};

        public int solution(int m, int n, int[][] puddles) {

            board = new int[n + 1][m + 1];

            for (int i = 0; i < puddles.length; i++) {
                int[] cur = puddles[i];
                board[cur[1]][cur[0]] = -1;
            }

            board[1][1] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (board[i][j] == -1) continue;

                    int up = (board[i-1][j] % DIVIDE);

                    if (up != -1) {
                        board[i][j] = (board[i][j] + up) % DIVIDE;
                    }

                    int left = board[i][j-1];

                    if (left != -1) {
                        board[i][j] = (board[i][j] + left) % DIVIDE;
                    }


                }
            }


            return board[n][m] % DIVIDE;
        }
    }
}
