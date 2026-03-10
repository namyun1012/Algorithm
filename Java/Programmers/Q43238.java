package Programmers;
import java.io.*;
import java.util.*;
public class Q43238 {


    // Time 으로 이진 탐색 진행 해 보기
    class Solution {
        public long solution(int n, int[] times) {
            long answer = 0;

            long max = 0;
            for (int i = 0; i < times.length; i++) {
                max = Math.max(max, times[i]);
            }


            long start = 0;
            long end = max * n;
            answer = end;


            while(start <= end) {
                long mid = (start + end) / 2;
                //System.out.println(mid);
                long people = 0;
                for (int i = 0; i < times.length; i++) {
                    people += (mid / times[i]);
                }

                if (people >= n) {
                    answer = Math.min(answer, mid);
                    end = mid - 1;
                }

                else {
                    start = mid + 1;
                }

            }

            return answer;
        }
    }
}
