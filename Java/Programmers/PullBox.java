package Programmers;

import java.io.*;
import java.util.*;

// 뭔가 이상하게 생각하다 어려운 문제?
public class PullBox {
    public int solution(int n, int w, int num) {
        int h = (n / w) + 1;

        int[][] board = new int[h + 3][w + 1];
        int[] heights = new int[w + 1];
        int cur = 1;

        for (int j = 1; j <= h; j++) {

            if (j % 2 == 1) {
                for (int i = 1; i <= w; i++) {
                    if(cur > n) break;


                    board[j][i] = cur;
                    heights[i] += 1;
                    cur++;
                }
            }

            else {
                for (int i = w; i >= 1; i--) {
                    if(cur > n) break;


                    board[j][i] = cur;
                    heights[i] += 1;
                    cur++;
                }

            }
        }

        int num_index = -1;
        int num_height = -1;
        for (int j = 1; j <= h; j++) {
            for (int i = 1; i <= w; i++) {
                if(board[j][i] == num) {
                    num_index = i;
                    num_height = j;
                    break;
                }
            }
        }


        int answer = heights[num_index] - num_height + 1;



        return answer;
    }
}