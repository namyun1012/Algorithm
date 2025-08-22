package Programmers;
import java.io.*;
import java.util.*;
public class Q12924 {


    class Solution {
        public int solution(int n) {
            int[] board = new int[n + 1];

            int sum = 0;

            for (int i = 1; i <= n; i++) {
                sum = 0;

                for (int j = i; j <= n; j++) {
                    sum += j;
                    if (sum <= n) board[sum]++;
                    else break;
                }
            }

            int answer = board[n];
            return answer;
        }
    }
}
