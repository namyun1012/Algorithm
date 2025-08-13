package Programmers;
import java.io.*;
import java.util.*;

public class Q12979 {


    // Greedy 떠올리기 어럽다.
// Parametric 전혀 아님
    class Solution {

        public static int answer = 0;

        public int solution(int n, int[] stations, int w) {

            int m = stations.length;
            int start = 1;

            for (int station : stations) {
                int end = station - w;

                for (int j = start; j < end;) {
                    answer++;
                    j += (2*w + 1);
                }

                start = station + w + 1;
            }


            for (int j = start; j <= n;) {
                answer++;
                j += (2*w + 1);
            }




            return answer;
        }
    }
}
